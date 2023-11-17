package TMSv3.SpedX.presentation.verify_email

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.components.TopBar
import TMSv3.SpedX.core.Constants.EMAIL_NOT_VERIFIED_MESSAGE
import TMSv3.SpedX.core.Constants.VERIFY_EMAIL_SCREEN
import TMSv3.SpedX.core.Utils.Companion.showMessage
import TMSv3.SpedX.presentation.profile.ProfileViewModel
import TMSv3.SpedX.presentation.profile.components.RevokeAccess
import TMSv3.SpedX.presentation.verify_email.components.ReloadUser
import TMSv3.SpedX.presentation.verify_email.components.VerifyEmailContent

@Composable
fun VerifyEmailScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                title = VERIFY_EMAIL_SCREEN,
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            VerifyEmailContent(
                padding = padding,
                reloadUser = {
                    viewModel.reloadUser()
                }
            )
        },
        scaffoldState = scaffoldState
    )

    ReloadUser(
        navigateToProfileScreen = {
            if (viewModel.isEmailVerified) {
                navigateToProfileScreen()
            } else {
                showMessage(context, EMAIL_NOT_VERIFIED_MESSAGE)
            }
        }
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}