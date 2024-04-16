package TMSv3.SpedX.presentation.drivers.driver_add

import TMSv3.SpedX.presentation.drivers.driver_add.components.AddDriverContent
import TMSv3.SpedX.presentation.orders.add_order.components.AddOrderContent
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun AddDriverScreen(
    viewModel: AddDriverViewModel = hiltViewModel(),
    navigateToDrivers: () -> Unit,
    navigateBack: () -> Unit
) {


    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        content = { padding ->
            AddDriverContent(padding = padding,
                addDriver = { driverName, driverPhoneNr, driverID, vehicleID ->
                    viewModel.addDriverDetails(
                        driverName,
                        driverPhoneNr,
                        driverID,
                        vehicleID
                    )
                },
                navigateToDrivers = navigateToDrivers,
                showSnackBar = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Uzupełenij pola!",
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                goBack = { navigateBack() })
        },
        scaffoldState = scaffoldState,
    )


}