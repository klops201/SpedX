package TMSv3.SpedX.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.profile.WebViewModel
import TMSv3.SpedX.presentation.settings.SettingsViewModel
import TMSv3.SpedX.presentation.settings.asn.GetAsnData
import TMSv3.SpedX.presentation.settings.asn.GetAsnDataWeb
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoFixHigh
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
fun goASN(
    navigateBack: () -> Unit,
    viewModel: WebViewModel = hiltViewModel()
) {

    var asnLoad by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getAsnData()
        Log.d(Constants.TAG, "uruchomienie --- getAsnData w go ASN ")
    }




    GetAsnDataWeb { item ->
        asnLoad = true
        val user = item.user ?: "błedne dane"
        val customer = item.customer ?: "błedne dane"
        val pass = item.gate ?: "błędne dane"

        val context = LocalContext.current
        val url =
            "https://www.autosatnet.eu/"

        Log.d(Constants.TAG, "aslLoad sprawdzenie 1    --  $asnLoad ")
        if (asnLoad) {
            Log.d(Constants.TAG, "aslLoad sprawdzenie 2   --  $asnLoad ")
            AndroidView(
                factory = { WebView(context) },
                update = { webView ->
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            Log.d(Constants.TAG, "wczytywane dane  --  $user, $customer, $pass ")
                            // Skrypt JavaScript do automatycznego uzupełniania formularza i logowania
                            val fillFormScript = """
                        // Uzupełnianie formularza
                        var customerInput = document.getElementById('loginOldCustomer');
                        var userInput = document.getElementById('loginOldUser');
                        var passwordInput = document.getElementById('loginOldPass');
                        
                        
                        if (customerInput && passwordInput) {
                            customerInput.value = "$customer";
                            userInput.value = "$user";
                            passwordInput.value = "$pass";

                            // Skrypt JavaScript do kliknięcia przycisku logowania
                            var loginButton = document.querySelector('button[type="submit"]');
                            if (loginButton) {
                                loginButton.click();
                                console.log("Kliknięto przycisk logowania ASN!");
                            } else {
                                console.log("Nie znaleziono przycisku logowania ASN!");
                            }
                        } else {
                            console.log("Nie znaleziono elementów formularza logowania ASN!");
                        }
                        
                        
                        
                        
                        setTimeout(function() {
                        console.log("Aktualny adres URL: " + window.location.href);
                        var currentUrl = window.location.href;
                        var desiredUrl = 'https://www.autosatnet.eu/monit/';
                        if( currentUrl == desiredUrl){
                        var isHere = true;
                        } else {
                        var isHere = false
                        }

                            if(isHere){
                                window.location.href = 'https://www.autosatnet.eu/sentgeo/';
                                
                                setTimeout(function() {
                                document.getElementById('sentgeo-share-vehicles').click();
                                }, 2000); 
                                
                                
                                console.log("Aktualny adres URL: " + window.location.href);
                            } else {
                                console.log("sentGEO problem");
                            }

                        }, 4000); 

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

        }
    }

}