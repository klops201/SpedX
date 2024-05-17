package TMSv3.SpedX.presentation.map.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.drivers.drivers_main.DriverMainViewModel
import TMSv3.SpedX.presentation.map.MapViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun GetASNDataMap(
    viewModel: MapViewModel = hiltViewModel(),
    MapScreen: @Composable (ASNdata: ASNdata) -> Unit
) {
    when(val getAsnDataResp = viewModel.asnDataResponse) {
        is Loading -> ProgressBar()
        is Success -> getAsnDataResp.data?.let { asnData ->
            MapScreen(asnData)
            Log.d(Constants.TAG, "asnData retrieved successfully(getasnData): $asnData")

        }

        is Failure -> LaunchedEffect(Unit) {
            print(getAsnDataResp.e)
        }
    }
}