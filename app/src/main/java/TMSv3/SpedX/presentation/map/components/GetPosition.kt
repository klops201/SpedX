package TMSv3.SpedX.presentation.map.components

import TMSv3.SpedX.components.ProgressBar
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response.*
import TMSv3.SpedX.presentation.map.MapViewModel
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel

//@Composable
//fun getPosition(
//    viewModel: MapViewModel = hiltViewModel(),
//    MapScreen: @Composable (Position: Position) -> Unit
//    ){
//    when( val positionResponse = viewModel.fetchPositionResponse){
//        is Loading -> ProgressBar()
//        is Success -> positionResponse.data?.let { position ->
//            MapScreen(position)
//        }
//        is Failure -> print(positionResponse.e)
//    }
//}