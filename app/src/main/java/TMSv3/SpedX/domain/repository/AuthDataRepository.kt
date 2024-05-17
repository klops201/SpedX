package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.GlobalLoginData
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.SentForm


typealias UpdatePuescResponse = Response<Boolean>
typealias UpdateETollResponse = Response<Boolean>
typealias UpdateVinietResponse = Response<Boolean>
typealias UpdateASNResponse = Response<Boolean>
typealias LoadPUESCResponse = Response<SentForm?>
typealias LoadETollResponse = Response<GlobalLoginData?>
typealias LoadVinietResponse = Response<GlobalLoginData?>
typealias LoadASNResponse = Response<ASNdata?>
typealias LoadAsnApiResponse = Response<String?>


interface AuthDataRepository {

    suspend fun updatePuesc(log: String, gate: String, NIP: String, address: String, borderPosition: String, borderRoad: String,
                            cargoPermit: String, companyName: String, emailConfirmation: String, gpsID: String, trailerID: String,
                            truckID: String): UpdatePuescResponse
    suspend fun updateEToll(log: String, gate: String): UpdateETollResponse
    suspend fun updateViniet(log: String, gate: String): UpdateVinietResponse
    suspend fun updateASN(customer: String, user: String, gate: String): UpdateASNResponse

    suspend fun loadPUESC(): LoadPUESCResponse
    suspend fun loadEToll(): LoadETollResponse
    suspend fun loadViniet(): LoadVinietResponse
    suspend fun loadASN(): LoadASNResponse
    suspend fun loadAsnApi(): LoadAsnApiResponse


}