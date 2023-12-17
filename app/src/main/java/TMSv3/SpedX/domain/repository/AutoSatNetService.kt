package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response
import retrofit2.http.GET

interface AutoSatNetService {
    @GET("positions/v1566?token=09176dca1d84239b1e4b8b779ad7a9a8")
    suspend fun getActualPosition(): Position

}