package TMSv3.SpedX.presentation.orders.edit_order

import TMSv3.SpedX.R
import TMSv3.SpedX.presentation.orders.edit_order.components.AddCmrOrder
import TMSv3.SpedX.presentation.orders.edit_order.components.EditOrderContent
import TMSv3.SpedX.presentation.orders.pick_order.components.PickOrderContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.core.net.toUri
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
    val galleryLauncher =  rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
        imageUri?.let {
            viewModel.addCMR(imageUri, orderId)
        }
    }



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
                navigateToOrderDetails = navigateToOrderDetails,
                uploadCmr =  {galleryLauncher.launch("image/*")}
            )
        },
        scaffoldState = scaffoldState,
        floatingActionButton ={
            FloatingActionButton(
                backgroundColor = colorResource(id = R.color.colorTest),
                onClick = {
                    coroutineScope.launch {
                        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                            message = "Usuwanie zlecenia...",
                            duration = SnackbarDuration.Long,
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
                Icon(Icons.Filled.Delete, "Floating action button.", tint = Color.White)
            }
        }
    )

    AddCmrOrder(addCmrToFirestore = {downloaduri -> viewModel.addCmrToOrder(downloaduri, orderId ) })

}
