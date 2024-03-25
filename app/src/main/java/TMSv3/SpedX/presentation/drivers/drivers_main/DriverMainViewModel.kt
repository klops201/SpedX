package TMSv3.SpedX.presentation.drivers.drivers_main

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.Vehicle
import TMSv3.SpedX.domain.repository.ASNApi
import TMSv3.SpedX.domain.repository.DeleteDriverResponse
import TMSv3.SpedX.domain.repository.DriverRepository
import TMSv3.SpedX.domain.repository.PositionRepository
import TMSv3.SpedX.domain.repository.SavePositionResponse
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DriverMainViewModel @Inject constructor(
    private val driverRepo: DriverRepository,
    private val positionRepo: PositionRepository
) : ViewModel() {

    var driversListResponse by mutableStateOf<Response<List<Driver>>>(Success(emptyList()))


    var savePositionResponse by mutableStateOf<SavePositionResponse>(Success(false))
        private set


    var DeleteDriverResponse by mutableStateOf<DeleteDriverResponse>(Success(false))
        private set






    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter


    var positionDetailResponse by mutableStateOf<Response<Position?>>(Loading)


    fun getDriverList() = viewModelScope.launch {
        driversListResponse = Loading
        driversListResponse = driverRepo.getDriversList()
    }


    private val _position = MutableLiveData<Position>()

    val position: LiveData<Position> = _position


    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicles: LiveData<List<Vehicle>> = _vehicles


    fun incrementCounter() {
        _counter.value = (_counter.value ?: 0) + 1
        Log.d(Constants.TAG, "++++ zwiększenie licznika---------- :$counter ")

    }


    fun fetchVehicles() {
        viewModelScope.launch {
            try {
                _vehicles.value = ASNApi.autoSatNetService.getVehiclesList()
                Log.d(
                    Constants.TAG,
                    "po pobraniu listy fur viewModel ${_vehicles.value}----------: "
                )

            } catch (e: Exception) {

                Log.d(Constants.TAG, "BŁĄD pobrania fur---------- :${_vehicles.value} ")
            }
        }
    }


    suspend fun fetchPosition(vehicleID: String): Position? {

        try {
            Log.d(Constants.TAG, "przed pobraniem pozycji viewModel $vehicleID ----------: ")

            _position.value = ASNApi.autoSatNetService.getActualPosition(vehicleID)
            Log.d(
                Constants.TAG,
                "po pobraniu pozycji viewModel $vehicleID----------: ${_position.value}"
            )
            return _position.value

        } catch (e: Exception) {
            Log.d(Constants.TAG, "BŁĄD fetchPosition---------- :${_position.value} ")
            return null
        }

    }


    fun fetchPositionWithCallback(vehicleID: String, callback: (Position?) -> Unit) {
        viewModelScope.launch {
            val position = fetchPosition(vehicleID)
            callback(position)
        }
    }


    fun deleteDriver(firebaseID: String) = viewModelScope.launch {
        DeleteDriverResponse = Loading
        DeleteDriverResponse = driverRepo.deleteDriver(firebaseID)
    }





    fun savePosition(position: Position, vehicleID: String) = viewModelScope.launch {
        savePositionResponse = Loading
        savePositionResponse = positionRepo.savePosition(position, vehicleID)
    }


    fun loadPosition(vehicleID: String) = viewModelScope.launch {
        positionDetailResponse = positionRepo.getPosition(vehicleID)
    }


}