package TMSv3.SpedX.presentation.orders.add_order

import TMSv3.SpedX.domain.repository.OrderRepository
import TMSv3.SpedX.domain.repository.addOrderResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import TMSv3.SpedX.domain.model.Response.Loading
import TMSv3.SpedX.domain.model.Response.Success
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class AddOrderViewModel @Inject constructor(
    private val repoOrder: OrderRepository
): ViewModel() {

    var addOrderResponse by mutableStateOf<addOrderResponse>(Success(false))
        private set


    fun addOrderFirebase(orderTitle: String, orderID: String, position: String?, finalDest: String,
                                 startDest: String, cargoName: String, cargoWeight: Int,
                                 driverID: String, cmrID: String?, createAt: String) = viewModelScope.launch {
        addOrderResponse = Loading
        addOrderResponse = repoOrder.addOrder(orderTitle, orderID, position, finalDest,
            startDest, cargoName, cargoWeight, driverID, cmrID,
            createAt)
    }

}