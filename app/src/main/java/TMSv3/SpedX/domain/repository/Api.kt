package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Position
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "https://api.autosatnet.eu/administrator/ostrokolowicz/"

private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

interface Api {
    @GET("positions/v1566?token=09176dca1d84239b1e4b8b779ad7a9a8")
    suspend fun getActualPosition(): Position


}


object ASNApi {
    val autoSatNetService: Api by lazy {
        retrofit.create(Api::class.java)
    }

}
