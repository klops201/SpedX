package TMSv3.SpedX.presentation.map

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Position
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun MapScreen(
    navigateBack: () -> Boolean,
    viewModel: MapViewModel = hiltViewModel()
) {


    LaunchedEffect(viewModel){
        viewModel.fetchPosition()
    }


    val fetchGPS by viewModel.position.observeAsState<Position>()
    Log.d(Constants.TAG, "wczytanie pozycji do screen----------: $fetchGPS")

    fetchGPS?.let { positionCar ->
        Log.d(Constants.TAG, "stworzenie latlng----------: $positionCar")

        // Użyj position w swoim kodzie Compose
        val positionGPS = LatLng(positionCar.latitude, positionCar.longitude)
        // ... reszta kodu MapScreen


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
            Log.d(Constants.TAG, "wczytanie markera-----------------------------------")
            Marker(
                state = MarkerState(positionGPS),
                title = "Aktualna lokalizacja",
                snippet = "Znacznik pojazdu"
            )
        }
    }

}