package TMSv3.SpedX.data.repository

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.repository.GetPositionResponse
import TMSv3.SpedX.domain.repository.PositionRepository
import TMSv3.SpedX.domain.repository.SavePositionResponse
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PositionRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
): PositionRepository {


    private val uid = auth.currentUser?.uid ?: ""
    private val userRef = db.collection("users").document(uid)



    override suspend fun savePosition(position: Position, vehicleId: String): SavePositionResponse {
        return try {
            Log.d(Constants.TAG, "*****Przed zapisanmie pozycji -------: $position----$vehicleId")
//            val positionRef = position.vehicleId
            val position1 = Position(
            position.counter,
            position.driverId1,
            position.fuelLevel,
            position.fuelUsage,
            position.ignitionState,
            position.latitude,
            position.longitude,
            position.speed,
            position.timeStampUnix,
            position.vehicleId,
            position.vehicleName
            )

            userRef.collection("vehicles").document(vehicleId).set(position1).await()
            Log.d(Constants.TAG, "UDANE zapisanie pozycji--------: $position1")

            Success(true)
        }catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun getPosition(vehicleId: String): GetPositionResponse {
        return try {
            Log.d(Constants.TAG, "przed pobraniem pozycjiu z bazy dancyh func(getPosition)--------: $vehicleId")
            val positionRef = userRef.collection("vehicles").document(vehicleId)
            val snapshot = positionRef.get().await()
            Log.d(Constants.TAG, "pobranie snapshot pozycji--------: $snapshot")

                val position = snapshot.toObject(Position::class.java)
            Log.d(Constants.TAG, "serializacja pozycji----------: $position")
//            val positionData = snapshot.get("internalValue") as? Map<String, Any>
//            val position = positionData?.let {
//                Position(
//                    counter = it["counter"] as? Int ?: 0,
//                    driverId1 = it["driverId1"] as? String ?: "",
//                    fuelLevel = it["fuelLevel"] as? Double ?: 0.0,
//                    fuelUsage = it["fuelUsage"] as? String ?: "",
//                    ignitionState = it["ignitionState"] as? Boolean ?: false,
//                    latitude = it["latitude"] as? Double ?: 0.0,
//                    longitude = it["longitude"] as? Double ?: 0.0,
//                    speed = it["speed"] as? Int ?: 0,
//                    timeStampUnix = it["timeStampUnix"] as? Int ?: 0,
//                    vehicleId = it["vehicleId"] as? String ?: "",
//                    vehicleName = it["vehicleName"] as? String ?: ""
//                )
//            }
            Success(position)
        } catch (e: Exception){
            Failure(e)
        }
    }
}