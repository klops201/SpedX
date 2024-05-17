package TMSv3.SpedX.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import TMSv3.SpedX.navigation.NavGraph
import TMSv3.SpedX.navigation.Screen
import TMSv3.SpedX.navigation.Screen.ProfileScreen
import TMSv3.SpedX.navigation.Screen.SignInScreen
import TMSv3.SpedX.navigation.Screen.VerifyEmailScreen
import TMSv3.SpedX.navigation.Screen.OrdersScreen
import TMSv3.SpedX.presentation.uiTheme.AppTheme

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {//// cala logika odsyłania do ekranów w zależność od stanu zalogowania
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                navController = rememberNavController()
                NavGraph(
                    navController = navController
                )
                AuthState()
            }
        }
    }

    @Composable
    private fun AuthState() {
        val isUserSignedOut = viewModel.getAuthState().collectAsState().value
        if (isUserSignedOut) {
            NavigateToSignInScreen()
        } else {
            if (viewModel.isEmailVerified) {
                NavigateToProfileScreen()
            } else {
                NavigateToVerifyEmailScreen()
            }
        }
    }

    @Composable
    private fun NavigateToSignInScreen() = navController.navigate(SignInScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToProfileScreen() = navController.navigate(ProfileScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }


    @Composable
    private fun NavigateToVerifyEmailScreen() = navController.navigate(VerifyEmailScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }
}