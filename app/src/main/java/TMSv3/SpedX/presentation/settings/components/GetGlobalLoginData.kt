package TMSv3.SpedX.presentation.settings.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.GlobalLoginData
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.orders.edit_order.EditOrderViewModel
import TMSv3.SpedX.presentation.settings.SettingsViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GetGlbData(
    viewModel: SettingsViewModel = hiltViewModel(),
    VinietContent: @Composable (GlobalLoginData: GlobalLoginData) -> Unit
) {
    when(val getGlbDataResp = viewModel.globalDataResponse) {
        is Loading -> ProgressBar()
        is Success -> getGlbDataResp.data?.let { glbData ->
            VinietContent(glbData)
            Log.d(Constants.TAG, "GlbData retrieved successfully(getGlbData): $glbData")

        }
        is Failure -> LaunchedEffect(Unit) {
            print(getGlbDataResp.e)
        }
    }
}