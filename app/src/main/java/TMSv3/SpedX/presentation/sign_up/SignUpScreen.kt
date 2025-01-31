package TMSv3.SpedX.presentation.sign_up

import TMSv3.SpedX.core.Constants
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.core.Constants.VERIFY_EMAIL_MESSAGE
import TMSv3.SpedX.core.Utils.Companion.showMessage
import TMSv3.SpedX.presentation.sign_up.components.SendEmailVerification
//import TMSv3.SpedX.presentation.sign_up.components.SignUp
import TMSv3.SpedX.presentation.sign_up.components.SignUpContent
import TMSv3.SpedX.presentation.sign_up.components.SignUpTopBar
import TMSv3.SpedX.sign_up.components.SignUp
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
@ExperimentalComposeUiApi
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            SignUpTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            SignUpContent(
                padding = padding,
                signUp = {name,  email, password ->
                    viewModel.signUpWithEmailAndPassword(email, password)
                    viewModel._name.value = name
                },
                navigateBack = navigateBack
            )
        }
    )

    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            showMessage(context, VERIFY_EMAIL_MESSAGE)
        },
        createUserFirestore = {
            viewModel.createFirebaseUser(viewModel._name.value)
        }
    )

    SendEmailVerification()
}