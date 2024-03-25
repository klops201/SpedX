package TMSv3.SpedX.presentation.profile.components

import android.database.Observable
import android.graphics.Color
import android.os.Bundle
import android.widget.TextClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.unit.dp


import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import TMSv3.SpedX.presentation.uiTheme.GreyBG
import TMSv3.SpedX.presentation.uiTheme.tmsBG
import TMSv3.SpedX.presentation.uiTheme.tmsContainer
import TMSv3.SpedX.presentation.uiTheme.tmsOnContainer
import TMSv3.SpedX.presentation.uiTheme.tmsOnPrimary
import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.presentation.profile.ProfileViewModel
import TMSv3.SpedX.presentation.uiTheme.Redetoll
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity

@Composable
fun Greeting(userName: String){
    Text(
        text = "Cześć $userName",
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),

        style = androidx.compose.ui.text.TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal),

        color = colorResource(id = R.color.colorTest),
        textAlign = TextAlign.Center

    )
}



@Composable
fun GetDayDate():String{
//    val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss z")
    val sdf = SimpleDateFormat(" dd-MM-yyyy ")
    val currentDateAndTime = sdf.format(Date())
    return currentDateAndTime
}


//
//
//@Composable
//fun Example(onClick: () -> Unit) {
//    FloatingActionButton(
//        onClick = { onClick() },
//    ) {
//        Icon(Icons.Filled.Add, "Floating action button.")
//    }
//}
//




@Composable
fun BoxDate(){

    Box(modifier = Modifier
        .height(100.dp)
        .width(160.dp)
        .clip(AlertDialogDefaults.shape)
        .background(colorResource(id = R.color.colorTest))
        .fillMaxSize(), contentAlignment = Alignment.Center){
        //Text(modifier = Modifier.padding(all = 10.dp), textAlign = TextAlign.Center, text = currentDateAndTime, fontSize = 30.sp)
        Text(text = GetDayDate(), fontSize = 25.sp, color= androidx.compose.ui.graphics.Color.White)

    }

}



@Composable
fun checkDrivers(onClick: () -> Unit){
    Box(modifier = Modifier
        .clip(AlertDialogDefaults.shape)
        .height(100.dp)
        .fillMaxWidth()
        .background(Redetoll)
        .clickable { onClick() }
    ){
        Row (modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Box (modifier = Modifier
                .weight(2f)
                .fillMaxSize(), contentAlignment = Alignment.Center){
                Text(
                    modifier = Modifier,
                    fontSize = 20.sp,
                    text = "KIEROWCY",
                    color = androidx.compose.ui.graphics.Color.White
                )
            }

            Image(painter = painterResource(id = R.drawable.driver), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color.White))
        }
    }
}






@Composable
fun OrderList(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(210.dp)
            //.width(210.dp)
            .clip(AlertDialogDefaults.shape)
            .clickable { onClick() }
            .background(GreyBG)
            .padding(15.dp)
            .fillMaxWidth()
    )
    {

        Column(verticalArrangement = Arrangement.Center) {
            Box(                    modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    text = "Lista zleceń",
                    fontSize = 20.sp
                )
            }
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = null, // Możesz dostosować opis dostępności
                )
                // Separacja między kropką a tekstem
                Spacer(modifier = Modifier.width(8.dp))

                // Tekst zadania
                Text(text = "zlecenie nr 1", fontSize = 20.sp)
            }
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically ) {
                Icon(
                    modifier = Modifier.clickable { println("Button Clicked!") },
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = null, // Możesz dostosować opis dostępności
                )
                // Separacja między kropką a tekstem
                Spacer(modifier = Modifier.width(8.dp))

                // Tekst zadania
                Text(text = "zlecenie nr 2", fontSize = 20.sp)
            }
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.clickable { println("Button Clicked!") },
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = null, // Możesz dostosować opis dostępności
                )
                // Separacja między kropką a tekstem
                Spacer(modifier = Modifier.width(8.dp))

                // Tekst zadania
                Text(text = "zlecenie nr 3", fontSize = 20.sp)
            }



        }

    }

}

@Composable
fun ShowUndoneOrder(order: Order) {
    Row(
        modifier = Modifier
//            .background(androidx.compose.material.MaterialTheme.colors.primary)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.dot),
            contentDescription = null,
            tint = androidx.compose.ui.graphics.Color.Red
            // Możesz dostosować opis dostępności
        )
        Spacer(Modifier.width(10.dp))
        androidx.compose.material.Text(
            text = "${order.orderTitle}",
            style = androidx.compose.material.MaterialTheme.typography.subtitle1,
            color = androidx.compose.ui.graphics.Color.White
        )
        Spacer(Modifier.width(10.dp))
        Log.d(Constants.TAG, "exe function ShowOrder----------------------------------: $order")
    }

}




@Composable
fun ImageBoxMap(openMap: () -> Unit){
    Box(modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .clip(AlertDialogDefaults.shape)
        .fillMaxSize()
        .clickable { openMap() }, contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.map), contentDescription = null,
            contentScale = ContentScale.Crop)
}}



@Composable
fun BoxSent(openBrowser: () -> Unit){
//    val intent by remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))}
    Box(modifier = Modifier
        .height(100.dp)
        //.width(210.dp)
        //.fillMaxSize()
        .clickable { openBrowser() }
        .background(androidx.compose.ui.graphics.Color.White)
        .clip(AlertDialogDefaults.shape)
        .fillMaxWidth()
        .border(
            width = 0.8.dp,
            color = colorResource(id = R.color.colorTextGray),
            shape = RoundedCornerShape(32.dp)
        )
        , contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.puesc2), contentDescription = null,
            modifier = Modifier
                .fillMaxSize())
    }}







@Composable
fun ButtonTest(openNet: () -> Unit){
    Button(onClick = { openNet() }) {

    }
}

@Composable
fun EtollBox(openBrowser: () -> Unit){
    Box(modifier = Modifier
        .height(100.dp)
        .width(220.dp)
        .clip(AlertDialogDefaults.shape)
        //.fillMaxSize()
        .clickable { openBrowser() }
        .background(tmsContainer), contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.etoll2), contentDescription = null,
            contentScale = ContentScale.Crop)
    }
}


@Composable
fun SettingsBox(){
    Box(modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .clip(AlertDialogDefaults.shape)
        .fillMaxSize()
        .background(colorResource(id = R.color.colorTest))
        .clickable { println("Button Clicked!") }, contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.settings), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color.White))
    }}


@Composable
fun WinietBox(buyTicket: () -> Unit){
    Box(modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .clip(AlertDialogDefaults.shape)
        .fillMaxSize()
        .background(colorResource(id = R.color.colorTest))
        .clickable { buyTicket() }, contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.winiet), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color.White))
    }}



@Composable
fun AutoSatNetBox(openBrowser: () -> Unit){
    Box(modifier = Modifier
        .height(100.dp)
        //.width(210.dp)
        //.fillMaxSize()
        .clickable { openBrowser() }
        .background(androidx.compose.ui.graphics.Color.White)
        .clip(AlertDialogDefaults.shape)
        .fillMaxWidth()
        , contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.autosatnet_logo), contentDescription = null,
            modifier = Modifier
                .fillMaxSize())
    }}



@Preview
@Composable
fun PreviewBBB() {
    checkDrivers(onClick = {})
}
