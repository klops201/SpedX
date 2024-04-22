package TMSv3.SpedX.components

import TMSv3.SpedX.R
import TMSv3.SpedX.domain.model.Driver
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.md_theme_light_primaryContainer

@Composable
fun DriverCard(driver: Driver, navigateEditDriver: () -> Unit) {

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(md_theme_light_primaryContainer)
            .height(250.dp)
            .width(186.dp)
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
                    .height(35.dp)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .weight(1f)
                        .fillMaxSize(),
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = Color.Black,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = "ID: ${driver.driverId}", textAlign = TextAlign.Center
                )
            }




            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = "${driver.driverName}", textAlign = TextAlign.Center, fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .fillMaxSize(),
                    imageVector = Icons.Filled.Phone,
                    contentDescription = null,
                    tint = Color.Black
                )

                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = "${driver.driverPhoneNr}",
                    textAlign = TextAlign.End,
                    fontSize = 20.sp,
                    color = Color.Black
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .fillMaxSize(),
                    imageVector = Icons.Filled.LocalShipping,
                    contentDescription = null,
                    tint = Color.Black
                )

                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = "${driver.vehicleId}",
                    textAlign = TextAlign.End,
                    fontSize = 20.sp,
                    color = Color.Black
                )

            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(35.dp)
//                    .padding(3.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Icon(
//                    modifier = Modifier
//                        .size(30.dp)
//                        .fillMaxSize()
//                        .clickable {
//
//                        },
//                    imageVector = Icons.Filled.Edit,
//                    contentDescription = null,
//                    tint = Color.Black
//                )
//
//                Icon(
//                    modifier = Modifier
//                        .size(30.dp)
//                        .fillMaxSize()
//                        .clickable {},
//                    imageVector = Icons.Filled.Delete,
//                    contentDescription = null,
//                    tint = Color.Black
//                )
//
//            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                androidx.compose.material.TextButton(
                    onClick = {navigateEditDriver()},
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(colorResource(id = R.color.colorTest))
                ) {
                    androidx.compose.material.Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Edytuj/ usuń",
                        color = Color.White, textAlign = TextAlign.Center, fontSize = 12.sp
                    )
                }

            }


        }

    }
}


@Preview
@Composable
fun cardd() {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp))
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.colorTest)),
                contentAlignment = Alignment.BottomCenter
            ) {
                androidx.compose.material.Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = "Zarządzaj kierowcami",
                    fontSize = 25.sp,
                    color = Color.White
                )
            }

            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(md_theme_light_primaryContainer)
                    .height(250.dp)
                    .width(230.dp)
//                    .weight(1f)
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
                            .height(35.dp)
                            .padding(3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.driver),
//                            contentDescription = null,
//                            contentScale = ContentScale.FillHeight,
//                            modifier = Modifier
//                                .weight(1f)
//                                .fillMaxSize(),
//                            colorFilter = ColorFilter.tint(Color.Black),
//                            alignment = Alignment.TopStart
//                        )
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .weight(1f)
                                .fillMaxSize(),
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = Color.Black,
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = "ID: d1167", textAlign = TextAlign.Center
                        )
                    }




                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Marek Ziutek", textAlign = TextAlign.Center, fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .padding(3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .fillMaxSize(),
                            imageVector = Icons.Filled.Phone,
                            contentDescription = null,
                            tint = Color.Black
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            text = "555 223 222",
                            textAlign = TextAlign.End,
                            fontSize = 20.sp,
                            color = Color.Black
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .padding(3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .fillMaxSize(),
                            imageVector = Icons.Filled.LocalShipping,
                            contentDescription = null,
                            tint = Color.Black
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            text = "v1566",
                            textAlign = TextAlign.End,
                            fontSize = 20.sp,
                            color = Color.Black
                        )

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.material.TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(horizontal = 40.dp)
                                .clip(RoundedCornerShape(18.dp))
                                .background(colorResource(id = R.color.colorTest))
                        ) {
                            androidx.compose.material.Text(
                                modifier = Modifier.fillMaxSize(),
                                text = "Edytuj/ usuń",
                                color = Color.White, textAlign = TextAlign.Center, fontSize = 12.sp
                            )
                        }

                    }
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(30.dp)
//                            .padding(3.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            modifier = Modifier
//                                .size(30.dp)
//                                .fillMaxSize(),
//                            imageVector = Icons.Filled.Update,
//                            contentDescription = null,
//                            tint = Color.Black
//                        )
//
//                        Text(
//                            modifier = Modifier
//                                .fillMaxSize(),
//                            text = "czas jazdy[min]:                  35",
//                            textAlign = TextAlign.End,
//                            fontSize = 15.sp,
//                            color = Color.Black
//                        )
//
//                    }
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(30.dp)
//                            .padding(3.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            modifier = Modifier
//                                .size(30.dp)
//                                .fillMaxSize(),
//                            imageVector = Icons.Filled.Block,
//                            contentDescription = null,
//                            tint = Color.Black
//                        )
//
//                        Text(
//                            modifier = Modifier
//                                .fillMaxSize(),
//                            text = "czas postoju[min]:                  5",
//                            textAlign = TextAlign.End,
//                            fontSize = 15.sp,
//                            color = Color.Black
//                        )
//
//                    }


                }

            }

        }
    }


}

