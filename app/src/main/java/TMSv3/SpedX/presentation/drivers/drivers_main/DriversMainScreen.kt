package TMSv3.SpedX.presentation.drivers.drivers_main

import TMSv3.SpedX.R
import TMSv3.SpedX.presentation.drivers.drivers_main.components.DriversMainContent
import TMSv3.SpedX.presentation.orders.pick_order.components.PickOrderContent
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun DriversMainScreen(
    navigateBack: () -> Boolean,
    viewModel: DriverMainViewModel = hiltViewModel(),
    navigateToDriverEditScr: (String?) -> Unit
) {


    val scaffoldState = rememberScaffoldState()
    Scaffold(
        content = { padding ->
            DriversMainContent(padding,
                navigateEditDriverScreen = {clickedDriver -> navigateToDriverEditScr(clickedDriver)})
        },
        scaffoldState = scaffoldState,
        floatingActionButton ={
            FloatingActionButton(
                onClick = {viewModel.incrementCounter()},
                backgroundColor = colorResource(id = R.color.colorTest)
            ) {
                Icon(Icons.Filled.Refresh, "", tint = Color.White)
            }
        }
    )
}