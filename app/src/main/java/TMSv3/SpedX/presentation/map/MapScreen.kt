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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.MarkerInfoWindow

@Composable
fun MapScreen(
    navigateBack: () -> Boolean,
    viewModel: MapViewModel = hiltViewModel()
) {


    LaunchedEffect(viewModel) {
        viewModel.fetchPosition()
    }


    val fetchGPS by viewModel.position.observeAsState<Position>()
    Log.d(Constants.TAG, "wczytanie pozycji do screen----------: $fetchGPS")

    fetchGPS?.let { positionCar ->
        Log.d(Constants.TAG, "stworzenie latlng----------: $positionCar")

        // Użyj position w swoim kodzie Compose
        val positionGPS = LatLng(positionCar.latitude, positionCar.longitude)
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
                    Text(text = "aktualna prędkość: $speed")

                }

                Log.d(Constants.TAG, "wczytanie markera-----------------------------------")
//            Marker(
//                state = MarkerState(positionGPS),
//                title = "Aktualna lokalizacja",
//                snippet = "Znacznik pojazdu"
//            )
            }
        }

    }
}