package TMSv3.SpedX.presentation.settings.asn

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.presentation.profile.WebViewModel
import TMSv3.SpedX.presentation.settings.SettingsViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun GetAsnDataWeb(
    viewModel: WebViewModel = hiltViewModel(),
    goASN: @Composable (ASNdata: ASNdata) -> Unit
) {
    when(val getAsnDataResp = viewModel.asnDataResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> getAsnDataResp.data?.let { asnData ->
            goASN(asnData)
            Log.d(Constants.TAG, "asnData retrieved successfully(getasnData): $asnData")

        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(getAsnDataResp.e)
        }
    }
}