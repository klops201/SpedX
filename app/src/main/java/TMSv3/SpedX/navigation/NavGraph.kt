package TMSv3.SpedX.navigation

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.core.Constants.NO_VALUE
import TMSv3.SpedX.core.Constants.ORDER_ID
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import TMSv3.SpedX.navigation.Screen.ForgotPasswordScreen
import TMSv3.SpedX.navigation.Screen.ProfileScreen
import TMSv3.SpedX.navigation.Screen.SignInScreen
import TMSv3.SpedX.navigation.Screen.SignUpScreen
import TMSv3.SpedX.navigation.Screen.OrdersScreen
import TMSv3.SpedX.navigation.Screen.PickOrderScreen
import TMSv3.SpedX.navigation.Screen.VerifyEmailScreen
import TMSv3.SpedX.navigation.Screen.AddOrderScreen
import TMSv3.SpedX.presentation.forgot_password.ForgotPasswordScreen
import TMSv3.SpedX.presentation.profile.ProfileScreen
import TMSv3.SpedX.presentation.sign_in.SignInScreen
import TMSv3.SpedX.presentation.sign_up.SignUpScreen
import TMSv3.SpedX.presentation.verify_email.VerifyEmailScreen
import TMSv3.SpedX.presentation.orders_list.OrdersScreen
import TMSv3.SpedX.presentation.pick_order.PickOrderScreen
import TMSv3.SpedX.presentation.add_order.AddOrderScreen
import android.util.Log
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
@ExperimentalComposeUiApi
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SignInScreen.route
    ) {
        composable(
            route = SignInScreen.route
        ) {
            SignInScreen(
                navigateToForgotPasswordScreen = {
                    navController.navigate(ForgotPasswordScreen.route)
                },
                navigateToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                }
            )
        }
        composable(
            route = ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = SignUpScreen.route
        ) {
            SignUpScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = VerifyEmailScreen.route
        ) {
            VerifyEmailScreen(
                navigateToProfileScreen = {
                    navController.navigate(ProfileScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = ProfileScreen.route
        ) {
            ProfileScreen(navController = navController,
                navigateToOrdersScreen = {
                    navController.navigate(OrdersScreen.route)
                })
        }
        composable(
            route = AddOrderScreen.route
        ) {
            AddOrderScreen(
                navigateBack = {
                    navController.popBackStack()
                }, navigateToOrders = { navController.navigate(OrdersScreen.route)}
                )
        }
        composable(
            route = OrdersScreen.route
        ) {
            OrdersScreen(
                navController = navController,
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToAddOrder = {
                    navController.navigate(AddOrderScreen.route)
                },
                navigateToOrderDetails = {
                        clickedOrderID ->
                    Log.d(Constants.TAG, "before -----navController.navigate{PickOrderScreen.route}/clickedOrderID-------------------------------------------------------------------orderId: $clickedOrderID")
                    navController.navigate("${PickOrderScreen.route}/$clickedOrderID")
                    Log.d(Constants.TAG, "after -----navController.navigate{PickOrderScreen.route}/clickedOrderID-------------------------------------------------------------------orderId: $clickedOrderID")

                }
            )
        }

        composable(
            route = "${PickOrderScreen.route}/{$ORDER_ID}",
            arguments = listOf(
                navArgument(ORDER_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val orderId: String = backStackEntry.arguments?.getString(ORDER_ID) ?: NO_VALUE
            PickOrderScreen(
                orderId = orderId,
                navigateBack = {
                    navController.popBackStack()
                },
                navController = navController,
            )
        }



    }
}

