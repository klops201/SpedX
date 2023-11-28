package TMSv3.SpedX.data.repository

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.UserApp
import TMSv3.SpedX.domain.repository.OrderRepository
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.OrdersList
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.repository.getOrdersResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : OrderRepository {
//    val currentUser get() = auth.currentUser
//    private val uid = auth.currentUser?.uid ?: ""
    private val ordersRef = db.collection("orders")

    override suspend fun getOrdersList(): getOrdersResponse {
        return try {
            val snapshot = ordersRef.get().await()
            val ordersList = snapshot.documents.map { doc ->
                doc.toObject(Order::class.java)!!
            }
            Log.d(Constants.TAG, "Order: $ordersList")
            Success(ordersList)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}