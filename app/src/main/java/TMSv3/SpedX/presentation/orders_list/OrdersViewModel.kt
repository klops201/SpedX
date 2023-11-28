package TMSv3.SpedX.presentation.orders_list

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import TMSv3.SpedX.domain.model.Response.Loading
import TMSv3.SpedX.domain.model.Response.Success
import TMSv3.SpedX.domain.repository.AuthRepository
import TMSv3.SpedX.domain.repository.OrderRepository
import TMSv3.SpedX.domain.repository.ReloadUserResponse
import TMSv3.SpedX.domain.repository.RevokeAccessResponse
import android.util.Log
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val repo: AuthRepository,
    private val repoOrder: OrderRepository
): ViewModel() {
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(Success(false))
        private set

    var ordersListResponse by mutableStateOf<Response<List<Order>>>(Success(emptyList()))

    init {
        var _ordersList = listOf<Order>()
        getOrdersListFirestore()
        Log.d(Constants.TAG, "ViewModel initialized with orders viewModel: $_ordersList")
    }

    var _ordersList = listOf<Order>()



    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Loading
        reloadUserResponse = repo.reloadFirebaseUser()
    }


   fun getOrdersListFirestore() = viewModelScope.launch {
       ordersListResponse = Loading
       ordersListResponse = repoOrder.getOrdersList()
   }



    fun signOut() = repo.signOut()

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = repo.revokeAccess()
    }

}
