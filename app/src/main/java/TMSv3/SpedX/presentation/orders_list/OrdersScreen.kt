package TMSv3.SpedX.presentation.orders_list

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.components.TopBar
import TMSv3.SpedX.core.Constants.ORDERS_SCREEN
import TMSv3.SpedX.presentation.orders_list.components.OrdersContent
import TMSv3.SpedX.presentation.profile.components.ProfileContent
import TMSv3.SpedX.presentation.profile.components.RevokeAccess
import androidx.navigation.NavController
import com.google.firebase.annotations.PreviewApi

@Composable
fun OrdersScreen(
    navController: NavController,
    viewModel: OrdersViewModel = hiltViewModel(),
    navigateBack: () -> Boolean
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
            OrdersContent(padding = padding,)
        },
        scaffoldState = scaffoldState
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}
