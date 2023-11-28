package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.OrdersList
import TMSv3.SpedX.domain.model.Response
import kotlinx.coroutines.flow.Flow


typealias getOrdersResponse = Response<List<Order>>


interface OrderRepository {

    suspend fun getOrdersList(): getOrdersResponse
}