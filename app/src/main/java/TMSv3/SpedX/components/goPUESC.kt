package TMSv3.SpedX.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.profile.WebViewModel
import TMSv3.SpedX.presentation.settings.puesc.GetSentFormWeb
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun goPUESC(
    navigateBack: () -> Unit,
    viewModel: WebViewModel = hiltViewModel()
) {


    var puescLoad by remember { mutableStateOf(false) }
    var formFill by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getPuescData()
    }


    GetSentFormWeb { item ->

        puescLoad = true
        val login = item.log ?: "błedne dane"
        val pass = item.gate ?: "błędne dane"
        val nip = item.NIP ?: "błędne dane"
        val address = item.address ?: "błędne dane"
        val borderPost = item.borderPosition ?: "błędne dane"
        val borderRoad = item.borderRoad ?: "błędne dane"
        val cargoPermit = item.cargoPermit ?: "błędne dane"
        val companyName = item.companyName ?: "błędne dane"
        val email = item.emailConfirmation ?: "błędne dane"
        val gpsId = item.gpsID ?: "błędne dane"
        val trailerId = item.trailerID ?: "błędne dane"
        val truckId = item.truckID ?: "błędne dane"


        val context = LocalContext.current
        val url =
            "https://puesc.gov.pl/puesc?p_p_id=com_liferay_login_web_portlet_LoginPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&saveLastPath=false&_com_liferay_login_web_portlet_LoginPortlet_mvcRenderCommandName=/login/login"


        if (puescLoad) {
            AndroidView(
                factory = { WebView(context) },
                update = { webView ->
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)

                            // Skrypt JavaScript do automatycznego uzupełniania formularza i logowania
                            val fillFormScript = """
                        
                        // Uzupełnianie formularza
                        var loginInput = document.getElementById('_com_liferay_login_web_portlet_LoginPortlet_login');
                        var passwordInput = document.getElementById('_com_liferay_login_web_portlet_LoginPortlet_password');
                        var logoutLink = document.querySelector('input.required.search.form-control#search');
                        if (loginInput && passwordInput) {
                            loginInput.value = '$login';
                            passwordInput.value = '$pass';

                            // Skrypt JavaScript do kliknięcia przycisku logowania
                            var loginButton = document.querySelector('button[type="submit"]');
                            if (loginButton) {
                                loginButton.click();
                                console.log("Kliknięto przycisk logowania!");
                            } else {
                                console.log("Nie znaleziono przycisku logowania!");
                            }
                        } else {
                            console.log("Nie znaleziono elementów formularza logowania!");
                        }
                        
                        
                        setTimeout(function() {
                        var currentUrl = window.location.href;
                        var desiredUrl = 'https://puesc.gov.pl/puesc';
                        if( currentUrl == desiredUrl){
                        var isHere = true;
                        } else {
                        var isHere = false
                        }
                        if(isHere == true){
                            window.location.href = 'https://puesc.gov.pl/web/guest/uslugi/przewoz-towarow-objety-monitorowaniem/sent-aktualizacja?systemName=SENT&amp;formName=1000724&amp;systemName=SENT&amp;formName=1000724';
                        } else {
                            console.log("brak zalogowania");
                        }
                        }, 4000); 
                        
                        
                        if ($formFill){
                        setTimeout(function() {
                        var nipInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_nip');
                        var addressInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_address');
                        var borderPostInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_borderPost');
                        var borderRoadInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_borderRoad');
                        var cargoPermitInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_cargoPermit');
                        var companyNameInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_companyName');
                        var emailInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_email');
                        var gpsIdInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_gpsId');
                        var trailerIdInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_trailerId');
                        var truckIdInput = document.getElementById('_com_liferay_forms_web_portlet_FormsPortlet_truckId');
                        
                        
                            if (nipInput && addressInput && borderPostInput && borderRoadInput &&
                            cargoPermitInput && companyNameInput && emailInput &&
                            gpsIdInput && trailerIdInput && truckIdInput) {
                            
                            // Przypisywanie wartości do pól
                            nipInput.value = '$nip';
                            addressInput.value = '$address';
                            borderPostInput.value = '$borderPost';
                            borderRoadInput.value = '$borderRoad';
                            cargoPermitInput.value = '$cargoPermit';
                            companyNameInput.value = '$companyName';
                            emailInput.value = '$email';
                            gpsIdInput.value = '$gpsId';
                            trailerIdInput.value = '$trailerId';
                            truckIdInput.value = '$truckId';
                        } else {
                            console.log("nie znaleziono wszystkich pól")
                        }
                        

                        
                        
                        }, 4000); 
                        } else {
                            console.log("nie uruchomiono autoFill")
                        }

                        
                    """.trimIndent()

                            // Wykonaj skrypt JavaScript po załadowaniu strony
                            webView.evaluateJavascript(fillFormScript, null)
                        }
                    }

                    // Ustaw listener dla konsoli JavaScript
                    webView.webChromeClient = object : WebChromeClient() {
                        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                            Log.d("WebViewConsole", "${consoleMessage?.message()}")
                            return super.onConsoleMessage(consoleMessage)
                        }
                    }

                    // Załaduj żądaną stronę
                    webView.loadUrl(url)
                },
                modifier = Modifier.fillMaxSize()
            )
            androidx.compose.material.FloatingActionButton(
                onClick = {
                    formFill = !formFill
                    Log.d(Constants.TAG, "clicked autofill")

                },
                backgroundColor = colorResource(id = R.color.colorPUESC)
            ) {
                Icon(Icons.Filled.AutoFixHigh, "", tint = Color.White)
            }
        }
    }

}