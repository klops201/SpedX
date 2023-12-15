package TMSv3.SpedX.presentation.map

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
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

@Composable
fun MapScreen(
    navigateBack: () -> Boolean,
) {
    Log.d(Constants.TAG, "przed wczytaniem zmiennych singapore")
    val singapore = LatLng(51.90675735473633, 22.7077693939209)
    val cameraPositionState = rememberCameraPositionState {
        Log.d(Constants.TAG, "wczytanie cameraPositionState-----------------------------------")

        position = CameraPosition.fromLatLngZoom(singapore, 17f)
    }
    Log.d(Constants.TAG, "przed uruchomieniem mapy-----------------------------------")
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Log.d(Constants.TAG, "wczytanie markera-----------------------------------")
        Marker(
            state = MarkerState(singapore),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }

}