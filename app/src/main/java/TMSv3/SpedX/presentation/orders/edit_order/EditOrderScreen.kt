package TMSv3.SpedX.presentation.orders.edit_order

import TMSv3.SpedX.presentation.orders.edit_order.components.EditOrderContent
import TMSv3.SpedX.presentation.orders.pick_order.components.PickOrderContent
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@Composable
fun EditOrderScreen(
    orderId: String,
    navController: NavController,
    viewModel: EditOrderViewModel = hiltViewModel(),
    navigateBack: () -> Boolean,
    navigateToOrderDetails: () -> Unit,
    navigateToOrders: () -> Unit
){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()




    Scaffold(
        content = { padding ->
            EditOrderContent(
                padding = padding,
                orderID = orderId,
                editOrder = {orderTitle, orderID, position, finalDest,
                             startDest, cargoName, cargoWeight, driverID, cmrID,
                             createAt -> viewModel.editOrderDetails(orderId, orderTitle, orderID, position, finalDest,
                    startDest, cargoName, cargoWeight, driverID, cmrID,
                    createAt)},
                navigateToOrderDetails = navigateToOrderDetails
            )
        },
        scaffoldState = scaffoldState,
        floatingActionButton ={
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                            message = "Usuwanie zlecenia",
                            duration = SnackbarDuration.Short,
                            actionLabel = "Anuluj"
                        )
                        when (snackbarResult) {
                            SnackbarResult.Dismissed -> {
                                viewModel.deleteOrder(orderId, true)
                                navigateToOrders()
                            }
                            SnackbarResult.ActionPerformed -> viewModel.deleteOrder(orderId, false)
                        }
                    }
                },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    )



}