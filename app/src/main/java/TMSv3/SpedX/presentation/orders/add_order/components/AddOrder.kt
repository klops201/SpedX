package TMSv3.SpedX.presentation.orders.add_order.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Utils
import TMSv3.SpedX.presentation.orders.add_order.AddOrderViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.domain.model.Response.*

@Composable
fun AddOrder(
    viewModel: AddOrderViewModel = hiltViewModel(),
    openOrders: () -> Unit,
) {
    when(val addOResponse = viewModel.addOrderResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            openOrders()
        }
        is Failure -> addOResponse.apply {
            LaunchedEffect(e) {
                Utils.print(e)
            }
        }
    }


}