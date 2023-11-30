package TMSv3.SpedX.presentation.pick_order

import TMSv3.SpedX.components.TopBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.core.Constants.TAG
import TMSv3.SpedX.presentation.orders_list.OrdersViewModel
import TMSv3.SpedX.presentation.pick_order.components.PickOrderContent
import TMSv3.SpedX.presentation.profile.components.ProfileContent
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

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

//@Preview
//@Composable
//fun PickOrderScreenPreview() {
//
//    PickOrderScreen(
//        navController = rememberNavController(),
//        navigateBack = {true}
//    )
//
//}

