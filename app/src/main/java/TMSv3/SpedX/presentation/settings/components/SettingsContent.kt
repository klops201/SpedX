package TMSv3.SpedX.presentation.settings.components

import TMSv3.SpedX.R
import TMSv3.SpedX.components.DataTextField
import TMSv3.SpedX.core.Constants
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@Composable
fun SettingsContent(padding: PaddingValues,
                    goBack: () -> Unit,
                    openVinejtes: () -> Unit,
                    openEtoll: () -> Unit,
                    openAsn: () -> Unit,
                    openPuesc: () -> Unit) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(30.dp)
//                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(color = colorResource(id = R.color.colorTest))
                    .clickable { openPuesc() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Dane serwisu PUESC",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(color = colorResource(id = R.color.colorTest))
                    .clickable { openEtoll() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Dane serwisu eToll",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(color = colorResource(id = R.color.colorTest))
                    .clickable { openVinejtes() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Dane serwisu SMSvinjetes",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(color = colorResource(id = R.color.colorTest))
                    .clickable { openAsn() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Dane serwisu AutoSatNet",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row (horizontalArrangement = Arrangement.SpaceBetween){
                TextButton(
                    onClick = { goBack() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(colorResource(id = R.color.colorTest))
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        text = "Cofnij",
                        color = Color.White
                    )


                }
            }

        }


    }
}

//@Preview
//@Composable
//fun checkSett(){
//
//    var orderID by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = "orderIdFB"
//                )
//            )
//        }
//    )
//
//
//
//        Box(
//            modifier = Modifier
//                .background(Color.White)
//                .fillMaxSize(), contentAlignment = Alignment.Center
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(30.dp)
////                .fillMaxSize()
//            ) {
//                Box(
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .fillMaxWidth()
//                        .height(50.dp)
//                        .clip(RoundedCornerShape(18.dp))
//                        .background(color = colorResource(id = R.color.colorTest)),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "Dane serwisu PUESC",
//                        color = Color.White,
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Box(
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .fillMaxWidth()
//                        .height(50.dp)
//                        .clip(RoundedCornerShape(18.dp))
//                        .background(color = colorResource(id = R.color.colorTest)),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "Dane serwisu eToll",
//                        color = Color.White,
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Box {
//                    DataTextField(modifier = Modifier
//                        .clip(RoundedCornerShape(18.dp))
//                        .background(color = colorResource(id = R.color.colorBg)),
//                        labelValue = "Tytuł zlecenia:",
//                        data = orderID,
//                        onDataValueChange = {}
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Row (horizontalArrangement = Arrangement.SpaceBetween){
//                    TextButton(
//                        onClick = {},
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(18.dp))
//                            .background(colorResource(id = R.color.colorTest))
//                    ) {
//                        Text(
//                            modifier = Modifier.padding(horizontal = 15.dp),
//                            text = "Cofnij",
//                            color = Color.White
//                        )
//                    }
//
//
//                    TextButton(
//                        onClick = {},
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(18.dp))
//                            .background(colorResource(id = R.color.colorTest))
//                    ) {
//                        Text(
//                            modifier = Modifier.padding(horizontal = 15.dp),
//                            text = "Aktualizuj",
//                            color = Color.White
//                        )
//                    }
//
//
//
//
//
//                }
//
//            }
//
//
//        }
//    }
