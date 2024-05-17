package TMSv3.SpedX.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.profile.WebViewModel
import TMSv3.SpedX.presentation.settings.components.GetGlobalLoginDataViniet
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
fun goVinjet(
    navigateBack: () -> Unit,
    viewModel: WebViewModel = hiltViewModel()
) {
    Log.d(Constants.TAG, "ODPALANIE GOVINJET")



    var vinietLoad by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getVinietData()
        Log.d(Constants.TAG, "uruchomienie --- getViniet w go viniet ")
    }









    GetGlobalLoginDataViniet { item ->
        vinietLoad = true

        val login = item.log ?: "błedne dane"
        val pass = item.gate ?: "błędne dane"


        val context = LocalContext.current
        val url =
            "https://smsvinjetes.lv/pl/users/sign_in"


        if(vinietLoad) {
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
                            var loginInput = document.getElementById('user_email');
                            var passwordInput = document.getElementById('user_password');
                            if (loginInput && passwordInput) {
                                loginInput.value = '$login';
                                passwordInput.value = '$pass';
                            
                                // Skrypt JavaScript do kliknięcia przycisku logowania
                                var loginButton = document.querySelector('input[type="submit"][name="commit"][value="Zaloguj"]');
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
                                // Sprawdzenie obecności Captchy
                                var captchaElement = document.getElementById('rc-anchor-container'); // Zastąp '.captcha-class' rzeczywistym selektorem Captchy
                                if (captchaElement) {
                                    console.log("Captcha wykryta! Zatrzymywanie skryptu.");
                                    return; // Zatrzymanie dalszego wykonywania skryptu
                                }
                            
                                var currentUrl = window.location.href;
                                var desiredUrl = 'https://smsvinjetes.lv/pl/active_customer/27356/welcome';
                                if (currentUrl == desiredUrl) {
                                    window.location.href = 'https://smsvinjetes.lv/pl/active_customer/27356/balance';
                                } else {
                                    console.log("brak zalogowania");
                                }
                            }, 3000);
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