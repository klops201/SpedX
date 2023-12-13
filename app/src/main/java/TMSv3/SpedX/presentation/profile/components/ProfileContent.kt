package TMSv3.SpedX.presentation.profile.components

import TMSv3.SpedX.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import TMSv3.SpedX.core.Constants.WELCOME_MESSAGE
import TMSv3.SpedX.presentation.profile.ProfileViewModel
import TMSv3.SpedX.presentation.uiTheme.componentShapes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.produceState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun ProfileContent(
    padding: PaddingValues,
    userName: String,
    navigateToOrdersScreen: () -> Unit,
    openMap: () -> Unit,
) {
    var goOrders: Boolean = false
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)// wazna kolejnosc
//            .padding(28.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.dog),
//            modifier = Modifier.fillMaxSize(),
//            contentDescription = "Background Image",
//            contentScale = ContentScale.Crop
//            //contentScale = ContentScale.Crop
//        )
//
//    }
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(componentShapes.small)
            .background(Color.White)// wazna kolejnosc
            .padding(28.dp)
            .verticalScroll(state = scrollState)
    ) {
        Column (modifier = Modifier
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Greeting(userName = userName)

            Spacer(modifier = Modifier.height(35.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){

                BoxDate()

            }


            Spacer(modifier = Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                ImageBoxMap(openMap = openMap)
                Spacer(modifier = Modifier.width(17.dp))
                BoxSent()
            }

            Spacer(modifier = Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                OrderList(onClick = {navigateToOrdersScreen()})

            }
            Spacer(modifier = Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                EtollBox()
                Spacer(modifier = Modifier.width(17.dp))
                SettingsBox()
            }

            Spacer(modifier = Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                WinietBox()
                Spacer(modifier = Modifier.width(17.dp))
                AutoSatNetBox()
            }


        }



    }






}