package TMSv3.SpedX.presentation.map

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.Vehicle
import TMSv3.SpedX.domain.repository.ASNApi
import TMSv3.SpedX.domain.repository.Api
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

): ViewModel() {

    private val _position = MutableLiveData<Position>()

    val position: LiveData<Position> = _position





    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicles: LiveData<List<Vehicle>> = _vehicles


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








    fun fetchPosition(vehicleId: String){
        viewModelScope.launch {
            try {

                if(vehicleId != "brak pojazdu"){
                    Log.d(Constants.TAG, "przed pobraniem pozycji ----------: ")

                    _position.value = ASNApi.autoSatNetService.getActualPosition(vehicleId)
                    Log.d(Constants.TAG, "po pobraniu pozycji ----------: ${_position.value}")
                } else {
                    Log.d(Constants.TAG, "Nie wczytano pojazdów")
                }

            }  catch (e: Exception) {

            }
        }
    }

}