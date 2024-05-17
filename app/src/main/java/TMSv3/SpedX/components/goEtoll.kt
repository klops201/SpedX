package TMSv3.SpedX.components

import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.profile.WebViewModel
import TMSv3.SpedX.presentation.settings.components.GetGlobalLoginDataEtoll
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun goEtoll(
    navigateBack: () -> Unit,
    viewModel: WebViewModel = hiltViewModel()
) {



    var etollLoad by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getEtollData()
    }


    GetGlobalLoginDataEtoll { item ->

        etollLoad = true
        val login = item.log ?: "błedne dane"
        val pass = item.gate ?: "błędne dane"

        val context = LocalContext.current
        val url =
            "https://login.mf.gov.pl/Account/Select?ReturnUrl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3DF4330BE7-5858-42DF-90E7-94635F6E2C43%26redirect_uri%3Dhttps%253A%252F%252Fmojekonto.etoll.gov.pl%252Fselfservice%252F%2523%252Flogin-callback%26response_type%3Dcode%26scope%3Dopenid%2520profile%2520EsbApi.UserAccess%2520offline_access%26nonce%3D8fb877de027950a678574f00956523c765dYi44QA%26state%3Dad38e6a1d8dacb882444702568bea1324bf4kzsj8%26code_challenge%3DprPLL8e8wO-QgNnu1ZlrfsMl6n6q6Q3UHjHh0IbKrTU%26code_challenge_method%3DS256"

        if (etollLoad) {
            AndroidView(
                factory = { WebView(context) },
                update = { webView ->
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)

                            // Skrypt JavaScript do automatycznego uzupełniania formularza i logowania
                            val fillFormScript = """
                        
                        var loginButton1 = document.getElementById('external-login-method');
                        
                        if(loginButton1){
                            loginButton.click();
                            setTimeout(function() {
                            var loginButton2 = document.getElementById('go-pz');
                            if(loginButton2){
                             loginButton2.click();
                            
                            setTimeout(function() {
                                var customerInput = document.getElementById('loginForm:login');
                                var passwordInput = document.getElementById('loginForm:hasło');
                                
                                if (customerInput && passwordInput) {
                                    customerInput.value = '$login';
                                    passwordInput.value = '$pass';

                                    // Skrypt JavaScript do kliknięcia przycisku logowania
                                    
//                                    var loginButton3 = document.querySelector('button[type="submit"]');
                                    var loginButton3 = document.getElementById('loginForm:loginButton');
                                    if (loginButton3) {
                                        loginButton3.click();
                                        console.log("Kliknięto przycisk logowania eToll!");
                                    } else {
                                        console.log("Nie znaleziono przycisku logowania eToll3!");
                                    }
                                } else {
                                    console.log("Nie znaleziono elementów formularza logowania eToll!");
                                }
                                
                            
                            }, 2000);
                            
                            
                            
                            } else {
                                console.log("Nie znaleziono przycisku logowania nr 2 eToll!");
                            }
                            
                            }, 1000);
                            
                        } else {
                            console.log("Nie znaleziono przycisku logowania nr 1 eToll!");
                        }
                        
                        
//                        // Uzupełnianie formularza
//                        var customerInput = document.getElementById('loginForm:login');
//                        var passwordInput = document.getElementById('loginForm:hasło');
//                        
//                        
//                        if (customerInput && passwordInput) {
//                            customerInput.value = 'robert.ostrokolowicz@gmail.com';
//                            passwordInput.value = 'Usługi1!';
//
//                            // Skrypt JavaScript do kliknięcia przycisku logowania
//                            var loginButton = document.querySelector('button[type="submit"]');
//                            if (loginButton) {
//                                loginButton.click();
//                                console.log("Kliknięto przycisk logowania ASN!");
//                            } else {
//                                console.log("Nie znaleziono przycisku logowania ASN!");
//                            }
//                        } else {
//                            console.log("Nie znaleziono elementów formularza logowania ASN!");
//                        }
//                        
//                        
//                        
//                        
//                        setTimeout(function() {
//                        console.log("Aktualny adres URL: " + window.location.href);
//
//                            var hintLink = document.getElementById('collapseMenu');
//
//                            if(hintLink){
//                                window.location.href = 'https://www.autosatnet.eu/sentgeo/';
//                                console.log("Aktualny adres URL: " + window.location.href);
//                            } else {
//                                console.log("sentGEO problem");
//                            }
//
//                        }, 4000); 

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