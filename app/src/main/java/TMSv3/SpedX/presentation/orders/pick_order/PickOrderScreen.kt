package TMSv3.SpedX.presentation.orders.pick_order

import TMSv3.SpedX.presentation.orders.pick_order.components.PickOrderContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController

@Composable
fun PickOrderScreen(
    orderId: String,
    navController: NavController,

    navigateBack: () -> Boolean,
){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()




    Scaffold(
        content = { padding ->
            PickOrderContent(
                padding = padding,
                orderID = orderId
            )
        },
        scaffoldState = scaffoldState
    )



}
