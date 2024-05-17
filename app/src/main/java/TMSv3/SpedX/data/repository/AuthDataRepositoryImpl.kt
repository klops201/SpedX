package TMSv3.SpedX.data.repository

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.GlobalLoginData
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.SentForm
import TMSv3.SpedX.domain.repository.AuthDataRepository
import TMSv3.SpedX.domain.repository.LoadASNResponse
import TMSv3.SpedX.domain.repository.LoadAsnApiResponse
import TMSv3.SpedX.domain.repository.LoadETollResponse
import TMSv3.SpedX.domain.repository.LoadPUESCResponse
import TMSv3.SpedX.domain.repository.LoadVinietResponse
import TMSv3.SpedX.domain.repository.UpdateASNResponse
import TMSv3.SpedX.domain.repository.UpdateETollResponse
import TMSv3.SpedX.domain.repository.UpdatePuescResponse
import TMSv3.SpedX.domain.repository.UpdateVinietResponse
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : AuthDataRepository {
    private val uid = auth.currentUser?.uid ?: ""
    private val userRef = db.collection("users").document(uid)

    override suspend fun updatePuesc(
        log: String,
        gate: String,
        NIP: String,
        address: String,
        borderPosition: String,
        borderRoad: String,
        cargoPermit: String,
        companyName: String,
        emailConfirmation: String,
        gpsID: String,
        trailerID: String,
        truckID: String
    ): UpdatePuescResponse {
        return try {
            val sentRef = userRef.collection("login_data").document("sent")

            sentRef
                .update(
                    mapOf(
                        "NIP" to NIP,
                        "address" to address,
                        "borderPosition" to borderPosition,
                        "borderRoad" to borderRoad,
                        "cargoPermit" to cargoPermit,
                        "companyName" to companyName,
                        "emailConfirmation" to emailConfirmation,
                        "gate" to gate,
                        "gpsID" to gpsID,
                        "log" to log,
                        "trailerID" to trailerID,
                        "truckID" to truckID,
//                        "type" to type,

                        ),
                )


            Success(true)
        } catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun updateEToll(log: String, gate: String): UpdateETollResponse {
        return try {
            val etollRef = userRef.collection("login_data").document("ticketHW")
            etollRef
                .update(
                    mapOf(
                        "gate" to gate,
                        "log" to log,
//                        "type" to type,

                    ),
                )
            Success(true)
        } catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun updateViniet(log: String, gate: String): UpdateVinietResponse {
        return try {
            val vinietRef = userRef.collection("login_data").document("viniet")
            vinietRef
                .update(
                    mapOf(
                        "gate" to gate,
                        "log" to log,
//                        "type" to type,

                    ),
                )
            Success(true)
        } catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun updateASN(
        customer: String,
        user: String,
        gate: String
    ): UpdateASNResponse {
        return try {
            val asnRef = userRef.collection("login_data").document("gpsTracking")
            asnRef
                .update(
                    mapOf(
                        "customer" to customer,
                        "gate" to gate,
//                        "type" to type,
                        "user" to user,
                    ),
                )
            Success(true)
        } catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun loadPUESC(): LoadPUESCResponse {
        return try {
            val sentRef = userRef.collection("login_data").document("sent")
            val snapshot = sentRef.get().await()
            Log.d(Constants.TAG, "snapshot wczytanego senta :: $snapshot")
            val sent = snapshot.toObject(SentForm::class.java)
            Log.d(Constants.TAG, "sent po serializacji :: $sent")
            Success(sent)
        } catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun loadEToll(): LoadETollResponse {
        return try {
            val etollRef = userRef.collection("login_data").document("ticketHW")
            val snapshot = etollRef.get().await()
            Log.d(Constants.TAG, "snapshot wczytanego etolla :: $snapshot")
            val etoll = snapshot.toObject(GlobalLoginData::class.java)
            Log.d(Constants.TAG, "etoll po serializacji :: $etoll")
            Success(etoll)
        } catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun loadViniet(): LoadVinietResponse {
        return try {
            val vinietRef = userRef.collection("login_data").document("viniet")
            val snapshot = vinietRef.get().await()
            Log.d(Constants.TAG, "snapshot wczytanej winiety :: $snapshot")
            val viniet = snapshot.toObject(GlobalLoginData::class.java)
            Log.d(Constants.TAG, "vinieta po serializacji :: $viniet")
            Success(viniet)
        } catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun loadASN(): LoadASNResponse {
        return try {
            val asnRef = userRef.collection("login_data").document("gpsTracking")
            val snapshot = asnRef.get().await()
            Log.d(Constants.TAG, "snapshot wczytanego ASN :: $snapshot")
            val asn = snapshot.toObject(ASNdata::class.java)
            Log.d(Constants.TAG, "asn po serializacji :: $asn")
            Success(asn)
        } catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun loadAsnApi(): LoadAsnApiResponse {
        return try {
            TODO("Not yet implemented - wystraczajace dane z loadASN")
        } catch (e: Exception){
            Failure(e)
        }
    }
}