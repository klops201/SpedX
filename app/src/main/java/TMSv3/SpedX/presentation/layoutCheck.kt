package TMSv3.SpedX.presentation

import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.presentation.orders.pick_order.components.OrderCargoBox
import TMSv3.SpedX.presentation.orders.pick_order.components.OrderLocationsBox
import TMSv3.SpedX.presentation.orders.pick_order.components.PickOrder
import TMSv3.SpedX.presentation.orders.pick_order.components.ShowCmr
import TMSv3.SpedX.presentation.orders.pick_order.components.TitleOrderBox
import TMSv3.SpedX.presentation.uiTheme.componentShapes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import TMSv3.SpedX.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp


@Composable
fun PickOrderContentt(
){

    val scrollState = rememberScrollState()

    val item = Order(
        firestoreID = "1",
        orderTitle = "Test Order",
        orderId = "12345",
        position = "Position",
        finaldestination = "Warszawa",
        startdestination = "Suwałki",
        cargoName = "Stal",
        cargoWeight = 100,
        driverId = "Driver ID",
        cmrId = "CMR ID",
        createAt = "2023-12-18"
    )

    Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
        Image(painter = painterResource(id = R.drawable.world_connections),
            contentDescription ="",
            contentScale = ContentScale.FillBounds,)

        Box(modifier = Modifier
            .padding(top = 220.dp)
            .clip(shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
            .background(colorResource(id = R.color.colorTest))){
            Column(
                modifier = Modifier
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val safeID = item.orderId ?: ""
                val safeFrom = item.startdestination ?: ""
                val safeTo = item.finaldestination ?: ""
                val safeCType = item.cargoName ?: ""
                val safeCWeight = item.cargoWeight ?: 0
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Sprawdź dane zlecenia", modifier = Modifier.padding(5.dp), fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.height(15.dp))
                TitleOrderBox(orderID = safeID)
                Spacer(modifier = Modifier.height(35.dp))
                OrderLocationsBox(safeFrom, safeTo)
                Spacer(modifier = Modifier.height(35.dp))
                OrderCargoBox(safeCType, safeCWeight)
                Spacer(modifier = Modifier.height(35.dp))

            }
        }


        }
}

@Preview
@Composable
fun PreviewPickOrderContent() {
    PickOrderContentt()
}


