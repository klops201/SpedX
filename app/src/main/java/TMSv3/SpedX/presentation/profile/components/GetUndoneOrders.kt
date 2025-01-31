package TMSv3.SpedX.presentation.profile.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.profile.ProfileViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun getUndoneOrders(
    viewModel: ProfileViewModel = hiltViewModel(),
    OrdersContent: @Composable (orders: List<Order>) -> Unit
) {
    when(val getOrdersResp = viewModel.ordersListResponse) {
        is Loading -> ProgressBar()
        is Success -> getOrdersResp.data?.let { orders ->
            OrdersContent(orders)
            Log.d(Constants.TAG, "Orders retrieved successfully(getOrdersFunct): $orders")

        }
        is Failure -> LaunchedEffect(Unit) {
            print(getOrdersResp.e)
        }
    }
}