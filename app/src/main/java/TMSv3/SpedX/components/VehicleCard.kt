package TMSv3.SpedX.components

import TMSv3.SpedX.R
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.core.Utils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.FlipCameraAndroid
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.md_theme_light_primaryContainer

@Composable
fun VehicleCard(position: Position) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(md_theme_light_primaryContainer)
            .height(250.dp)
            .width(186.dp)
    ) {

        val safeIginition = position.ignitionState ?: false
        val safeTimeStamp = position.timeStampUnix ?: 0
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
//                    .weight(1f)
                        .fillMaxHeight(),
                    imageVector = Icons.Filled.LocalShipping,
                    contentDescription = null,
                    tint = Color.Black
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .weight(1f),
                    , text = "ID: ${position.vehicleId}", textAlign = TextAlign.End
                )
            }




            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                text = "${position.vehicleName}",
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                color = Color.Black
            )




            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .fillMaxSize(),
                    imageVector = Icons.Filled.Speed,
                    contentDescription = null,
                    tint = Color.Black
                )

                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = "${position.speed} km/h",
                    textAlign = TextAlign.End,
                    fontSize = 15.sp,
                    color = Color.Black
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .fillMaxSize(),
                    imageVector = Icons.Filled.LocalGasStation,
                    contentDescription = null,
                    tint = Color.Black
                )

                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = "${position.fuelLevel} L",
                    textAlign = TextAlign.End,
                    fontSize = 15.sp,
                    color = Color.Black
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .fillMaxSize(),
                    imageVector = Icons.Filled.Bolt,
                    contentDescription = null,
                    tint = Color.Black
                )


                if (safeIginition) {
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "stacyjka włączona",
                        textAlign = TextAlign.End,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "stacyjka wyłączona",
                        textAlign = TextAlign.End,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .fillMaxSize(),
                    imageVector = Icons.Filled.Route,
                    contentDescription = null,
                    tint = Color.Black
                )

                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = "${position.counter} km",
                    textAlign = TextAlign.End,
                    fontSize = 15.sp,
                    color = Color.Black
                )

            }
            val date = Utils.TimeStampUnixConverter(safeTimeStamp)

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "dane z : $date",
                fontSize = 10.sp,
                textAlign = TextAlign.End
            )


        }

    }
}


@Preview
@Composable
fun vehCard() {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(md_theme_light_primaryContainer)
            .height(250.dp)
            .width(250.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
//                    .weight(1f)
                        .fillMaxHeight(),
                    imageVector = Icons.Filled.LocalShipping,
                    contentDescription = null,
                    tint = Color.Black
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .weight(1f),
                    , text = "ID: v1566", textAlign = TextAlign.End
                )
            }




            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                text = "BS2040", textAlign = TextAlign.Center, fontSize = 15.sp, color = Color.Black
            )


            Column(verticalArrangement = Arrangement.SpaceEvenly) {
//            Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .fillMaxSize(),
                        imageVector = Icons.Filled.Speed,
                        contentDescription = null,
                        tint = Color.Black
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "0 km/h",
                        textAlign = TextAlign.End,
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                }
//            Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .fillMaxSize(),
                        imageVector = Icons.Filled.LocalGasStation,
                        contentDescription = null,
                        tint = Color.Black
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "306.4 L",
                        textAlign = TextAlign.End,
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                }
//            Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .fillMaxSize(),
                        imageVector = Icons.Filled.Bolt,
                        contentDescription = null,
                        tint = Color.Black
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "włączony",
                        textAlign = TextAlign.End,
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                }
//            Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .fillMaxSize(),
                        imageVector = Icons.Filled.Route,
                        contentDescription = null,
                        tint = Color.Black
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "1423905 km",
                        textAlign = TextAlign.End,
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                }

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "dane z : xxxxxxx",
                    textAlign = TextAlign.End
                )


            }


        }

    }


}

