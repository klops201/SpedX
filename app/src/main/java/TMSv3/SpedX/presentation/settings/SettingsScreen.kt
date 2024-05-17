package TMSv3.SpedX.presentation.settings

import TMSv3.SpedX.R
import TMSv3.SpedX.components.TopBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.orders.orders_list.components.OrdersContent
import TMSv3.SpedX.presentation.profile.components.RevokeAccess
import TMSv3.SpedX.presentation.settings.components.SettingsContent
import android.util.Log
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@Composable
fun SettingsScreen(
    navigateBack: () -> Boolean,
    navigateVinjetes: () -> Unit,
    navigateEtoll: () -> Unit,
    navigateAsn: () -> Unit,
    navigatePuesc: () -> Unit

){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        content = { padding ->
            SettingsContent(padding = padding,
                goBack = {navigateBack()},
                openVinejtes = {navigateVinjetes()},
                openEtoll = {navigateEtoll()},
                openAsn = {navigateAsn()},
                openPuesc = {navigatePuesc()}
    )})


}