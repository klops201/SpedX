package TMSv3.SpedX.presentation.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.components.TopBar
import TMSv3.SpedX.core.Constants.PROFILE_SCREEN
import TMSv3.SpedX.navigation.Screen
import TMSv3.SpedX.presentation.profile.components.LoadUserName
import TMSv3.SpedX.presentation.profile.components.ProfileContent
import TMSv3.SpedX.presentation.profile.components.RevokeAccess
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.navigation.NavController
import com.google.firebase.annotations.PreviewApi

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController,
    navigateToOrdersScreen: () -> Unit,
    navigateToMapScreen: () -> Unit,
    navigateToTicketScreen: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    viewModel.getUserName()
    LoadUserName(onSuccess = {userName ->
        viewModel._nameFirebase.value = userName
    })
    Scaffold(
        topBar = {
            TopBar(
                title = PROFILE_SCREEN,
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            ProfileContent(
                padding = padding,
                userName = viewModel._nameFirebase.value,
                navigateToOrdersScreen = navigateToOrdersScreen,
                openMap = navigateToMapScreen,
                goBuyTickets = navigateToTicketScreen,
            )
        },
        scaffoldState = scaffoldState
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}
