package TMSv3.SpedX.presentation.drivers.driver_edit

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.repository.CmrRepository
import TMSv3.SpedX.domain.repository.DriverRepository
import TMSv3.SpedX.domain.repository.OrderRepository
import TMSv3.SpedX.domain.repository.deleteDriverResponse
import TMSv3.SpedX.domain.repository.editDriverResponse
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditDriverViewModel @Inject constructor(
    private val repoOrder: OrderRepository,
    private val repoDriver: DriverRepository,

    ) : ViewModel() {

    var driverDetailResponse by mutableStateOf<Response<Driver?>>(Loading)


    var editDriverResponse by mutableStateOf<editDriverResponse>(Success(false))
        private set


    var deleteDriverResponse by mutableStateOf<deleteDriverResponse>(Success(false))
        private set


    fun editDriverDetails(
        firebaseID: String,
        driverName: String,
        driverPhoneNr: Int,
        driverID: String,
        vehicleID: String
    ) = viewModelScope.launch {
        editDriverResponse = Loading
        editDriverResponse =
            repoDriver.editDriver(firebaseID, driverName, driverPhoneNr, driverID, vehicleID)
    }


    fun getDriverDetails(driverID: String) = viewModelScope.launch {
        driverDetailResponse = repoDriver.getDriverInfo(driverID)
    }


    fun deleteDriver(firebaseID: String, delete: Boolean) = viewModelScope.launch {
        deleteDriverResponse = Loading
        if (delete) {
            deleteDriverResponse = repoDriver.deleteDriver(firebaseID)
        }
    }


}