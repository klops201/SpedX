package TMSv3.SpedX.presentation.profile

import TMSv3.SpedX.components.goASN
import TMSv3.SpedX.components.goEtoll
import TMSv3.SpedX.components.goPUESC
import TMSv3.SpedX.components.goVinjet
import TMSv3.SpedX.navigation.Screen
import android.webkit.WebView
import androidx.compose.runtime.Composable

@Composable
fun WebScreenView(navigateBack: () -> Unit,
                  app: Int) {



    when (app) {
        0 -> goPUESC(navigateBack)
        1 -> goASN(navigateBack)
        3 -> goEtoll(navigateBack)
        4 -> goVinjet(navigateBack)
        5 -> println("Nieznana ocena")
        else -> println("Nieznana wartość")
    }
}