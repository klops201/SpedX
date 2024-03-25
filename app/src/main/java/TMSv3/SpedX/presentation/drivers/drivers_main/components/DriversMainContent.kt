package TMSv3.SpedX.presentation.drivers.drivers_main.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.components.DriverCard
import TMSv3.SpedX.components.VehicleCard
import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Vehicle
import TMSv3.SpedX.presentation.drivers.drivers_main.DriverMainViewModel
import TMSv3.SpedX.presentation.orders.orders_list.OrdersViewModel
import TMSv3.SpedX.presentation.orders.orders_list.components.ShowOrder
import TMSv3.SpedX.presentation.uiTheme.GreyBG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData

@Composable
fun DriversMainContent(
    padding: PaddingValues,
    viewModel: DriverMainViewModel = hiltViewModel(),
    navigateEditDriverScreen: (String?) -> Unit
) {
    val scrollState = rememberScrollState()
//    var loadData by remember { mutableStateOf(false) }
    var currentStep by remember { mutableStateOf(0) }
    LaunchedEffect(viewModel) {
        viewModel.getDriverList()
    }

    LaunchedEffect(viewModel) {
        viewModel.fetchVehicles()
    }

    val fetchedVehicles by viewModel.vehicles.observeAsState(emptyList())
    val counter by viewModel.counter.observeAsState()


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
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = "Zarządzaj kierowcami",
                    fontSize = 25.sp,
                    color = Color.White
                )
            }
//            Spacer(modifier = Modifier.height(10.dp))
            GetDrivers { drivers ->
                Log.d(Constants.TAG, "LISTA DRIVEROW---------- :$drivers ")
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
//                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(drivers) { driver ->
                        Spacer(modifier = Modifier.height(5.dp))
                        ShowDrivers(driver = driver, vehicles = fetchedVehicles, counter, navigateToEditDriver = {navigateEditDriverScreen(driver.firebaseID)} )

                    }
                }

            }


        }

    }


}


@Composable
fun ShowDrivers(
    driver: Driver, vehicles: List<Vehicle>,
    counterRef: Int?,
    viewModel: DriverMainViewModel = hiltViewModel(),
    navigateToEditDriver: (String?) -> Unit
) {

    val safeID: String = driver.vehicleId ?: ""
    val safeFBID: String = driver.firebaseID ?: ""
    val checkID = vehicles.any { it.vehicleId == safeID }
    Log.d(Constants.TAG, "SZUKANIE ID--------- :$safeID ===== $checkID ")


    if (checkID) {


        LaunchedEffect(Unit) {
            Log.d(Constants.TAG, "START LAUNCHeFF $safeID ===== $checkID ")

            viewModel.fetchPositionWithCallback(safeID) { position ->
                // Tutaj możesz wykonać operacje na pozycji
                if (position != null) {
                    viewModel.savePosition(position, safeID)
                }
            }
            val fetchedPosition = viewModel.position.value
            Log.d(Constants.TAG, "czy jest co zapisać??????--------- :$fetchedPosition ")
//            if(fetchedPosition != null) {
//                fetchedPosition?.let { position ->
//                    Log.d(Constants.TAG, "PRZEKAZANIE DO ZAPISANIA--------- :$position ")
//                    viewModel.savePosition(position, safeID)
//                }
//            }
//
//            viewModel.loadPosition(safeID)
            viewModel.loadPosition(safeID)
            Log.d(Constants.TAG, "KONIEC LAUNCHeFF $safeID ===== $checkID ")

        }

        LaunchedEffect(counterRef) {
            viewModel.loadPosition(safeID)
            viewModel.fetchPositionWithCallback(safeID) { position ->
                // Tutaj możesz wykonać operacje na pozycji
                if (position != null) {
                    viewModel.savePosition(position, safeID)
                }
            }
            val fetchedPosition = viewModel.position.value
            Log.d(Constants.TAG, "czy jest co zapisać??????--------- :$fetchedPosition ")
//            if(fetchedPosition != null) {
//                fetchedPosition?.let { position ->
//                    Log.d(Constants.TAG, "PRZEKAZANIE DO ZAPISANIA--------- :$position ")
//                    viewModel.savePosition(position, safeID)
//                }
//            }
//
//            viewModel.loadPosition(safeID)
//            viewModel.loadPosition(safeID)
            Log.d(Constants.TAG, "KONIEC LAUNCHeFF $safeID ===== $checkID ")
        }


    }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        DriverCard(driver = driver,navigateEditDriver = {navigateToEditDriver(safeFBID)})
        if (checkID) {
            GetPosition { position ->
                VehicleCard(position = position)
            }
        } else {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .height(250.dp)
                    .width(186.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize() // Aby tekst wypełniał całą kartę
                        .background(GreyBG), // Dodaj tło do Box
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(13.dp),
                        textAlign = TextAlign.Center,
                        text = "Brak aktualnych danych. \n Sprawdź poprawność ID pojazdu.",
                        fontSize = 15.sp // Dodaj kolor tekstu, aby był widoczny na czerwonym tle
                    )
                }
            }
        }


    }


}


@Preview
@Composable
fun checkkk() {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .height(250.dp)
            .width(186.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize() // Aby tekst wypełniał całą kartę
                .background(GreyBG), // Dodaj tło do Box
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(13.dp),
                textAlign = TextAlign.Center,
                text = "Brak aktualnych danych \n Sprawdź poprawność ID pojazdu",
                fontSize = 15.sp // Dodaj kolor tekstu, aby był widoczny na czerwonym tle
            )
        }
    }
}