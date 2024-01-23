package TMSv3.SpedX.presentation.orders.pick_order

import TMSv3.SpedX.R
import TMSv3.SpedX.presentation.orders.pick_order.components.PickOrderContent
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController

@Composable
fun PickOrderScreen(
    orderId: String,
    navController: NavController,

    navigateBack: () -> Boolean,
    navigateToEditOrder: (String?) -> Unit
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
        scaffoldState = scaffoldState,
        floatingActionButton ={
            FloatingActionButton(
                onClick = { navigateToEditOrder(orderId)},
                backgroundColor = colorResource(id = R.color.colorTest)
            ) {
                Icon(Icons.Filled.Edit, "Floating action button.", tint = Color.White)
            }
        }
    )



}
