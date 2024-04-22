package TMSv3.SpedX.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun goASN(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val url =
        "https://www.autosatnet.eu/"
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
                        var customerInput = document.getElementById('loginOldCustomer');
                        var userInput = document.getElementById('loginOldUser');
                        var passwordInput = document.getElementById('loginOldPass');
                        
                        
                        if (customerInput && passwordInput) {
                            customerInput.value = 'Ostrokolowicz';
                            userInput.value = 'Administrator';
                            passwordInput.value = 'Uslugi1!';

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

                            var hintLink = document.getElementById('collapseMenu');

                            if(hintLink){
                                window.location.href = 'https://www.autosatnet.eu/sentgeo/';
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