package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Response
import android.net.Uri




typealias addCmrFirebaseResponse = Response<Uri?>
typealias addCmrToOrderResponse = Response<Boolean>
typealias getCmrFromOrderResponse = Response<String?>

interface CmrRepository {

    suspend fun addCmrFirebase(imageUri: Uri, orderID: String): addCmrFirebaseResponse

    suspend fun addCmrToOrder(downloadUri: Uri, orderID: String): addCmrToOrderResponse

    suspend fun getCmrFromOrder( orderID: String): getCmrFromOrderResponse
}
