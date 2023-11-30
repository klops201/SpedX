package TMSv3.SpedX.presentation.orders_list

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.components.TopBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.core.Constants.ORDERS_SCREEN
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
fun OrdersScreen(
    navController: NavController,
    viewModel: OrdersViewModel = hiltViewModel(),
    navigateBack: () -> Boolean,
    navigateToAddOrder: () -> Unit,
    navigateToOrderDetails: (String?) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBar(
                title = ORDERS_SCREEN,
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            OrdersContent(padding = padding,
                orderClicked = {clickedOrderID -> navigateToOrderDetails(clickedOrderID)
                    Log.d(Constants.TAG, "ORDER_ID PASS TO ORDERS_SCREEN------------------------------------------------: $clickedOrderID")})
        },
        scaffoldState = scaffoldState,
        floatingActionButton ={
            FloatingActionButton(
                onClick = { navigateToAddOrder()},
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}
