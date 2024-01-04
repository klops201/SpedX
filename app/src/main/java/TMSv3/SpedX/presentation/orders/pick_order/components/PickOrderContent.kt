package TMSv3.SpedX.presentation.orders.pick_order.components

import TMSv3.SpedX.R
import TMSv3.SpedX.presentation.orders.pick_order.PickOrderViewModel
import TMSv3.SpedX.presentation.uiTheme.componentShapes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


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

    var imageFullScreen by remember { mutableStateOf(false)}

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .graphicsLayer {
                        alpha = 1f - (scrollState.value.toFloat() / scrollState.maxValue)
                        translationY = 0.5f * scrollState.value
                    }, contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.world_connections),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )
            }
            PickOrder { item ->
                Box(
                    modifier = Modifier
                        //            .padding(top = 220.dp)
                        .clip(shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
                        .background(colorResource(id = R.color.colorTest))
                    //                    .verticalScroll(state = scrollState)
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
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Sprawdź dane zlecenia",
                            modifier = Modifier.padding(5.dp),
                            fontSize = 20.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        TitleOrderBox(orderID = safeID)
                        Spacer(modifier = Modifier.height(35.dp))
                        OrderLocationsBox(safeFrom, safeTo)
                        Spacer(modifier = Modifier.height(35.dp))
                        OrderCargoBox(safeCType, safeCWeight)
                        Spacer(modifier = Modifier.height(35.dp))
                        ShowCmr { imageUri ->
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {

                                    Text(
                                        text = "Sprawdź swój list przewozowy",
                                        modifier = Modifier.padding(5.dp).weight(1f),
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )

                                    AsyncImage(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .clickable {
                                              imageFullScreen = true
                                            },
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(imageUri)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = null
                                    )
                                    if (imageFullScreen) {
                                        Dialog(onDismissRequest = {imageFullScreen = false}, properties = DialogProperties(usePlatformDefaultWidth = false)) {
                                            // Custom layout for the dialog
                                            Surface(
                                                modifier = Modifier.fillMaxSize(),
                                            ) {
                                                Column(
                                                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    var scale by remember { mutableStateOf(1f) }
                                                    var rotation by remember { mutableStateOf(0f) }
                                                    var offset by remember { mutableStateOf(Offset.Zero) }
                                                    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
                                                        scale *= zoomChange
                                                        rotation += rotationChange
                                                        offset += offsetChange
                                                    }
                                                    AsyncImage(
                                                        modifier = Modifier.fillMaxSize()
                                                            .graphicsLayer(
                                                                scaleX = scale,
                                                                scaleY = scale,
                                                                rotationZ = rotation,
                                                                translationX = offset.x,
                                                                translationY = offset.y
                                                            )
                                                            // add transformable to listen to multitouch transformation events
                                                            // after offset
                                                            .transformable(state = state)
                                                            .fillMaxSize(),
                                                        model = ImageRequest.Builder(LocalContext.current)
                                                            .data(imageUri)
                                                            .crossfade(true)
                                                            .build(),
                                                        contentDescription = null
                                                    )

                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }



}


