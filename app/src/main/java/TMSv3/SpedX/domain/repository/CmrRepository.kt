package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Response
import android.net.Uri




typealias addCmrFirebaseResponse = Response<Uri?>
typealias addImageToOrderResponse = Response<Boolean>
typealias getImageFromOrderResponse = Response<String>

interface CmrRepository {

    suspend fun addCmrFirebase(imageUri: Uri): addCmrFirebaseResponse

    suspend fun addImageToOrder(downloadUri: Uri, orderID: String): addImageToOrderResponse

    suspend fun getImageFromOrder( orderID: String): getImageFromOrderResponse
}
