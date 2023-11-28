package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Response
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User
typealias UserNameResponse = Response<String>


interface MainRepository {
    val currentUser: FirebaseUser?

    suspend fun getUserName(): UserNameResponse



}