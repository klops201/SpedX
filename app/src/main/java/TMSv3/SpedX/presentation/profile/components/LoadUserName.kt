package TMSv3.SpedX.presentation.profile.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.presentation.profile.ProfileViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.domain.repository.CreateUserResponse
import TMSv3.SpedX.domain.repository.UserNameResponse

@Composable
fun LoadUserName(
    viewModel: ProfileViewModel = hiltViewModel(),
    onSuccess: (String) -> Unit
) {
    when(val userNameResponse = viewModel.createUserAppResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val userName = userNameResponse.data
            onSuccess(userName)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(userNameResponse.e)
        }
    }
}