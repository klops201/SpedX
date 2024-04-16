package TMSv3.SpedX.presentation.drivers.driver_add

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.DriverASN
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.Vehicle
import TMSv3.SpedX.domain.repository.ASNApi
import TMSv3.SpedX.domain.repository.DriverRepository
import TMSv3.SpedX.domain.repository.addDriverResponse
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddDriverViewModel @Inject
constructor(
    private val repoDriver: DriverRepository
) : ViewModel() {

    var addDriverResponse by mutableStateOf<addDriverResponse>(Success(false))
        private set


    private val _drivers = MutableLiveData<List<DriverASN>>()
    val drivers: LiveData<List<DriverASN>> = _drivers







    fun addDriverDetails(
        driverName: String,
        driverPhoneNr: Int,
        driverID: String,
        vehicleID: String
    ) = viewModelScope.launch {
        addDriverResponse = Loading
        addDriverResponse =
            repoDriver.addDriver(driverName, driverPhoneNr, driverID, vehicleID)
    }



    fun fetchDrivers() {
        viewModelScope.launch {
            try {
                _drivers.value = ASNApi.autoSatNetService.getDriversList()
                Log.d(
                    Constants.TAG,
                    "po pobraniu listy ziutkow viewModel ${_drivers.value}----------: "
                )

            } catch (e: Exception) {

                Log.d(Constants.TAG, "BŁĄD pobrania ziutkow---------- :${_drivers.value} ")
            }
        }
    }






}