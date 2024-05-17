package TMSv3.SpedX.presentation.drivers.driver_edit.components

import TMSv3.SpedX.R
import TMSv3.SpedX.components.DataTextField
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.drivers.driver_edit.EditDriverViewModel
import TMSv3.SpedX.presentation.drivers.drivers_main.DriverMainViewModel
import TMSv3.SpedX.presentation.drivers.drivers_main.components.GetASNDataApiED
import TMSv3.SpedX.presentation.drivers.drivers_main.components.GetDrivers
import TMSv3.SpedX.presentation.drivers.drivers_main.components.ShowDrivers
import TMSv3.SpedX.presentation.orders.edit_order.EditOrderViewModel
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch

@Composable
fun EditDriverContent(
    padding: PaddingValues,
    driverID: String,
    goBack: () -> Unit,
    editDriver: (driverName: String, driverPhoneNr: Int, driverID: String, vehicleID: String) -> Unit,
    navigateToDrivers: () -> Unit,
    scaffoldST: ScaffoldState,
    viewModel: EditDriverViewModel = hiltViewModel(),
    viewModel1: DriverMainViewModel = hiltViewModel(),
) {

    LaunchedEffect(driverID) {
        Log.d(Constants.TAG, "SZUKANIE DRIVERA $driverID")
        viewModel.getDriverDetails(driverID)
    }


    LaunchedEffect(viewModel) {
        viewModel1.getAsnData()
    }


    GetASNDataApiED{item ->

        val user = item.user ?: "błedne dane"
        val customer = item.customer ?: "błedne dane"
        val pass = item.gate ?: "błedne dane"



    LaunchedEffect(viewModel) {
        viewModel1.fetchVehicles(user.lowercase(), customer.lowercase(), pass)
    }


    val fetchedVehicles by viewModel1.vehicles.observeAsState(emptyList())


    Log.d(Constants.TAG, "przechwycone pojazdy ED start---------- :$fetchedVehicles ")


    var showDrivers by remember {
        mutableStateOf(false)
    }


    var deleteDriver by remember {
        mutableStateOf(false)
    }



//    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()





    GetDriver { driver ->


//        LaunchedEffect(viewModel) {
//            viewModel1.fetchVehicles(user.lowercase(), customer.lowercase(), pass)
//        }

        val safeDriverId = driver.driverId ?: ""
        val safeFBId = driver.firebaseID ?: ""
        val safeDriverName = driver.driverName ?: ""
        val safeDriverPhoneNr = driver.driverPhoneNr ?: 0
        val safeVehicleId = driver.vehicleId ?: ""

        var driverId by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = safeDriverId
                    )
                )
            }
        )
        var driverName by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = safeDriverName
                    )
                )
            }
        )
        var vehicleId by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = safeVehicleId
                    )
                )
            }
        )
        var driverPhoneNr by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = safeDriverPhoneNr.toString()
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
                            DataTextField(modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                                labelValue = "ID ",
                                data = driverId,
                                onDataValueChange = { newValue ->
                                    driverId = newValue
                                }
                            )


                        }
                        Spacer(modifier = Modifier.height(30.dp))

                        DataTextField(modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Imię i nazwisko ",
                            data = driverName,
                            onDataValueChange = { newValue ->
                                driverName = newValue
                            }
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

                            DataTextField(modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                                labelValue = "Nr tel ",
                                data = driverPhoneNr,
                                onDataValueChange = { newValue ->
                                    driverPhoneNr = newValue
                                }
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

                            DataTextField(modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                                labelValue = "ID pojazdu ",
                                data = vehicleId,
                                onDataValueChange = { newValue ->
                                    vehicleId = newValue
                                }
                            )


                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { showDrivers = !showDrivers }, modifier = Modifier
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
                                onClick = {
                                    deleteDriver = true
                                    Log.d(Constants.TAG, "kliknięto w ikonę $deleteDriver")
                                }, modifier = Modifier
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
                            text = "Anuluj",
                            color = Color.White
                        )
                    }

                    TextButton(
                        onClick = {
                            editDriver(
                                driverName.text,
                                driverPhoneNr.text.toInt(),
                                driverId.text,
                                vehicleId.text
                            )
                            navigateToDrivers()
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
    }

    LaunchedEffect(deleteDriver) {
        if (deleteDriver) {
            Log.d(Constants.TAG, "Odpalono LaunchedEffect: $deleteDriver")
            val snackbarResult = scaffoldST.snackbarHostState.showSnackbar(
                message = "Usuwanie kierowcy...",
                duration = SnackbarDuration.Long,
                actionLabel = "Anuluj"
            )
            Log.d(Constants.TAG, "Snackbar result: $snackbarResult")

            when (snackbarResult) {
                SnackbarResult.Dismissed -> {
                    viewModel.deleteDriver(driverID, true)
                    navigateToDrivers()
                }

                SnackbarResult.ActionPerformed -> {
                    viewModel.deleteDriver(driverID, false)
                }
            }

            // Zresetuj stan deleteDriver po zakończeniu interakcji
            deleteDriver = false
        }
    }





    if (showDrivers) {
        Log.d(Constants.TAG, "pokazta ciezarowki $fetchedVehicles")
        Dialog(
            onDismissRequest = { showDrivers = !showDrivers },
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
                        items(fetchedVehicles) { vehicle ->

                            val safeVehID = vehicle.vehicleId ?: ""
                            val safeVehName = vehicle.vehicleName ?: ""


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "ID: $safeVehID",
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center
                                )
                                // Dodaj dodatkowe kolumny lub elementy wiersza tutaj w razie potrzeby
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(
                                    text = safeVehName,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center
                                )

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
                        androidx.compose.material3.TextButton(
                            onClick = { showDrivers = false },
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