package TMSv3.SpedX.presentation.settings.viniet

import TMSv3.SpedX.presentation.settings.components.SettingsContent
import TMSv3.SpedX.presentation.settings.viniet.components.VinietContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun VinietScreen(
    navigateBack: () -> Boolean,
){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        content = { padding ->
            VinietContent(padding = padding,
                goBack = {navigateBack()}
            )
        })


}