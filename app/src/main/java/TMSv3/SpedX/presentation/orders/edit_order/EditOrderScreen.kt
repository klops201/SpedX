package TMSv3.SpedX.presentation.orders.edit_order

import TMSv3.SpedX.presentation.orders.edit_order.components.EditOrderContent
import TMSv3.SpedX.presentation.orders.pick_order.components.PickOrderContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun EditOrderScreen(
    orderId: String,
    navController: NavController,
    viewModel: EditOrderViewModel = hiltViewModel(),
    navigateBack: () -> Boolean,
    navigateToOrderDetails: () -> Unit,
){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()




    Scaffold(
        content = { padding ->
            EditOrderContent(
                padding = padding,
                orderID = orderId,
                editOrder = {orderTitle, position, finalDest,
                             startDest, cargoName, cargoWeight, driverID, cmrID,
                             createAt -> viewModel.editOrderDetails(orderTitle, orderId, position, finalDest,
                    startDest, cargoName, cargoWeight, driverID, cmrID,
                    createAt)},
                navigateToOrderDetails = navigateToOrderDetails
            )
        },
        scaffoldState = scaffoldState
    )



}
