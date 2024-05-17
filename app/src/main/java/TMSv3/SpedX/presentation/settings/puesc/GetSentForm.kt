package TMSv3.SpedX.presentation.settings.puesc

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.ASNdata
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.model.SentForm
import TMSv3.SpedX.presentation.settings.SettingsViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GetSentForm(
    viewModel: SettingsViewModel = hiltViewModel(),
    PuescContent: @Composable (SentForm: SentForm) -> Unit
) {
    when(val getSentFormResp = viewModel.puescDataResponse) {
        is Loading -> ProgressBar()
        is Success -> getSentFormResp.data?.let { sentData ->
            PuescContent(sentData)
            Log.d(Constants.TAG, "sentData retrieved successfully(gesentData): $sentData")

        }
        is Failure -> LaunchedEffect(Unit) {
            print(getSentFormResp.e)
        }
    }
}