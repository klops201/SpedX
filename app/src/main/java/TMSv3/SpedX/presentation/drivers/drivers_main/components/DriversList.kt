package TMSv3.SpedX.presentation.drivers.drivers_main.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.drivers.drivers_main.DriverMainViewModel
import TMSv3.SpedX.presentation.orders.orders_list.OrdersViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GetDrivers(
    viewModel: DriverMainViewModel = hiltViewModel(),
    DriversMainContent: @Composable (drivers: List<Driver>) -> Unit
) {
    when(val getDriversResp = viewModel.driversListResponse) {
        is Loading -> ProgressBar()
        is Success -> getDriversResp.data?.let { drivers ->
            DriversMainContent(drivers)
            Log.d(Constants.TAG, "Drivers retrieved successfully(): $drivers")

        }
        is Failure -> LaunchedEffect(Unit) {
            print(getDriversResp.e)
        }
    }
}