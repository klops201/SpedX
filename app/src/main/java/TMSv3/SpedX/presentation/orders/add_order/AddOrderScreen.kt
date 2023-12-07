package TMSv3.SpedX.presentation.orders.add_order

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.presentation.orders.add_order.components.AddOrderContent
import androidx.compose.material.SnackbarDuration
import kotlinx.coroutines.launch

@Composable
fun AddOrderScreen(
    viewModel: AddOrderViewModel = hiltViewModel(),
    navigateToOrders: () -> Unit,
    navigateBack: () -> Boolean
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        content = { padding ->
            AddOrderContent(padding = padding,
                addOrder = {orderTitle, orderID, position, finalDest,
                    startDest, cargoName, cargoWeight, driverID, cmrID,
                    createAt -> viewModel.addOrderFirebase(orderTitle, orderID, position, finalDest,
                    startDest, cargoName, cargoWeight, driverID, cmrID,
                    createAt)},
                navigateToOrdersScr = navigateToOrders,
                showSnackBar = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Uzupełenij pola!",
                            duration = SnackbarDuration.Short
                        )
                    }
                })
        },
        scaffoldState = scaffoldState,
    )



}
