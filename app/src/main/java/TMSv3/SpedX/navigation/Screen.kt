package TMSv3.SpedX.navigation

import TMSv3.SpedX.core.Constants.ADD_ORDER_SCREEN
import TMSv3.SpedX.core.Constants.DRIVERS_MAIN_SCREEN
import TMSv3.SpedX.core.Constants.EDIT_DRIVER_SCREEN
import TMSv3.SpedX.core.Constants.EDIT_ORDER_SCREEN
import TMSv3.SpedX.core.Constants.FORGOT_PASSWORD_SCREEN
import TMSv3.SpedX.core.Constants.ORDERS_SCREEN
import TMSv3.SpedX.core.Constants.PICK_ORDER_SCREEN
import TMSv3.SpedX.core.Constants.PROFILE_SCREEN
import TMSv3.SpedX.core.Constants.SIGN_IN_SCREEN
import TMSv3.SpedX.core.Constants.VERIFY_EMAIL_SCREEN
import TMSv3.SpedX.core.Constants.SIGN_UP_SCREEN
import TMSv3.SpedX.core.Constants.MAP_SCREEN
import TMSv3.SpedX.core.Constants.TICKET_SCREEN

sealed class Screen(val route: String) {
    object SignInScreen: Screen(SIGN_IN_SCREEN)
    object ForgotPasswordScreen: Screen(FORGOT_PASSWORD_SCREEN)
    object SignUpScreen: Screen(SIGN_UP_SCREEN)
    object VerifyEmailScreen: Screen(VERIFY_EMAIL_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
    object OrdersScreen: Screen(ORDERS_SCREEN)
    object PickOrderScreen: Screen(PICK_ORDER_SCREEN)
    object AddOrderScreen: Screen(ADD_ORDER_SCREEN)
    object EditOrderScreen: Screen(EDIT_ORDER_SCREEN)
    object MapScreen: Screen(MAP_SCREEN)
    object TicketScreen: Screen(TICKET_SCREEN)
    object DriversMainScreen: Screen(DRIVERS_MAIN_SCREEN)
    object EditDriverScreen: Screen(EDIT_DRIVER_SCREEN)
}