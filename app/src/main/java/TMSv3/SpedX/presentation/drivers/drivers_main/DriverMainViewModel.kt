package TMSv3.SpedX.presentation.drivers.drivers_main

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.Vehicle
import TMSv3.SpedX.domain.repository.ASNApi
import TMSv3.SpedX.domain.repository.Api
import TMSv3.SpedX.domain.repository.AuthDataRepository
import TMSv3.SpedX.domain.repository.DriverRepository
import TMSv3.SpedX.domain.repository.PositionRepository
import TMSv3.SpedX.domain.repository.SavePositionResponse
import TMSv3.SpedX.domain.repository.deleteDriverResponse
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
    private val positionRepo: PositionRepository,
    private val repoData: AuthDataRepository
) : ViewModel() {

    var driversListResponse by mutableStateOf<Response<List<Driver>>>(Success(emptyList()))


    var savePositionResponse by mutableStateOf<SavePositionResponse>(Success(false))
        private set

    var asnDataResponse by mutableStateOf<Response<ASNdata?>>(Loading)



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


    fun fetchVehicles(user: String, customer: String, pass: String,) {
        viewModelScope.launch {
            try {
                val token = Api.generateToken(user, pass)
                _vehicles.value = ASNApi.autoSatNetService.getVehiclesList(user, customer, token)
                Log.d(
                    Constants.TAG,
                    "po pobraniu listy fur viewModel ${_vehicles.value}----------: "
                )

            } catch (e: Exception) {

                Log.d(Constants.TAG, "BŁĄD pobrania fur---------- :${_vehicles.value} ")
            }
        }
    }


    suspend fun fetchPosition(user: String, customer: String, pass: String, vehicleID: String): Position? {

        try {
            val token = Api.generateToken(user, pass)
            Log.d(Constants.TAG, "przed pobraniem pozycji viewModel $vehicleID ----------: ")

            _position.value = ASNApi.autoSatNetService.getActualPosition(user, customer, vehicleID, token)
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


    fun fetchPositionWithCallback(user: String, customer: String, pass: String, vehicleID: String, callback: (Position?) -> Unit) {
        viewModelScope.launch {
            val position = fetchPosition(user, customer, pass, vehicleID)
            callback(position)
        }
    }





    fun savePosition(position: Position, vehicleID: String) = viewModelScope.launch {
        savePositionResponse = Loading
        savePositionResponse = positionRepo.savePosition(position, vehicleID)
    }


    fun loadPosition(vehicleID: String) = viewModelScope.launch {
        positionDetailResponse = positionRepo.getPosition(vehicleID)
    }


    fun getAsnData() = viewModelScope.launch {
        asnDataResponse = repoData.loadASN()
    }





}