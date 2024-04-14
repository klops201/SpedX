package TMSv3.SpedX.presentation.drivers.driver_edit.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.drivers.driver_edit.EditDriverViewModel
import TMSv3.SpedX.presentation.orders.edit_order.EditOrderViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun GetDriver(
    viewModel: EditDriverViewModel = hiltViewModel(),
    EditDriverContent: @Composable (Driver: Driver) -> Unit
) {
    when(val getDriverResp = viewModel.driverDetailResponse) {
        is Loading -> ProgressBar()
        is Success -> getDriverResp.data?.let { driver ->
            EditDriverContent(driver)
            Log.d(Constants.TAG, "driver retrieved successfully(GetDriver): $driver")

        }
        is Failure -> LaunchedEffect(Unit) {
            print(getDriverResp.e)
        }
    }
}