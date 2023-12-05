package TMSv3.SpedX.data.repository

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.UserApp
import TMSv3.SpedX.domain.repository.OrderRepository
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.OrdersList
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.repository.addOrderResponse
import TMSv3.SpedX.domain.repository.getOrderDetailsResponse
import TMSv3.SpedX.domain.repository.getOrdersResponse
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : OrderRepository {
    //val currentUser get() = auth.currentUser
    private val uid = auth.currentUser?.uid ?: ""
    private val userRef = db.collection("users").document(uid)


    override suspend fun getOrdersList(): getOrdersResponse {
        return try {
            val ordersRef = userRef.collection("orders").orderBy("createAt",
                Query.Direction.DESCENDING
            )
            Log.d(Constants.TAG, "przed snapshot--------------uid:: $uid")
            val snapshot = ordersRef.get().await()
            Log.d(Constants.TAG, "po snapshot--------------snapshot rozmiar:: ${snapshot.documents.size}")
            val ordersList = snapshot.documents.map { doc ->
                val order = doc.toObject(Order::class.java)
                Log.d(Constants.TAG, "Dokument: $doc, Zamówienie: $order")
                order
            }.filterNotNull()
            Log.d(Constants.TAG, "Order: $ordersList")
            Success(ordersList)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getOrderDetails(orderID: String): getOrderDetailsResponse {

        return try {
            val orderDetailRef = userRef.collection("orders").whereEqualTo("orderId", orderID)
            Log.d(Constants.TAG, "przed snapshot--------------pobranie konkretnego orderu:: $orderID")
            val snapshot = orderDetailRef.get().await()
            Log.d(Constants.TAG, "po snapshot--------------snapshot rozmiar:: ${snapshot.documents.size}")
            val ordersList = snapshot.documents.map { doc ->
                doc.toObject(Order::class.java)
            }
            val order = ordersList.first()
            Success(order)
        } catch (e: Exception) {
            Failure(e)
        }


    }

    override suspend fun addOrder(
        orderTitle: String,
        orderID: String,
        position: String?,
        finalDest: String,
        startDest: String,
        cargoName: String,
        cargoWeight: Int,
        driverID: String,
        cmrID: String?,
        createAt: String
    ): addOrderResponse {
        return try {
            val order = Order(
                orderTitle,
                orderID,
                position,
                finalDest,
                startDest,
                cargoName,
                cargoWeight,
                driverID,
                cmrID,
                createAt
            )
            userRef.collection("orders").document()
                .set(order).await()

            Success(true)
        } catch (e: Exception) {
            Failure(e)

        }    }





}