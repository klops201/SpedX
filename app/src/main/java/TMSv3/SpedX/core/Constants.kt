package TMSv3.SpedX.core

object Constants {
    //App
    const val TAG = "AppTag"

    //Buttons
    const val SIGN_IN_BUTTON = "Zaloguj się"
    const val RESET_PASSWORD_BUTTON = "Reset"
    const val SIGN_UP_BUTTON = "Zarejestruj się"

    //Menu Items
    const val SIGN_OUT_ITEM = "Wyloguj się"
    const val REVOKE_ACCESS_ITEM = "Odnów dostęp"

    //Order
    const val ORDER_DETAILS = "Informacje o zleceniu"
    const val ADD_ORDER = "Dodaj zlecenie"
    const val EDIT_ORDER = "Edytuj zlecenie"



    //Navi
    const val ORDER_ID = "clickedOrderID"
    const val DRIVER_ID = "clickedDriverID"


    //Empty Value
    const val NO_VALUE = ""



    //Screens
    const val SIGN_IN_SCREEN = "Zaloguj się"
    const val FORGOT_PASSWORD_SCREEN = "Zapomniałem hasło"
    const val SIGN_UP_SCREEN = "Zarejestruj się"
    const val VERIFY_EMAIL_SCREEN = "Zweryfikuj email"
    const val PROFILE_SCREEN = "Ekran główny"
    const val ORDERS_SCREEN = "Zlecenia"
    const val PICK_ORDER_SCREEN = "Zlecenie "
    const val ADD_ORDER_SCREEN = "Dodaj zlecenie"
    const val EDIT_ORDER_SCREEN = "Edytuj zlecenie"
    const val MAP_SCREEN = "Podgląd pojazdu"
    const val TICKET_SCREEN = "Kupno winiety"
    const val DRIVERS_MAIN_SCREEN = "Kierowcy"
    const val EDIT_DRIVER_SCREEN = "Edytuj informacje o kierowcy"

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
    const val ALREADY_USER = "Masz już konto?. Zaloguj się"
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