package TMSv3.SpedX.presentation.settings.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.GlobalLoginData
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.presentation.profile.WebViewModel
import TMSv3.SpedX.presentation.settings.SettingsViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GetGlobalLoginDataViniet(
    viewModel: WebViewModel = hiltViewModel(),
    goVinjet: @Composable (GlobalLoginData: GlobalLoginData) -> Unit
) {
    when(val getGlbDataResp = viewModel.globalDataResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> getGlbDataResp.data?.let { glbData ->
            goVinjet(glbData)
            Log.d(Constants.TAG, "GlbData retrieved successfully(getGlbData): $glbData")

        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(getGlbDataResp.e)
        }
    }
}