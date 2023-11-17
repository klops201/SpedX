package TMSv3.SpedX.presentation.sign_up.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import TMSv3.SpedX.components.BackIcon
import TMSv3.SpedX.core.Constants.SIGN_UP_SCREEN

@Composable
fun SignUpTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = SIGN_UP_SCREEN
            )
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        }
    )
}