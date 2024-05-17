package TMSv3.SpedX.presentation.settings.asn

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.GlobalLoginData
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.settings.SettingsViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GetAsnData(
    viewModel: SettingsViewModel = hiltViewModel(),
    AsnContent: @Composable (ASNdata: ASNdata) -> Unit
) {
    when(val getAsnDataResp = viewModel.asnDataResponse) {
        is Loading -> ProgressBar()
        is Success -> getAsnDataResp.data?.let { asnData ->
            AsnContent(asnData)
            Log.d(Constants.TAG, "asnData retrieved successfully(getasnData): $asnData")

        }
        is Failure -> LaunchedEffect(Unit) {
            print(getAsnDataResp.e)
        }
    }
}