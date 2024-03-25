package TMSv3.SpedX.presentation.map

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.Response.*
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



     fun fetchPosition(){
        viewModelScope.launch {
            try {
                Log.d(Constants.TAG, "przed pobraniem pozycji ----------: ")

                _position.value = ASNApi.autoSatNetService.getActualPosition("v1566")
                Log.d(Constants.TAG, "po pobraniu pozycji ----------: ${_position.value}")

            }  catch (e: Exception) {

            }
        }
    }

}