package TMSv3.SpedX.data.repository

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.repository.DriverRepository
import TMSv3.SpedX.domain.repository.addDriverResponse
import TMSv3.SpedX.domain.repository.deleteDriverResponse
import TMSv3.SpedX.domain.repository.editDriverResponse
import TMSv3.SpedX.domain.repository.getDriverInfoResponse
import TMSv3.SpedX.domain.repository.getDriverTruckResponse
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


    override suspend fun getDriverInfo(firebaseID: String): getDriverInfoResponse {
        return try {
            val driverDetailRef = userRef.collection("drivers").document(firebaseID)
            val snapshot = driverDetailRef.get().await()
            Log.d(Constants.TAG, "snapshot wczytanego kierowcy(INFO) :: $snapshot")
            val driver = snapshot.toObject(Driver::class.java)
            Log.d(Constants.TAG, "driver po serializacji :: $driver")
            Success(driver)
        } catch (e: Exception) {
            Failure(e)
        }
    }


    override suspend fun deleteDriver(firebaseID: String): deleteDriverResponse {
        return try {
            Log.d(Constants.TAG, "%%%%% usuwanie kierowcy :: $firebaseID")

            userRef.collection("drivers").document(firebaseID).delete().await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)

        }
    }

    override suspend fun editDriver(
        firebaseID: String,
        driverName: String,
        driverPhoneNr: Int,
        driverId: String,
        vehicleId: String
    ): editDriverResponse {
        return try {
            Log.d(
                Constants.TAG,
                "???? szukanie kierowcy::FB =  $firebaseID,$driverName,$driverPhoneNr,$driverId,$vehicleId"
            )

            val driverRef = userRef.collection("drivers").document(firebaseID)
            driverRef
                .update(
                    mapOf(
                        "driverName" to driverName,
                        "driverPhoneNr" to driverPhoneNr,
                        "driverId" to driverId,
                        "vehicleId" to vehicleId

                    ),
                )
            Log.d(Constants.TAG, "po zapisaniu kierowy po edycie:: $firebaseID")
            Log.d(
                Constants.TAG,
                "PO EDICIE::FB =  $firebaseID,$driverName,$driverPhoneNr,$driverId,$vehicleId"
            )


            Success(true)
        } catch (e: Exception) {
            Log.e(Constants.TAG, "Błąd podczas aktualizacji danych kierowcy: ${e.message}", e)
            Failure(e)
        }
    }

    override suspend fun addDriver(
        driverName: String,
        driverPhoneNr: Int,
        driverId: String,
        vehicleId: String
    ): addDriverResponse {
        return try {
            val docId: String = userRef.collection("drivers").document().getId()

            val driver = Driver(
                docId,
                driverName,
                driverPhoneNr,
                driverId,
                vehicleId
            )
            userRef.collection("drivers").document(docId).set(driver).await()
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

    override suspend fun getDriverTruck(vehicleId: String): getDriverTruckResponse {
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