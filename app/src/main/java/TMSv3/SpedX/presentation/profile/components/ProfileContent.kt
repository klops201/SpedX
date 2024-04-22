package TMSv3.SpedX.presentation.profile.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
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
import TMSv3.SpedX.core.Utils
import TMSv3.SpedX.presentation.orders.orders_list.OrdersViewModel
import TMSv3.SpedX.presentation.orders.orders_list.components.ShowOrder
import TMSv3.SpedX.presentation.orders.orders_list.components.getOrders
import TMSv3.SpedX.presentation.profile.ProfileViewModel
import TMSv3.SpedX.presentation.uiTheme.GreyBG
import TMSv3.SpedX.presentation.uiTheme.componentShapes
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun ProfileContent(
    padding: PaddingValues,
    userName: String,
    navigateToOrdersScreen: () -> Unit,
    openMap: () -> Unit,
    goBuyTickets: () -> Unit,
    openDriversList: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
    openWeb: (Int) -> Unit
) {

    LaunchedEffect(viewModel) {
        viewModel.fetchVehicles()
    }





    var goOrders: Boolean = false

    LaunchedEffect(viewModel) {
        viewModel.getUndoneOrdersList()
    }

    val context = LocalContext.current

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(componentShapes.small)
            .background(Color.White)// wazna kolejnosc
            .padding(horizontal = 15.dp)
            .verticalScroll(state = scrollState)
    ) {
        Column (modifier = Modifier
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(35.dp))
            Greeting(userName = userName)

            Spacer(modifier = Modifier.height(35.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){

                BoxDate(openCalendar = {Utils.openCalendar(context)})
                Spacer(modifier = Modifier.width(17.dp))
                checkDrivers(onClick = {openDriversList()})
            }


            Spacer(modifier = Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                ImageBoxMap(openMap = openMap)
                Spacer(modifier = Modifier.width(17.dp))
                BoxSent(openBrowser = {openWeb(0)}
                )
            }

            Spacer(modifier = Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
//
                Box(
                    modifier = Modifier
                        .height(210.dp)
                        //.width(210.dp)
                        .clip(AlertDialogDefaults.shape)
                        .clickable { navigateToOrdersScreen() }
                        .background(colorResource(id = R.color.colorTest))
                        .padding(15.dp)
                        .fillMaxWidth()
                )
                {

                    Column(verticalArrangement = Arrangement.Center) {
//                        Box(                    modifier = Modifier
//                            .weight(1f)
//                            .fillMaxWidth()
//                            .fillMaxHeight()) {
//                            androidx.compose.material3.Text(
//                                modifier = Modifier
//                                    .align(Alignment.Center),
//                                textAlign = TextAlign.Center,
//                                text = "Lista zleceń",
//                                fontSize = 20.sp
//                            )
//                        }
                        getUndoneOrders { orders ->
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                contentPadding = PaddingValues(8.dp)
                            ) {
                                items(orders) { order ->
                                    Log.d(Constants.TAG, "exe funct lazy column -------------------: $order")
//                                    val clickecOrderID = order.firestoreID
                                    ShowUndoneOrder(order = order)
                                }
                            }

                        }

//                        Row(
//                            Modifier
//                                .weight(1f)
//                                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically ) {
//                            Icon(
//                                modifier = Modifier.clickable { println("Button Clicked!") },
//                                painter = painterResource(id = R.drawable.dot),
//                                contentDescription = null, // Możesz dostosować opis dostępności
//                            )
//                            // Separacja między kropką a tekstem
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            // Tekst zadania
//                            androidx.compose.material3.Text(
//                                text = "zlecenie nr 2",
//                                fontSize = 20.sp
//                            )
//                        }
//                        Row(
//                            Modifier
//                                .weight(1f)
//                                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
//                            Icon(
//                                modifier = Modifier.clickable { println("Button Clicked!") },
//                                painter = painterResource(id = R.drawable.dot),
//                                contentDescription = null, // Możesz dostosować opis dostępności
//                            )
//                            // Separacja między kropką a tekstem
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            // Tekst zadania
//                            androidx.compose.material3.Text(
//                                text = "zlecenie nr 3",
//                                fontSize = 20.sp
//                            )
//                        }



                    }

                }

            }
            Spacer(modifier = Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                EtollBox(openBrowser = {Utils.openBrowser("https://shorturl.at/dgDMT", context)})
                Spacer(modifier = Modifier.width(17.dp))
                SettingsBox()
            }

            Spacer(modifier = Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                WinietBox(buyTicket = {goBuyTickets()})
                Spacer(modifier = Modifier.width(17.dp))
                AutoSatNetBox(openBrowser = {openWeb(1)})
            }


        }



    }






}