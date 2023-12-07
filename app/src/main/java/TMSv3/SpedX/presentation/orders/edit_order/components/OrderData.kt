package TMSv3.SpedX.presentation.orders.edit_order.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.presentation.orders.edit_order.EditOrderViewModel
import TMSv3.SpedX.presentation.orders.pick_order.PickOrderViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OrderData(
    viewModel: EditOrderViewModel = hiltViewModel(),
    EditOrderContent: @Composable (Order: Order) -> Unit
) {
    when(val getOrderDetResp = viewModel.orderDetailResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> getOrderDetResp.data?.let { order ->
            EditOrderContent(order)
            Log.d(Constants.TAG, "Order retrieved successfully(PickOrder): $order")

        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(getOrderDetResp.e)
        }
    }
}