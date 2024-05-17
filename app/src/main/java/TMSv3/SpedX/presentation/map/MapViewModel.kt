package TMSv3.SpedX.presentation.map

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.Vehicle
import TMSv3.SpedX.domain.repository.ASNApi
import TMSv3.SpedX.domain.repository.Api
import TMSv3.SpedX.domain.repository.AuthDataRepository
import android.util.Log
import androidx.compose.runtime.State
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
class MapViewModel @Inject constructor(
    private val repoData: AuthDataRepository
): ViewModel() {

    private val _position = MutableLiveData<Position>()

    val position: LiveData<Position> = _position


    var asnDataResponse by mutableStateOf<Response<ASNdata?>>(Loading)


    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicles: LiveData<List<Vehicle>> = _vehicles


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




    fun getAsnData() = viewModelScope.launch {
        asnDataResponse = repoData.loadASN()
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

}