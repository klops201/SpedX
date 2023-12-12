package TMSv3.SpedX.data.repository

import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.repository.CmrRepository
import TMSv3.SpedX.domain.repository.addCmrFirebaseResponse
import TMSv3.SpedX.domain.repository.addCmrToOrderResponse
import TMSv3.SpedX.domain.repository.getCmrFromOrderResponse
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CmrRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage
) : CmrRepository {
    private val uid = auth.currentUser?.uid ?: ""
    private val userRef = db.collection("users").document(uid)




    override suspend fun addCmrFirebase(imageUri: Uri): addCmrFirebaseResponse {
        return try {
            val downloadUrl = storage.reference.child("cmr").child(uid)
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            (Success(downloadUrl))
        } catch (e: Exception) {
            (Failure(e))
        }
    }

    override suspend fun addCmrToOrder(downloadUri: Uri, orderID: String): addCmrToOrderResponse {
        return try {
            userRef.collection("orders").document(orderID)
                .update("cmrId", downloadUri.toString())
                .await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getCmrFromOrder( orderID: String): getCmrFromOrderResponse {
        return try {
            val imageUrl = userRef.collection("orders").document(orderID).get().await().getString("cmrId") ?: "brak Url"
            Success(imageUrl)
        } catch (e: Exception) {
            Failure(e)
        }
    }




}




