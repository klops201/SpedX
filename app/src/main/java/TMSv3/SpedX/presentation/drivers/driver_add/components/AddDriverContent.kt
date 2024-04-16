package TMSv3.SpedX.presentation.drivers.driver_add.components

import TMSv3.SpedX.R
import TMSv3.SpedX.components.DataTextField
import TMSv3.SpedX.components.NumberField
import TMSv3.SpedX.components.SmallSpacer
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.drivers.driver_add.AddDriverViewModel
import android.util.Log
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun AddDriverContent(
    padding: PaddingValues,
    addDriver: (
        driverName: String,
        driverPhoneNr: Int,
        driverID: String,
        vehicleID: String
    ) -> Unit,
    goBack: () -> Unit,
    navigateToDrivers: () -> Unit,
    showSnackBar: () -> Unit,
    viewModel: AddDriverViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(viewModel) {
        viewModel.fetchDrivers()
    }

    val fetchedDrivers by viewModel.drivers.observeAsState(emptyList())


    var showDriversList by remember {
        mutableStateOf(false)
    }


    var driverName by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )

    var driverPhoneNr by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )

    var driverId by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )

    var vehicleId by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
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
                    .padding(vertical = 60.dp, horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(18.dp))
                    .background(colorResource(id = R.color.colorTest)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize()
                        .verticalScroll(state = scrollState),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DataTextField(modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(color = colorResource(id = R.color.colorBg)),
                        labelValue = "Imię i nazwisko",
                        data = driverName,
                        onDataValueChange = { newValue ->
                            driverName = newValue
                        }
                    )
                    Spacer(modifier = Modifier.height(17.dp))
                    DataTextField(modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(color = colorResource(id = R.color.colorBg)),
                        labelValue = "Numer telefonu",
                        data = driverPhoneNr,
                        onDataValueChange = { newValue ->
                            driverPhoneNr = newValue
                        }
                    )
                    Spacer(modifier = Modifier.height(17.dp))
                    DataTextField(modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(color = colorResource(id = R.color.colorBg)),
                        labelValue = "ID kierowcy",
                        data = driverId,
                        onDataValueChange = { newValue ->
                            driverId = newValue
                        }
                    )
                    Spacer(modifier = Modifier.height(17.dp))
                    DataTextField(modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(color = colorResource(id = R.color.colorBg)),
                        labelValue = "ID pojazdu",
                        data = vehicleId,
                        onDataValueChange = { newValue ->
                            vehicleId = newValue
                        }
                    )
                    Spacer(modifier = Modifier.height(17.dp))
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

                        SmallSpacer()

                        IconButton(
                            onClick = { showDriversList = !showDriversList }, modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .weight(3f)
                                .padding(horizontal = 20.dp)
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
                                    text = "Kierowcy w ASN",
                                    fontSize = 15.sp, textAlign = TextAlign.Center
                                )
                            }

                        }
                    }

                    Spacer(modifier = Modifier.width(15.dp))
                }
            }
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    onClick = {
                        goBack()
                        Log.d(Constants.TAG, "KLIIIIIK w anuluj ")
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(colorResource(id = R.color.colorTest))
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        text = "Anuluj",
                        color = Color.White
                    )
                }


//                Button(shape = CircleShape,
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = colorResource(id = R.color.colorBg),
//                        contentColor = colorResource(id = R.color.colorTest)
//                    ),
//                    onClick = {
//                        if (areFieldsFilled(
//                                driverName, driverPhoneNr, driverId,
//                                vehicleId
//                            )
//                        ) {
//                            addDriver(
//                                driverName.text,
//                                driverPhoneNr.text.toInt(),
//                                driverId.text,
//                                vehicleId.text,
//                            )
//                            navigateToDrivers()
//                        } else {
//                            showSnackBar()
//                        }
//                    }
//                ) {
//                    Text(
//                        text = Constants.ADD_DRIVER,
//                        fontSize = 15.sp
//                    )
//                }


                TextButton(
                    onClick = {
                        if (areFieldsFilled(
                                driverName, driverPhoneNr, driverId,
                                vehicleId
                            )
                        ) {
                            addDriver(
                                driverName.text,
                                driverPhoneNr.text.toInt(),
                                driverId.text,
                                vehicleId.text,
                            )
                            navigateToDrivers()
                        } else {
                            showSnackBar()
                        }
                    },
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

    if (showDriversList) {
        Log.d(Constants.TAG, "pokazta ziutkow $fetchedDrivers")
        Dialog(
            onDismissRequest = { showDriversList = !showDriversList },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(5.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.guide1),
//                        contentDescription = "imageDescription",
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .clip(RoundedCornerShape(16.dp))
//                    )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        //                    contentPadding = PaddingValues(4.dp)
                    ) {
                        items(fetchedDrivers) { driver ->

                            val safeDrivID = driver.driverId ?: ""
                            val safeDrivName = driver.driverName ?: ""
                            val safeDrivSurName = driver.driverSurname ?: ""


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "ID: $safeDrivID",
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center
                                )
                                // Dodaj dodatkowe kolumny lub elementy wiersza tutaj w razie potrzeby
                                Spacer(modifier = Modifier.width(20.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = safeDrivName,
                                        modifier = Modifier,
                                        style = MaterialTheme.typography.body1,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = safeDrivSurName,
                                        modifier = Modifier,
                                        style = MaterialTheme.typography.body1,
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                            Divider(modifier = Modifier.fillMaxWidth())
                        }

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = { showDriversList = false },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            androidx.compose.material3.Text("Zamknij")
                        }
                    }


                }

            }
//                }
        }
    }


}

private fun areFieldsFilled(
    driverName: TextFieldValue, driverPhoneNr: TextFieldValue, driverId: TextFieldValue,
    vehicleId: TextFieldValue
): Boolean {
    return driverName.text.isNotEmpty() &&
            driverPhoneNr.text.isNotEmpty() &&
            driverId.text.isNotEmpty() &&
            vehicleId.text.isNotEmpty()
}

