package TMSv3.SpedX.data.repository

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.repository.OrderRepository
import TMSv3.SpedX.domain.repository.addOrderResponse
import TMSv3.SpedX.domain.repository.deleteOrderResponse
import TMSv3.SpedX.domain.repository.editOrderResponse
import TMSv3.SpedX.domain.repository.getOrderDetailsResponse
import TMSv3.SpedX.domain.repository.getOrdersResponse
import TMSv3.SpedX.domain.repository.getUDOrdersResponse
import TMSv3.SpedX.domain.repository.markDoneResponse
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : OrderRepository {
    private val uid = auth.currentUser?.uid ?: ""
    private val userRef = db.collection("users").document(uid)


    override suspend fun getOrdersList(): getOrdersResponse {
        return try {
            val ordersRef = userRef.collection("orders").orderBy(
                "createAt",
                Query.Direction.DESCENDING
            )
            Log.d(Constants.TAG, "przed snapshot--------------uid:: $uid")
            val snapshot = ordersRef.get().await()
            Log.d(
                Constants.TAG,
                "po snapshot--------------snapshot rozmiar:: ${snapshot.documents.size}"
            )
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
            val orderDetailRef = userRef.collection("orders").document(orderID)
            val snapshot = orderDetailRef.get().await()
            Log.d(Constants.TAG, "snapshot wczytanego zlecenia :: $snapshot")
            val order = snapshot.toObject(Order::class.java)
            Log.d(Constants.TAG, "zlecenie po serializacji :: $order")
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
            val docId: String = userRef.collection("orders").document().getId()


            val order = Order(
                docId,
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
            userRef.collection("orders").document(docId)
                .set(order).await()

            Success(true)
        } catch (e: Exception) {
            Failure(e)

        }
    }

    override suspend fun editOrder(
        firestoreID: String,
        orderTitle: String,
        orderID: String,
        position: String,
        finalDest: String,
        startDest: String,
        cargoName: String,
        cargoWeight: Int,
        driverID: String,
        cmrID: String,
        createAt: String
    ): editOrderResponse {
        return try {
            val orderDetailRef = userRef.collection("orders").document(firestoreID)
            Log.d(
                Constants.TAG,
                "przed snapshot--------------pobranie konkretnego orderu EDYCJA:: $firestoreID"
            )
            orderDetailRef
                .update(
                    mapOf(
                        "orderTitle" to orderTitle,
                        "orderId" to orderID,
                        "position" to position,
                        "finaldestination" to finalDest,
                        "startdestination" to startDest,
                        "cargoName" to cargoName,
                        "cargoWeight" to cargoWeight,
                        "driverId" to driverID,
                        "cmrId" to cmrID,
                        "createAt" to createAt,

                        ),
                )





            Success(true)
        } catch (e: Exception) {
            Failure(e)

        }


    }

    override suspend fun deleteOrder(firestoreID: String): deleteOrderResponse {
        return try {
            userRef.collection("orders").document(firestoreID).delete().await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)

        }
    }

    override suspend fun markDone(orderID: String): markDoneResponse {
        return try {
            userRef.collection("orders").document(orderID)
                .update("done", true)
                .await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getUndoneOrders(): getUDOrdersResponse {
        return try {
            val ordersRef = userRef.collection("orders").orderBy(
                "createAt",
                Query.Direction.DESCENDING
            )
//                .whereEqualTo("done", false)
            Log.d(Constants.TAG, "przed snapshot niedokonczone zamowienia--------------uid:: $uid")
            val snapshot = ordersRef.get().await()
            Log.d(
                Constants.TAG,
                "po snapshot-niedokonczone zamowienia-------------snapshot rozmiar:: ${snapshot.documents.size}"
            )
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
}