package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Vehicle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


private const val BASE_URL = "https://api.autosatnet.eu/administrator/ostrokolowicz/"

private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

interface Api {
    @GET("positions/{version}")
    suspend fun getActualPosition(
        @Path("version") version: String,
        @Query("token") token: String = generateToken("administrator", "Uslugi1!")
    ): Position



    @GET("vehicles")
    suspend fun getVehiclesList(
        @Query("token") token: String = generateToken("administrator", "Uslugi1!")
    ): List<Vehicle>



    companion object {
        fun generateToken(login: String, password: String): String {
            val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val hashedPassword = hashPassword(password).substring(0, 19)
            return hashToken("$login:$hashedPassword:$todayDate").substring(0, 32)
        }

        private fun hashPassword(password: String): String {
            val md = MessageDigest.getInstance("MD5")
            val hashedBytes = md.digest(password.toByteArray())
            return hashedBytes.joinToString("") { "%02x".format(it) }
        }

        private fun hashToken(token: String): String {
            val md = MessageDigest.getInstance("MD5")
            val hashedBytes = md.digest(token.toByteArray())
            return hashedBytes.joinToString("") { "%02x".format(it) }
        }
    }
}


object ASNApi {
    val autoSatNetService: Api by lazy {
        retrofit.create(Api::class.java)
    }

}
