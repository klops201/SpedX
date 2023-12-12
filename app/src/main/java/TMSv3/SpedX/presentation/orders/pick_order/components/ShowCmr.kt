package TMSv3.SpedX.presentation.orders.pick_order.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.orders.edit_order.EditOrderViewModel
import TMSv3.SpedX.presentation.orders.pick_order.PickOrderViewModel
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ShowCmr(
    viewModel: PickOrderViewModel = hiltViewModel(),
    loadCmr: @Composable (imageUri: String) -> Unit
){
    when( val getCmrResp = viewModel.getCmrOrderResponse){
        is Loading -> ProgressBar()
        is Success -> getCmrResp.data?.let {imageUri ->
            loadCmr(imageUri)
        }
        is Failure -> print(getCmrResp.e)
    }



}