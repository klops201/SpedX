package TMSv3.SpedX.presentation.drivers.driver_edit

import TMSv3.SpedX.R
import TMSv3.SpedX.components.TopBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.drivers.driver_edit.components.EditDriverContent
import TMSv3.SpedX.presentation.orders.orders_list.components.OrdersContent
import android.util.Log
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EditDriverScreen(
    driverIDFB: String,
    navigateBack: () -> Unit,
    navigateToDrivers: () -> Unit,
    viewModel: EditDriverViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        content = { padding ->
            EditDriverContent(padding = padding,
                goBack = { navigateBack() },
                driverID = driverIDFB,
                editDriver = {driverName, driverPhoneNr, driverID, vehicleID -> viewModel.editDriverDetails(driverIDFB, driverName, driverPhoneNr, driverID, vehicleID)},
                navigateToDrivers = navigateToDrivers,
                scaffoldST = scaffoldState)
        },
        scaffoldState = scaffoldState
    )


}