package TMSv3.SpedX.presentation.orders.pick_order

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.Response.Success
import TMSv3.SpedX.domain.repository.OrderRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import TMSv3.SpedX.domain.model.Response.Loading
import TMSv3.SpedX.domain.repository.CmrRepository
import TMSv3.SpedX.domain.repository.getCmrFromOrderResponse
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PickOrderViewModel @Inject constructor(
    private val repoOrder: OrderRepository,
    private val repoCmr: CmrRepository,
): ViewModel()  {
    init {
        Log.d(Constants.TAG, "pickOrderViewModelLoad-------------------)")
    }
    var orderDetailResponse by mutableStateOf<Response<Order?>>(Loading)


    var getCmrOrderResponse by mutableStateOf<getCmrFromOrderResponse>(Success(null))
        private set




    fun getOrderDetails(orderID: String) = viewModelScope.launch {
        Log.d(Constants.TAG, "pickOrderViewModelgetOrderDetails-------------------: $orderID")
        orderDetailResponse = repoOrder.getOrderDetails(orderID)
    }


    fun getCmrFromOrder(orderID: String) = viewModelScope.launch {
        getCmrOrderResponse = Loading
        getCmrOrderResponse = repoCmr.getCmrFromOrder(orderID)
    }



}