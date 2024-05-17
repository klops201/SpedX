package TMSv3.SpedX.presentation.profile

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
import TMSv3.SpedX.domain.model.Vehicle
import TMSv3.SpedX.domain.repository.ASNApi
import TMSv3.SpedX.domain.repository.AuthRepository
import TMSv3.SpedX.domain.repository.MainRepository
import TMSv3.SpedX.domain.repository.ReloadUserResponse
import TMSv3.SpedX.domain.repository.RevokeAccessResponse
import TMSv3.SpedX.domain.repository.CreateUserResponse
import TMSv3.SpedX.domain.repository.OrderRepository
import TMSv3.SpedX.domain.repository.UserNameResponse
import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: AuthRepository,
    private val repoM: MainRepository,
    private val repoOrder: OrderRepository
): ViewModel() {
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(Success(false))
        private set
    var createUserAppResponse by mutableStateOf<UserNameResponse>(Loading)
        private set


    var ordersListResponse by mutableStateOf<Response<List<Order>>>(Success(emptyList()))

    val _nameFirebase = mutableStateOf("")
    val nameFirebase: State<String> = _nameFirebase







    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Loading
        reloadUserResponse = repo.reloadFirebaseUser()
    }

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    fun signOut() = repo.signOut()

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = repo.revokeAccess()
    }

    //val userEmail = MutableStateFlow("")
    //val userEmail get() = repo.currentUser?.email?: ""

    fun getUserName() = viewModelScope.launch {
        createUserAppResponse = repoM.getUserName()
    }



    fun getUndoneOrdersList() = viewModelScope.launch {
        ordersListResponse = Loading
        ordersListResponse = repoOrder.getUndoneOrders()
    }


}
