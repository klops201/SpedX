package TMSv3.SpedX.presentation.settings.puesc

import TMSv3.SpedX.presentation.settings.etoll.EtollContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun PuescScreen(
    navigateBack: () -> Boolean,
){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        content = { padding ->
            PuescContent(padding = padding,
                goBack = {navigateBack()}
            )
        })


}