package TMSv3.SpedX.data.repository

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.repository.DeleteDriverResponse
import TMSv3.SpedX.domain.repository.DriverRepository
import TMSv3.SpedX.domain.repository.EditDriverResponse
import TMSv3.SpedX.domain.repository.GetDriverInfoResponse
import TMSv3.SpedX.domain.repository.GetDriverTruckResponse
import TMSv3.SpedX.domain.repository.getDriversListResponse
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DriverRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : DriverRepository {

    private val uid = auth.currentUser?.uid ?: ""
    private val userRef = db.collection("users").document(uid)


    override suspend fun getDriverInfo(firebaseID: String): GetDriverInfoResponse {
        return try {
            val driverDetailRef = userRef.collection("drivers").document(firebaseID)
            val snapshot = driverDetailRef.get().await()
            val driver = snapshot.toObject(Driver::class.java)
            Success(driver)
        } catch (e: Exception) {
            Failure(e)
        }
    }


    override suspend fun deleteDriver(firebaseID: String): DeleteDriverResponse {
        return try {
            userRef.collection("drivers").document(firebaseID).delete().await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)

        }
    }

    override suspend fun editDriver(
        driverId: String,
        driverName: String,
        driverPhoneNr: Int,
        firebaseID: String,
        vehicleId: String
    ): EditDriverResponse {
        return try {
            val driverRef = userRef.collection("drivers").document(firebaseID)
            driverRef
                .update(
                    mapOf(
                        "driverId" to driverId,
                        "driverName" to driverName,
                        "driverPhoneNr" to driverPhoneNr,
                        "firebaseID" to firebaseID, /// pozniej zakomentowac i sprawdzic
                        "vehicleId" to vehicleId

                        ),
                )
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getDriversList(): getDriversListResponse {
        return try {
            val driversDetailRef = userRef.collection("drivers")
            val snapshot = driversDetailRef.get().await()
            val driversList = snapshot.documents.map { doc ->
                val driver = doc.toObject(Driver::class.java)
                driver
            }.filterNotNull()
            Log.d(Constants.TAG, "driverslist: $driversList")
            Success(driversList)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getDriverTruck(vehicleId: String): GetDriverTruckResponse {
        return try {
            val vehicleDetailRef = userRef.collection("vehicles").document(vehicleId)
            val snapshot = vehicleDetailRef.get().await()
            val position = snapshot.toObject(Position::class.java)
            Success(position)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}