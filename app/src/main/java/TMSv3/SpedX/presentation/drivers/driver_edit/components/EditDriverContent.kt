package TMSv3.SpedX.presentation.drivers.driver_edit.components

import TMSv3.SpedX.R
import TMSv3.SpedX.components.DataTextField
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.uiTheme.GreyBG
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun EditDriverContent(
    padding: PaddingValues,
    goBack: () -> Unit
) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 30.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(18.dp))
                    .background(colorResource(id = R.color.colorTest))
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 30.dp, horizontal = 15.dp)
                        .fillMaxSize()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .weight(1f)
                                .fillMaxSize(),
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        TextField(
                            value = "ID", onValueChange = {}, modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                            textStyle = TextStyle(color = Color.Black)
                        )


                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    TextField(
                        value = "Imię i nazwisko", onValueChange = {}, modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                        textStyle = TextStyle(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .weight(1f)
                                .fillMaxSize(),
                            imageVector = Icons.Filled.Phone,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        TextField(
                            value = "Nr tel", onValueChange = {}, modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                            textStyle = TextStyle(color = Color.Black)
                        )


                    }

                    Spacer(modifier = Modifier.height(30.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .weight(1f)
                                .fillMaxSize(),
                            imageVector = Icons.Filled.LocalShipping,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        TextField(
                            value = "ID pojazdu", onValueChange = {}, modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                            textStyle = TextStyle(color = Color.Black)
                        )


                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ }, modifier = Modifier
                                .weight(3f)
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg))
                        ) {
                            Row(
                                modifier = Modifier.padding(2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(25.dp)
                                        .fillMaxSize(),
                                    imageVector = Icons.Filled.List,
                                    contentDescription = null,
                                    tint = Color.Black,
                                )
                                Text(
                                    text = "Sprawdź pojazdy w bazie ASN",
                                    fontSize = 15.sp, textAlign = TextAlign.Center
                                )
                            }

                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        IconButton(
                            onClick = { /*TODO*/ }, modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg))
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(25.dp)
                                    .fillMaxSize(),
                                imageVector = Icons.Filled.DeleteForever,
                                contentDescription = null,
                                tint = Color.Red,
                            )

                        }
                    }


                }
            }
//            Spacer(modifier = Modifier.height(60.dp))

            Row(
                modifier = Modifier
                    .padding(30.dp)

                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    onClick = { goBack() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(colorResource(id = R.color.colorTest))
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        text = "anuluj",
                        color = Color.White
                    )
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(colorResource(id = R.color.colorTest))
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        text = "Zapisz",
                        color = Color.White
                    )
                }

            }


        }


    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun editDriver() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 30.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(18.dp))
                    .background(colorResource(id = R.color.colorTest))
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 30.dp, horizontal = 15.dp)
                        .fillMaxSize()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .weight(1f)
                                .fillMaxSize(),
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        TextField(
                            value = "ID", onValueChange = {}, modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                            textStyle = TextStyle(color = Color.Black)
                        )


                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    TextField(
                        value = "Imię i nazwisko", onValueChange = {}, modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                        textStyle = TextStyle(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .weight(1f)
                                .fillMaxSize(),
                            imageVector = Icons.Filled.Phone,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        TextField(
                            value = "Nr tel", onValueChange = {}, modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                            textStyle = TextStyle(color = Color.Black)
                        )


                    }

                    Spacer(modifier = Modifier.height(30.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .weight(1f)
                                .fillMaxSize(),
                            imageVector = Icons.Filled.LocalShipping,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        TextField(
                            value = "ID pojazdu", onValueChange = {}, modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                            textStyle = TextStyle(color = Color.Black)
                        )


                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ }, modifier = Modifier
                                .weight(3f)
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg))
                        ) {
                            Row(
                                modifier = Modifier.padding(2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(25.dp)
                                        .fillMaxSize(),
                                    imageVector = Icons.Filled.List,
                                    contentDescription = null,
                                    tint = Color.Black,
                                )
                                Text(
                                    text = "Sprawdź pojazdy w bazie ASN",
                                    fontSize = 15.sp, textAlign = TextAlign.Center
                                )
                            }

                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        IconButton(
                            onClick = { /*TODO*/ }, modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg))
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(25.dp)
                                    .fillMaxSize(),
                                imageVector = Icons.Filled.DeleteForever,
                                contentDescription = null,
                                tint = Color.Red,
                            )

                        }
                    }


                }
            }
//            Spacer(modifier = Modifier.height(60.dp))

            Row(
                modifier = Modifier
                    .padding(30.dp)

                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(colorResource(id = R.color.colorTest))
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        text = "anuluj",
                        color = Color.White
                    )
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(colorResource(id = R.color.colorTest))
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        text = "Zapisz",
                        color = Color.White
                    )
                }

            }


        }


    }
}