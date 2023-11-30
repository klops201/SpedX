package TMSv3.SpedX.core

object Constants {
    //App
    const val TAG = "AppTag"

    //Buttons
    const val SIGN_IN_BUTTON = "Zaloguj si"
    const val RESET_PASSWORD_BUTTON = "Reset"
    const val SIGN_UP_BUTTON = "Zrejetruj sie"

    //Menu Items
    const val SIGN_OUT_ITEM = "Wyloguj się"
    const val REVOKE_ACCESS_ITEM = "Odnów dostęp"

    //Order
    const val ORDER_DETAILS = "Informacje o zleceniu"



    //Navi
    const val ORDER_ID = "clickedOrderID"


    //Empty Value
    const val NO_VALUE = ""



    //Screens
    const val SIGN_IN_SCREEN = "Sign in"
    const val FORGOT_PASSWORD_SCREEN = "Forgot password"
    const val SIGN_UP_SCREEN = "Sign up"
    const val VERIFY_EMAIL_SCREEN = "Verify email"
    const val PROFILE_SCREEN = "Profile"
    const val ORDERS_SCREEN = "Zlecenia"
    const val PICK_ORDER_SCREEN = "Zlecenie "

    //Labels
    const val EMAIL_LABEL = "Email"
    const val PASSWORD_LABEL = "Hasło"
    const val NAME_LABEL = "Imię"

    //Useful
    const val EMPTY_STRING = ""
    const val VERTICAL_DIVIDER = "|"

    //Texts
    const val FORGOT_PASSWORD = "Zapomniałeś hasła?"
    const val NO_ACCOUNT = "Nie masz konta? Zarejestruj się."
    const val ALREADY_USER = "Masz już konto. Zaloguj się"
    const val WELCOME_MESSAGE = "Witaj!."
    const val ALREADY_VERIFIED = "Zweryfikowałem mail"
    const val SPAM_EMAIL = "Jeśli nie, sprawdź folder spam."

    //Messages
    const val VERIFY_EMAIL_MESSAGE = "Wysłaliśmy tobie mailem link weryfikacyjny."
    const val EMAIL_NOT_VERIFIED_MESSAGE = "Twoj email nie jest zweryfikowany."
    const val RESET_PASSWORD_MESSAGE = "Wysłaliśmy tobie link resetujący hasło."
    const val REVOKE_ACCESS_MESSAGE = "You need to re-authenticate before revoking the access."
    const val ACCESS_REVOKED_MESSAGE = "Your access has been revoked."

    //Error Messages
    const val SENSITIVE_OPERATION_MESSAGE = "This operation is sensitive and requires recent authentication. Log in again before retrying this request."
}