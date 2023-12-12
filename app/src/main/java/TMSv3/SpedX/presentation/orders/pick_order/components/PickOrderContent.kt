package TMSv3.SpedX.presentation.orders.pick_order.components

import TMSv3.SpedX.presentation.orders.pick_order.PickOrderViewModel
import TMSv3.SpedX.presentation.uiTheme.componentShapes
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PickOrderContent(
    padding: PaddingValues,
    orderID: String,
    viewModel: PickOrderViewModel = hiltViewModel(),
){

    val scrollState = rememberScrollState()
    LaunchedEffect(orderID) {
        viewModel.getOrderDetails(orderID)
    }
    LaunchedEffect(orderID) {
        viewModel.getCmrFromOrder(orderID)
    }

    PickOrder {item ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(componentShapes.small)
                .background(Color.White)// wazna kolejnosc
                .padding(28.dp)
                .verticalScroll(state = scrollState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val safeID = item.orderId ?: ""
                val safeFrom = item.startdestination ?: ""
                val safeTo = item.finaldestination ?: ""
                val safeCType = item.cargoName ?: ""
                val safeCWeight = item.cargoWeight ?: 0
                TitleOrderBox(orderID = safeID)
                Spacer(modifier = Modifier.height(35.dp))
                OrderLocationsBox(safeFrom, safeTo)
                Spacer(modifier = Modifier.height(35.dp))
                OrderCargoBox(safeCType, safeCWeight)
                Spacer(modifier = Modifier.height(35.dp))
                ShowCmr {imageUri ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageUri)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

            }


        }
    }



}


