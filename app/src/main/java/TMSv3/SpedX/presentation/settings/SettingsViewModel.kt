package TMSv3.SpedX.presentation.settings

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.GlobalLoginData
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.SentForm
import TMSv3.SpedX.domain.repository.AuthDataRepository
import TMSv3.SpedX.domain.repository.CmrRepository
import TMSv3.SpedX.domain.repository.OrderRepository
import TMSv3.SpedX.domain.repository.UpdateASNResponse
import TMSv3.SpedX.domain.repository.UpdateETollResponse
import TMSv3.SpedX.domain.repository.UpdatePuescResponse
import TMSv3.SpedX.domain.repository.UpdateVinietResponse
import android.net.Uri
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
class SettingsViewModel @Inject constructor(
    private val repoData: AuthDataRepository
) : ViewModel() {

    var updateVinietResponse by mutableStateOf<UpdateVinietResponse>(Success(false))
        private set

    var globalDataResponse by mutableStateOf<Response<GlobalLoginData?>>(Loading)

    var updatePuescResponse by mutableStateOf<UpdatePuescResponse>(Success(false))
        private set

    var puescDataResponse by mutableStateOf<Response<SentForm?>>(Loading)

    var updateAsnResponse by mutableStateOf<UpdateASNResponse>(Success(false))
        private set

    var asnDataResponse by mutableStateOf<Response<ASNdata?>>(Loading)

    var updateEtollResponse by mutableStateOf<UpdateETollResponse>(Success(false))
        private set
//
//    var etollDataResponse by mutableStateOf<Response<GlobalLoginData?>>(Loading)

    fun updateViniet(login: String, password: String) = viewModelScope.launch {
        updateVinietResponse = Loading
        updateVinietResponse = repoData.updateViniet(login, password)
    }


    fun getVinietData() = viewModelScope.launch {
        globalDataResponse = repoData.loadViniet()
    }



    fun updatePuesc(log: String, gate: String, NIP: String, address: String, borderPosition: String, borderRoad: String,
                    cargoPermit: String, companyName: String, emailConfirmation: String, gpsID: String, trailerID: String,
                    truckID: String) = viewModelScope.launch {
        updatePuescResponse = Loading
        updatePuescResponse = repoData.updatePuesc(log, gate, NIP, address, borderPosition, borderRoad,
            cargoPermit, companyName, emailConfirmation, gpsID, trailerID,
            truckID)
    }

    fun getPuescData() = viewModelScope.launch {
        puescDataResponse = repoData.loadPUESC()
    }

    fun updateAsn(customer: String, user: String, password: String) = viewModelScope.launch {
        updateAsnResponse = Loading
        updateAsnResponse = repoData.updateASN(customer, user, password)
    }

    fun getAsnData() = viewModelScope.launch {
        asnDataResponse = repoData.loadASN()
    }

    fun updateEtoll(login: String, password: String) = viewModelScope.launch {
        updateEtollResponse = Loading
        updateEtollResponse = repoData.updateEToll(login, password)
    }

    fun getEtollData() = viewModelScope.launch {
        globalDataResponse = repoData.loadEToll()
    }





}