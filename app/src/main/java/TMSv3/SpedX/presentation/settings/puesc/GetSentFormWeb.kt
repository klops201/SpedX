package TMSv3.SpedX.presentation.settings.puesc

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Response
import TMSv3.SpedX.domain.model.SentForm
import TMSv3.SpedX.presentation.profile.WebViewModel
import TMSv3.SpedX.presentation.settings.SettingsViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GetSentFormWeb(
    viewModel: WebViewModel = hiltViewModel(),
    goPUESC: @Composable (SentForm: SentForm) -> Unit
) {
    when(val getSentFormResp = viewModel.puescDataResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> getSentFormResp.data?.let { sentData ->
            goPUESC(sentData)

        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(getSentFormResp.e)
        }
    }
}