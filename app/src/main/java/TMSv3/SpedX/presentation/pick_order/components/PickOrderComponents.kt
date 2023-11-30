package TMSv3.SpedX.presentation.pick_order.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants.ORDER_DETAILS
import TMSv3.SpedX.presentation.profile.components.GetDayDate
import TMSv3.SpedX.presentation.uiTheme.tmsOnPrimary
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TitleOrderBox(orderID: String) {

    Box(
        modifier = Modifier
            .height(100.dp)
            .width(160.dp)
            .clip(AlertDialogDefaults.shape)
            .background(tmsOnPrimary)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        //Text(modifier = Modifier.padding(all = 10.dp), textAlign = TextAlign.Center, text = currentDateAndTime, fontSize = 30.sp)
        Text(text = orderID)

    }

}


@Composable
fun OrderLocationsBox(startLoc: String, finalLoc: String) {

    Row(
        Modifier
            .fillMaxWidth()
            .clip(AlertDialogDefaults.shape)
            .background(tmsOnPrimary), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
                    modifier = Modifier
                        .weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                text = "z $startLoc",
                fontSize = 20.sp
            )
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
                text = "do $finalLoc",
                fontSize = 20.sp
            )
        }
    }

}

@Composable
fun OrderCargoBox(cargoName: String, cargoWeight: Int) {

    Row(
        Modifier
            .fillMaxWidth()
            .clip(AlertDialogDefaults.shape)
            .background(tmsOnPrimary), verticalAlignment = Alignment.CenterVertically
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
                fontSize = 20.sp
            )
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
                fontSize = 20.sp
            )
        }
    }

}





@Preview
@Composable
fun CheckFunctPrev(){
    OrderCargoBox(cargoName = "test", cargoWeight = 100)
}