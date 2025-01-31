package TMSv3.SpedX.presentation.sign_in.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import TMSv3.SpedX.core.Constants.SIGN_IN_SCREEN

@Composable
fun SignInTopBar() {
    TopAppBar (
        title = {
            Text(
                text = SIGN_IN_SCREEN
            )
        }
    )
}