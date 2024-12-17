## SpedX

## Funkcjonalności

Aplikacja oferuje dostęp do podstawowych funkcji przydatnych przy prowadzeniu niewielkiej firmy transportowej.

Funkcje zostały dobrane i zaimplementowane pod potrzeby konkretnej firmy, tworząc wygodną aplikację do obsługi kliku platform m.in.:
- PUESC - Platforma Usług Elektronicznych Skarbowo-Celnych;
- eToll - System Poboru Opłaty Elektronicznej KAS;
- SMSvinjetes.lv - System umożliwiający zakup eurowiniet;
- AutoSatNet - Systemu monitoringu GPS firmy ATROM;
 
Szczegółowy spis funkcji:
- niezleżne logowanie dla wielu użytkowników z weryfikacją adresu e-mail + przypomnienie hasła;
- moduł zarządzenie kierowcami - operacje CRUD + dane z API AutoSatNet;
- moduł zarządzania zleceniami - operacje CRUD;
- funkcja kalendarza - przekierowanie do domyślnej aplikacji kalendarza w telefonie;
- funkcja mapy - wybranie dostępnego pojazdu z bazy i wczytanie jego aktualnej pozycji/ danych poprzez API Google Maps + przekierowanie do jego pozycji w zewnętrzenej aplikacji Google Maps;
- funkcja PUESC - aplikacja automatycznie loguje użytkownika w obsadzonej w aplikacji przeglądarce do serwisu i przekierowuje do zakładki formularz SENT;
- funkcja eToll - aplikacja przekierowuje użytkownika do zewnętrzenej przeglądarki do strony logowania do serwisu;
- funkcja AutoSatNet - aplikacja przekierowuje użytkownika do zewnętrzenej przeglądarki do strony logowania do serwisu;
- funkcja SMSvinjetes.lv - generowanie kodów sms do szybkiego kupna winiet i przekierowanie z treścią do domyślnej aplikacji wiadomości w telefonie + automatycznie loguje użytkownika w obsadzonej w aplikacji przeglądarce do serwisu i przekierowuje do zakładki saldo;
- funkcja ustawienia - przechowywanie danych logowania do użytych zewnętrznych serwisów

Warunkiem koniecznym do skorzystania z aplikacji jest smartfon z systemem Android w wersji 9.0 lub wyższej.

## Technologie

Aplikacja wykorzystuje architekturę oprogramowania MVVM (Model/View/ViewModel)

Bazę aplikacji stanowi wcześniej zaimplementowany system logowania stoworzony (z użyciem Firebase Authentication) przez Alexa Mamo (Google Developer Expert for Firebase) i udostępniony na zasadach licencji Apache License 2.0.
https://github.com/alexmamo/FirebaseSignInWithEmailAndPassword

- Bazy danych:
	- Firestore Database
	- Firebase Storage
	- Firebase Authentication

- API:
	- AutoSatNet https://api.autosatnet.eu/
	- Google Maps https://developers.google.com/maps?hl=pl

- Jetpack Compose
- Material Design
- Dependency Injection DI/ Dagger Hilt
- Kotlin Coroutines/ zarządzanie współbieżnością i asynchronicznymi zadaniami

W folderze app screens znajdują się zrzuty ekranu z aplikacji.

Kod źródłowy zawarty w tym repozytorium jest prywatny i nie może być kopiowany, modyfikowany ani używany bez mojej wyraźnej zgody. Wszelkie próby wykorzystania tego kodu bez autoryzacji są zabronione.


## Functionalities

The application provides access to essential features useful for managing a small transport company. 

The functions were carefully selected and implemented to meet the needs of a specific company, creating a convenient application for operating several platforms, including:
  - PUESC - Platform of Electronic Tax and Customs Services;
  - eToll - Electronic Toll Collection System by KAS;
  - SMSvinjetes.lv - System for purchasing eurovignettes;
  - AutoSatNet - ATROM's GPS monitoring system.
    
Detailed list of features:
  - Independent login for multiple users with email verification + password recovery;
  - Driver management module – CRUD operations + data retrieved from the AutoSatNet API;
  - Order management module – CRUD operations;
  - Calendar function – redirects to the phone's default calendar application;
  - Map function – selecting an available vehicle from the database and loading its current position/data via the Google Maps API + redirecting to its location in the external Google Maps application;
  - PUESC function – the application automatically logs the user into the service via the built-in browser and redirects to the SENT form tab;
  - eToll function – the application redirects the user to the external browser on the service's login page;
  - AutoSatNet function – the application redirects the user to the external browser on the service's login page;
  - SMSvinjetes.lv function – generates SMS codes for quickly purchasing vignettes and redirects the message content to the phone's default messaging application + automatically logs the user into the service via the built-in browser and redirects to the balance tab;
  - Settings function – stores login data for the external services used.
    
The application requires a smartphone with Android 9.0 or higher.

## Technologies

The application uses the MVVM (Model/View/ViewModel) software architecture.

The application's core is based on a previously implemented login system (using Firebase Authentication) created by Alex Mamo (Google Developer Expert for Firebase) and shared under the Apache License 2.0:
https://github.com/alexmamo/FirebaseSignInWithEmailAndPassword

- Databases:
  - Firestore Database
  - Firebase Storage
  - Firebase Authentication

- APIs:
  - AutoSatNet https://api.autosatnet.eu/
  - Google Maps https://developers.google.com/maps?hl=pl
  
- Jetpack Compose
- Material Design
- Dependency Injection (DI) / Dagger Hilt
- Kotlin Coroutines for managing concurrency and asynchronous tasks

The app screens folder contains screenshots of the application.


The source code contained in this repository is private and may not be copied, modified, or used without my express permission. Any attempt to use this code without authorization is prohibited.

