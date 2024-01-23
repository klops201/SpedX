package TMSv3.SpedX.presentation.ticket

import TMSv3.SpedX.components.TopBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.navigation.Screen
import TMSv3.SpedX.presentation.profile.components.ProfileContent
import TMSv3.SpedX.presentation.ticket.components.TicketContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable

@Composable
fun TicketScreen(
    navigateBack: () -> Boolean,
){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            TopBar(
                title = Constants.TICKET_SCREEN,
                signOut = {

                },
                revokeAccess = {

                }
            )
        },
        content = {paddingValues ->
        TicketContent(paddingValues = paddingValues)
        },
        scaffoldState = scaffoldState
    )
}