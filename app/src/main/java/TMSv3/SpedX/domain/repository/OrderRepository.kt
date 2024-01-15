package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.OrdersList
import TMSv3.SpedX.domain.model.Response
import kotlinx.coroutines.flow.Flow


typealias getOrdersResponse = Response<List<Order>>
typealias getUDOrdersResponse = Response<List<Order>>
typealias getOrderDetailsResponse = Response<Order?>
typealias addOrderResponse = Response<Boolean>
typealias editOrderResponse = Response<Boolean>
typealias deleteOrderResponse = Response<Boolean>
typealias markDoneResponse = Response<Boolean>


interface OrderRepository {

    suspend fun getOrdersList(): getOrdersResponse
    suspend fun getOrderDetails(orderID: String): getOrderDetailsResponse

    suspend fun addOrder(orderTitle: String, orderID: String, position: String?, finalDest: String,
                         startDest: String, cargoName: String, cargoWeight: Int,
                         driverID: String, cmrID: String?, createAt: String): addOrderResponse

    suspend fun editOrder(firestoreID: String, orderTitle: String, orderID: String, position: String, finalDest: String,
                         startDest: String, cargoName: String, cargoWeight: Int,
                         driverID: String, cmrID: String, createAt: String): editOrderResponse


    suspend fun deleteOrder(firestoreID: String): deleteOrderResponse

    suspend fun markDone(orderID: String): markDoneResponse

    suspend fun getUndoneOrders(): getUDOrdersResponse



}