package TMSv3.SpedX.presentation.orders.pick_order.components

import TMSv3.SpedX.R
import TMSv3.SpedX.presentation.uiTheme.md_theme_light_primary
import TMSv3.SpedX.presentation.uiTheme.md_theme_light_primaryContainer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest



@Composable
fun TitleOrderBox(orderID: String, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
//            .padding(10.dp)
//            .height(100.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(md_theme_light_primary)
            .fillMaxWidth()
            , contentAlignment = Alignment.Center

    ) {
        //Text(modifier = Modifier.padding(all = 10.dp), textAlign = TextAlign.Center, text = currentDateAndTime, fontSize = 30.sp)
//        Text(text = "Sprawdź dane zlecenia ", modifier = Modifier.padding(5.dp).align(Alignment.TopCenter), fontSize = 20.sp)
        Text(text = "Numer zamówienia: $orderID", modifier = Modifier
            .padding(5.dp)
            .align(Alignment.Center), fontSize = 20.sp, color = Color.White)

    }

}

//@Composable
//private fun TransformableSample() {
//    // Ustaw wszystkie stany transformacji
//    var scale by remember { mutableStateOf(1f) }
//    var rotation by remember { mutableStateOf(0f) }
//    var offset by remember { mutableStateOf(Offset.Zero) }
//    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
//        scale *= zoomChange
//        rotation += rotationChange
//        offset += offsetChange
//    }}



@Composable
fun OrderLocationsBox(startLoc: String, finalLoc: String) {


        Box(
                    modifier = Modifier
//                        .padding(10.dp)
                        .clip(AlertDialogDefaults.shape)
                        .background(md_theme_light_primaryContainer)
                        .fillMaxWidth(),
        ) {
            Row (modifier = Modifier
                .padding(10.dp)
                .height(IntrinsicSize.Min)) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()) {
                    Box (modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.BottomEnd) {
                        Icon(
                            modifier = Modifier,
                            painter = painterResource(id = R.drawable.dot1),
                            contentDescription = null, // Możesz dostosować opis dostępności
                        )
                    }
                    Divider(
                        color = Color.LightGray,
                        modifier = Modifier
                            .weight(2f)
                            .width(2.dp)
                    )
                    Box (modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.TopCenter) {
                        Icon(
                            modifier = Modifier,
                            painter = painterResource(id = R.drawable.dot1),
                            contentDescription = null,
                            tint = colorResource(id = R.color.colorTest)
                        )
                    }

                }
                Spacer(modifier = Modifier.width(10.dp))


                Column (verticalArrangement = Arrangement.Center){
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            ,
                        textAlign = TextAlign.Start,
                        text = "Miejsce załadunku",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.colorText)
                    )
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            ,
                        textAlign = TextAlign.Center,
                        text = "$startLoc",
                        fontSize = 20.sp
                    )
                    Divider(color = Color.Black, thickness = 1.dp)
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                        ,
                        textAlign = TextAlign.Start,
                        text = "Miejsce rozładunku",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.colorText)
                    )
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                        ,
                        textAlign = TextAlign.Center,
                        text = "$finalLoc",
                        fontSize = 20.sp
                    )
                }
            }


        }
        Spacer(modifier = Modifier.width(8.dp))




}

@Composable
fun OrderCargoBox(cargoName: String, cargoWeight: Int) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(md_theme_light_primaryContainer), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                text = "typ towaru: $cargoName",
                fontSize = 20.sp, color = Color.Black)

        }
        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                text = "masa: $cargoWeight kg",
                fontSize = 20.sp, color = Color.Black
            )
        }
    }

}



@Composable
fun doneCheckbox(done: Boolean, change: () -> Unit ){
    var doneState = remember { mutableStateOf(done) }
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        if(doneState.value == false){
            Box (modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .padding(3.dp)){
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = md_theme_light_primaryContainer),
                    onClick = {
                        change()
                        doneState.value = !doneState.value
                    }) {
                    Text(
                        text = "Oznacz zlecenie jako wykonane",
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
            }
            Box(modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(Color.Red)
                .padding(6.dp), contentAlignment = Alignment.Center) {
                    Text(text = "Status zlecenia: w trakcie", color = Color.White, textAlign = TextAlign.Center)
//                        Text(text = "W trakcie", color = Color.White)

            }
        }else {
            Column(modifier = Modifier
                .clip(RoundedCornerShape(55.dp))
                .background(colorResource(id = R.color.colorGreenDone))
                .fillMaxSize(), verticalArrangement = Arrangement.SpaceAround
                , horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Status zlecenia:", color = Color.White)
                Text(text = "Zakończone", color = Color.White)

            }
        }

    }
}


@Composable
fun CmrBox(imageUri: String, afterClick: () -> Unit){


        Box (modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(md_theme_light_primaryContainer)){
            Row(modifier = Modifier.fillMaxWidth()
                .height(170.dp),verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = "Sprawdź swój list przewozowy",
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f),
                fontSize = 20.sp,
                color = Color.Black
            )
            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(18.dp))
                    .padding(6.dp)
                    .fillMaxHeight()
                    .clickable {
                        afterClick()
                    },
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUri)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )


           }

        }




    }








@Preview
@Composable
fun CheckFunctPrev(){
    CmrBox(imageUri = "asasas") {
        
    }
}