package TMSv3.SpedX.presentation.profile

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.GlobalLoginData
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.SentForm
import TMSv3.SpedX.domain.repository.AuthDataRepository
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
class WebViewModel @Inject constructor(
    private val repoData: AuthDataRepository
) : ViewModel() {


    var globalDataResponse by mutableStateOf<Response<GlobalLoginData?>>(Response.Loading)

    var puescDataResponse by mutableStateOf<Response<SentForm?>>(Response.Loading)

    var asnDataResponse by mutableStateOf<Response<ASNdata?>>(Response.Loading)


    fun getVinietData() = viewModelScope.launch {
        globalDataResponse = repoData.loadViniet()
    }


    fun getPuescData() = viewModelScope.launch {
        puescDataResponse = repoData.loadPUESC()
    }

    fun getAsnData() = viewModelScope.launch {
        Log.d(Constants.TAG, "uruchomienie --- getAsnData ")

        asnDataResponse = repoData.loadASN()
    }

    fun getEtollData() = viewModelScope.launch {
        globalDataResponse = repoData.loadEToll()
    }





}