package TMSv3.SpedX.presentation.orders.edit_order.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.orders.edit_order.EditOrderViewModel
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun AddCmrOrder(
    viewModel: EditOrderViewModel = hiltViewModel(),
    addCmrToFirestore: (downloaduri: Uri) -> Unit
){
    when( val addCmrToStorage = viewModel.addCmrFirebaseResponse){
        is Loading -> ProgressBar()
        is Success -> addCmrToStorage.data?.let {downloadUri ->
            LaunchedEffect(downloadUri){
                addCmrToFirestore(downloadUri)
            }
        }
        is Failure -> print(addCmrToStorage.e)
    }
}