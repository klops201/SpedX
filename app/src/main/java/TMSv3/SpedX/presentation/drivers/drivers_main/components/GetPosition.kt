package TMSv3.SpedX.presentation.drivers.drivers_main.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.drivers.drivers_main.DriverMainViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GetPosition(
    viewModel: DriverMainViewModel = hiltViewModel(),
    DriversMainContent: @Composable (position: Position) -> Unit
) {
    when(val getPositionResp = viewModel.positionDetailResponse) {
        is Loading -> ProgressBar()
        is Success -> getPositionResp.data?.let { position ->
            Log.d(Constants.TAG, "(1)_position retrieved successfully from database: $position")

            DriversMainContent(position)
            Log.d(Constants.TAG, "(2)_position retrieved successfully from database: $position")

        }
        is Failure -> LaunchedEffect(Unit) {
            print(getPositionResp.e)
            Log.d(Constants.TAG, "BŁAD wczytywania pozycji z bazy danych $getPositionResp")

        }
    }
}