package TMSv3.SpedX.navigation

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.core.Constants.APP_ID
import TMSv3.SpedX.core.Constants.DRIVER_ID
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
import TMSv3.SpedX.navigation.Screen.EditOrderScreen
import TMSv3.SpedX.navigation.Screen.TicketScreen
import TMSv3.SpedX.navigation.Screen.MapScreen
import TMSv3.SpedX.navigation.Screen.DriversMainScreen
import TMSv3.SpedX.navigation.Screen.EditDriverScreen
import TMSv3.SpedX.navigation.Screen.AddDriverScreen
import TMSv3.SpedX.navigation.Screen.WebScreen
import TMSv3.SpedX.presentation.forgot_password.ForgotPasswordScreen
import TMSv3.SpedX.presentation.profile.ProfileScreen
import TMSv3.SpedX.presentation.profile.WebScreenView
import TMSv3.SpedX.presentation.sign_in.SignInScreen
import TMSv3.SpedX.presentation.ticket.TicketScreen
import TMSv3.SpedX.presentation.sign_up.SignUpScreen
import TMSv3.SpedX.presentation.verify_email.VerifyEmailScreen
import TMSv3.SpedX.presentation.orders.orders_list.OrdersScreen
import TMSv3.SpedX.presentation.orders.pick_order.PickOrderScreen
import TMSv3.SpedX.presentation.orders.add_order.AddOrderScreen
import TMSv3.SpedX.presentation.orders.edit_order.EditOrderScreen
import TMSv3.SpedX.presentation.map.MapScreen
import TMSv3.SpedX.presentation.drivers.driver_edit.EditDriverScreen
import TMSv3.SpedX.presentation.drivers.drivers_main.DriversMainScreen
import TMSv3.SpedX.presentation.drivers.driver_add.AddDriverScreen
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
            route = TicketScreen.route
        ) {
            TicketScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = MapScreen.route
        ) {
            MapScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }




        composable(
            route = DriversMainScreen.route
        ) {
            DriversMainScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToDriverEditScr = { clickedDriverID ->
                    Log.d(
                        Constants.TAG,
                        "before -----navController.navigate{EditDriverScreen.route}/clickedDriver-------------------------------------------------------------------clickedDriver: $clickedDriverID"
                    )
                    navController.navigate("${EditDriverScreen.route}/$clickedDriverID")
                    Log.d(
                        Constants.TAG,
                        "after -----navController.navigate{EditDriverScreen.route}/clickedDriver-------------------------------------------------------------------clickedDriver: $clickedDriverID"
                    )

                },
                naviagteAddDriver = {
                    navController.navigate(AddDriverScreen.route)
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
                },
                navigateToMapScreen = { navController.navigate(MapScreen.route) },
                navigateToTicketScreen = { navController.navigate(TicketScreen.route) },
                navigateToDriversScreen = { navController.navigate(DriversMainScreen.route) },
                openWeb = { appNr -> navController.navigate("${WebScreen.route}/$appNr")
                    Log.d(
                        Constants.TAG,
                        "NAVGRAPH WHICH APP------------------------------appNr: $appNr"
                    )
                }
            )
        }
        composable(
            route = AddOrderScreen.route
        ) {
            AddOrderScreen(
                navigateBack = {
                    navController.popBackStack()
                }, navigateToOrders = {
                    navController.navigate(OrdersScreen.route) {
                        popUpTo(OrdersScreen.route) {
                            inclusive = true
                        }
                    }
                }
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
                navigateToOrderDetails = { clickedOrderID ->
                    Log.d(
                        Constants.TAG,
                        "before -----navController.navigate{PickOrderScreen.route}/clickedOrderID-------------------------------------------------------------------orderId: $clickedOrderID"
                    )
                    navController.navigate("${PickOrderScreen.route}/$clickedOrderID")
                    Log.d(
                        Constants.TAG,
                        "after -----navController.navigate{PickOrderScreen.route}/clickedOrderID-------------------------------------------------------------------orderId: $clickedOrderID"
                    )

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
                navigateToEditOrder = { orderId ->
                    navController.navigate("${EditOrderScreen.route}/$orderId")
                }
            )
        }

        composable(
            route = "${EditDriverScreen.route}/{$DRIVER_ID}",
            arguments = listOf(
                navArgument(DRIVER_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val driverId: String = backStackEntry.arguments?.getString(DRIVER_ID) ?: NO_VALUE
            EditDriverScreen(
                driverIDFB = driverId,
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToDrivers = {
                    navController.navigate(DriversMainScreen.route) {
                        popUpTo(DriversMainScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }



        composable(
            route = "${EditOrderScreen.route}/{$ORDER_ID}",
            arguments = listOf(
                navArgument(ORDER_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val orderId: String = backStackEntry.arguments?.getString(ORDER_ID) ?: NO_VALUE
            EditOrderScreen(
                orderId = orderId,
                navigateBack = {
                    navController.popBackStack()
                },
                navController = navController,
                navigateToOrderDetails = { navController.navigate("${PickOrderScreen.route}/$orderId") },
                navigateToOrders = {
                    navController.navigate(OrdersScreen.route) {
                        popUpTo(OrdersScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }


        composable(
            route = AddDriverScreen.route
        ) {
            AddDriverScreen(
                navigateBack = {
                    navController.popBackStack()
                }, navigateToDrivers = {
                    navController.navigate(DriversMainScreen.route) {
                        popUpTo(DriversMainScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }


        composable(
            route = "${WebScreen.route}/{appNr}",
            arguments = listOf(
                navArgument("appNr") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val appId: Int = backStackEntry.arguments?.getInt("appNr") ?: 0
            Log.d(
                Constants.TAG,
                "ODEBRANE APP NUMBER W WebSCR-------------------------------------------------------------------appId: $appId"
            )

            WebScreenView(
                navigateBack = {
                    navController.popBackStack()
                },
                app = appId
            )
        }


    }
}

