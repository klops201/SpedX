package TMSv3.SpedX.presentation.profile.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants.ACCESS_REVOKED_MESSAGE
import TMSv3.SpedX.core.Constants.REVOKE_ACCESS_MESSAGE
import TMSv3.SpedX.core.Constants.SENSITIVE_OPERATION_MESSAGE
import TMSv3.SpedX.core.Constants.SIGN_OUT_ITEM
import TMSv3.SpedX.core.Utils.Companion.print
import TMSv3.SpedX.core.Utils.Companion.showMessage
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.profile.ProfileViewModel

@Composable
fun RevokeAccess(
    viewModel: ProfileViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit,
) {
    val context = LocalContext.current

    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT_ITEM
        )
        if (result == SnackbarResult.ActionPerformed) {
            signOut()
        }
    }

    when(val revokeAccessResponse = viewModel.revokeAccessResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isAccessRevoked = revokeAccessResponse.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked) {
                    showMessage(context, ACCESS_REVOKED_MESSAGE)
                }
            }
        }
        is Failure -> revokeAccessResponse.apply {
            LaunchedEffect(e) {
                print(e)
                if (e.message == SENSITIVE_OPERATION_MESSAGE) {
                    showRevokeAccessMessage()
                }
            }
        }
    }
}