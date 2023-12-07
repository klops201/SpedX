package TMSv3.SpedX.presentation.orders.edit_order

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.repository.OrderRepository
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import TMSv3.SpedX.domain.model.Response.Loading
import TMSv3.SpedX.domain.model.Response.Success
import TMSv3.SpedX.domain.repository.deleteOrderResponse
import TMSv3.SpedX.domain.repository.editOrderResponse


@HiltViewModel
class EditOrderViewModel @Inject constructor(
    private val repoOrder: OrderRepository
): ViewModel() {

    var orderDetailResponse by mutableStateOf<Response<Order?>>(Loading)

    var editOrderResponse by mutableStateOf<editOrderResponse>(Success(false))
        private set


    var deleteOrderResponse by mutableStateOf<deleteOrderResponse>(Success(false))
        private set



    fun getOrderDetails(orderID: String) = viewModelScope.launch {
        Log.d(Constants.TAG, "pickOrderViewModelgetOrderDetails-------------------: $orderID")
        orderDetailResponse = repoOrder.getOrderDetails(orderID)
    }


    fun editOrderDetails(firestoreID: String, orderTitle: String, orderID: String, position: String, finalDest: String,
                         startDest: String, cargoName: String, cargoWeight: Int,
                         driverID: String, cmrID: String, createAt: String) = viewModelScope.launch {
        editOrderResponse = Loading
        editOrderResponse = repoOrder.editOrder(firestoreID, orderTitle, orderID, position, finalDest, startDest, cargoName, cargoWeight, driverID, cmrID, createAt)

    }



    fun deleteOrder(firestoreID: String, delete: Boolean) = viewModelScope.launch {
        deleteOrderResponse = Loading
        if(delete){deleteOrderResponse = repoOrder.deleteOrder(firestoreID)}
    }


}