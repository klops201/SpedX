package TMSv3.SpedX.presentation.add_order

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.components.TopBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.core.Constants.ORDERS_SCREEN
import TMSv3.SpedX.presentation.add_order.components.AddOrder
import TMSv3.SpedX.presentation.add_order.components.AddOrderContent
import TMSv3.SpedX.presentation.orders_list.components.OrdersContent
import TMSv3.SpedX.presentation.profile.components.ProfileContent
import TMSv3.SpedX.presentation.profile.components.RevokeAccess
import android.util.Log
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.navigation.NavController
import com.google.firebase.annotations.PreviewApi

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
                navigateToOrdersScr = navigateToOrders)
        },
        scaffoldState = scaffoldState,
    )



}
