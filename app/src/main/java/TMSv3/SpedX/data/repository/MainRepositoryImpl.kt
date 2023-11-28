package TMSv3.SpedX.data.repository

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.repository.MainRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import TMSv3.SpedX.domain.model.UserApp
import TMSv3.SpedX.domain.repository.UserNameResponse
import TMSv3.SpedX.domain.model.Response.Failure
import TMSv3.SpedX.domain.model.Response.Success
import android.util.Log

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
): MainRepository {
    override val currentUser get() = auth.currentUser
    private val uid = auth.currentUser?.uid ?: ""
    private val docRef = db.collection("users").document(uid)




    override suspend fun getUserName(): UserNameResponse {
        return try {
            Log.d(Constants.TAG, "znaleziono usera ----------: $uid")
            val snapshot = docRef.get().await()
            val user = snapshot.toObject(UserApp::class.java)
            val name = user?.name?: ""
            Log.d(Constants.TAG, "objekt user-------: $user")
            Log.d(Constants.TAG, "imie usera: $name")
            Success(name)
        } catch (e: Exception) {
            Failure(e)
        }

    }
}