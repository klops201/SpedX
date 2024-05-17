package TMSv3.SpedX.presentation.settings.etoll

import TMSv3.SpedX.presentation.settings.viniet.components.VinietContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun EtollScreen(
    navigateBack: () -> Boolean,
){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        content = { padding ->
            EtollContent(padding = padding,
                goBack = {navigateBack()}
            )
        })


}