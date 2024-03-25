package TMSv3.SpedX.presentation.map

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.presentation.uiTheme.tmsOnPrimary
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.maps.android.compose.MarkerInfoWindow

@Composable
fun MapScreen(
    navigateBack: () -> Boolean,
    viewModel: MapViewModel = hiltViewModel()
) {


    LaunchedEffect(viewModel) {
        viewModel.fetchPosition()
    }
    var openDialog by remember { mutableStateOf(false) }

    val fetchGPS by viewModel.position.observeAsState<Position>()
    Log.d(Constants.TAG, "wczytanie pozycji do screen----------: $fetchGPS")

    fetchGPS?.let { positionCar ->
        Log.d(Constants.TAG, "stworzenie latlng----------: $positionCar")
        val safelat = positionCar.latitude ?: 0.00
        val safelng = positionCar.longitude ?: 0.00
        // Użyj position w swoim kodzie Compose
        if(safelat == 0.00 || safelng == 0.00){
            openDialog = true
        }
        if(openDialog) {
            Dialog(onDismissRequest = {openDialog = !openDialog}) {
                // Draw a rectangle shape with rounded corners inside the dialog
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Wczytywanie danych się nie powiodło. Sprawdź połączenie z internetem." ,
                            modifier = Modifier.padding(16.dp),
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            TextButton(
                                onClick = { openDialog = false},
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text("Zamknij")
                            }
                        }
                    }
                }
            }
        }
        val positionGPS = LatLng(safelat, safelng)
        // ... reszta kodu MapScreen

        val speed = positionCar.speed
        Log.d(Constants.TAG, "wczytanie pozycji")
        Log.d(Constants.TAG, "przed wczytaniem zmiennych singapore")
        val singapore = LatLng(51.90675735473633, 22.7077693939209)
        val cameraPositionState = rememberCameraPositionState {
            Log.d(Constants.TAG, "wczytanie cameraPositionState-----------------------------------")

            position = CameraPosition.fromLatLngZoom(positionGPS, 17f)
        }
        Log.d(Constants.TAG, "przed uruchomieniem mapy-----------------------------------")
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            MarkerInfoWindow(
                state = MarkerState(position = positionGPS),
            ) { marker ->


                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(160.dp)
                        .clip(AlertDialogDefaults.shape)
                        .background(tmsOnPrimary)
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    //Text(modifier = Modifier.padding(all = 10.dp), textAlign = TextAlign.Center, text = currentDateAndTime, fontSize = 30.sp)
                    Text(text = "aktualna prędkość:\n $speed km/h", color = Color.White)

                }

                Log.d(Constants.TAG, "wczytanie markera-----------------------------------")

            }
        }

    }
}