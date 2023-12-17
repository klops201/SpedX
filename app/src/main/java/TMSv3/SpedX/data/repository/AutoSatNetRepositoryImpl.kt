//package TMSv3.SpedX.data.repository
//
//import TMSv3.SpedX.core.Constants
//import TMSv3.SpedX.domain.repository.AutoSatNetRepository
//import TMSv3.SpedX.domain.repository.AutoSatNetService
//import TMSv3.SpedX.domain.repository.RetrofitInstance
//import TMSv3.SpedX.domain.repository.getPositionResponse
//import android.util.Log
//
//class AutoSatNetRepositoryImpl(private val autoSatNetService: AutoSatNetService) : AutoSatNetRepository {
//    override suspend fun getPosition(): getPositionResponse
//        {
//            try {
//                return RetrofitInstance.autoSatNetService.getActualPosition()
//                Log.d(Constants.TAG, "przechwycenie pozycji asnImpl")
//            } catch (e: Exception) {
//                throw e
//            }
//        }
//
//}