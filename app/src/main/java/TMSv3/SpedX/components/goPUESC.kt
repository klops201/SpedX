package TMSv3.SpedX.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun goPUESC(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val url =
        "https://puesc.gov.pl/puesc?p_p_id=com_liferay_login_web_portlet_LoginPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&saveLastPath=false&_com_liferay_login_web_portlet_LoginPortlet_mvcRenderCommandName=/login/login"

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
                            loginInput.value = 'robert.ostrokolowicz@gmail.com';
                            passwordInput.value = 'ScaniaZiemne66';

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
                        var homePage =  window.location.href = 'https://puesc.gov.pl/';
                        
                        
                        
                        setTimeout(function() {
                        var logoutLink = document.querySelector('input.required.search.form-control#search');
                        if(logoutLink){
                            window.location.href = 'https://puesc.gov.pl/web/guest/uslugi/przewoz-towarow-objety-monitorowaniem/sent-aktualizacja?systemName=SENT&amp;formName=1000724&amp;systemName=SENT&amp;formName=1000724';
                        } else {
                            console.log("brak zalogowania");
                        }
                        }, 4000); 
                        
//                        window.location.href = 'https://puesc.gov.pl/web/guest/uslugi/przewoz-towarow-objety-monitorowaniem/sent-aktualizacja?systemName=SENT&amp;formName=1000724&amp;systemName=SENT&amp;formName=1000724';
//                       
                       
//                        // Po zalogowaniu klikaj w kolejne zakładki
//                        
//                        
//                        var firstTabButton = document.querySelector('a[href="https://puesc.gov.pl/web/guest/uslugi/przewoz-towarow-objety-monitorowaniem/sent-aktualizacja?systemName=SENT&amp;formName=1000724&amp;systemName=SENT&amp;formName=1000724"]');
//                       
//                        if (firstTabButton) {
//                            firstTabButton.click();
//                            console.log("Kliknięto pierwszy przycisk zakładki!");
//                                var secondTabLink = document.querySelector('a[href="https://puesc.gov.pl/web/guest/uslugi/przewoz-towarow-objety-monitorowaniem/sent-aktualizacja?systemName=SENT&amp;formName=1000724&amp;systemName=SENT&amp;formName=1000724"]');
//                                if (secondTabLink) {
//                                    secondTabLink.click();
//                                    console.log("Kliknięto drugi link zakładki!");
//                                }
//                        } else {
//                            console.log("Nie znaleziono elementów edit SENT!");
//                        }
                        
                        
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
            Log.d(Constants.TAG, "clicked autofill")

        },
        backgroundColor = colorResource(id = R.color.colorPUESC)
    ) {
        Icon(Icons.Filled.AutoFixHigh, "", tint = Color.White)
    }


}