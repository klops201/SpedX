package TMSv3.SpedX.presentation.drivers.drivers_main

import TMSv3.SpedX.R
import TMSv3.SpedX.presentation.drivers.drivers_main.components.DriversMainContent
import TMSv3.SpedX.presentation.orders.pick_order.components.PickOrderContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun DriversMainScreen(
    navigateBack: () -> Boolean,
    viewModel: DriverMainViewModel = hiltViewModel(),
    navigateToDriverEditScr: (String?) -> Unit,
    naviagteAddDriver: () -> Unit
) {


    var showActions by remember {
        mutableStateOf(false)
    }

    var toExpand by remember {
        mutableStateOf(true)
    }


    val density = LocalDensity.current

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        content = { padding ->
            DriversMainContent(padding,
                navigateEditDriverScreen = { clickedDriver -> navigateToDriverEditScr(clickedDriver) })
        },
        scaffoldState = scaffoldState,
        floatingActionButton = {

            AnimatedVisibility(
                visible = showActions,
                enter = scaleIn(),
//                    } + expandVertically(
//                        // Expand from the top.
//                        expandFrom = Alignment.Top
//                    ) + fadeIn(
//                        // Fade in with the initial alpha of 0.3f.
//                        initialAlpha = 0.3f
//                    ),
                exit = scaleOut(),

                ) {
                Column {
                    FloatingActionButton(
                        onClick = {
                            showActions = !showActions
                            toExpand = !toExpand
                        },
                        backgroundColor = colorResource(id = R.color.colorTest)
                    ) {
                        Icon(Icons.Filled.ExpandMore, "", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    FloatingActionButton(
                        onClick = { viewModel.incrementCounter() },
                        backgroundColor = colorResource(id = R.color.colorTest)
                    ) {
                        Icon(Icons.Filled.Refresh, "", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    FloatingActionButton(
                        onClick = { naviagteAddDriver() },
                        backgroundColor = colorResource(id = R.color.colorTest)
                    ) {
                        Icon(Icons.Filled.Add, "", tint = Color.White)
                    }


                }
            }


            AnimatedVisibility(
                visible = toExpand,
                enter = scaleIn() + fadeIn(),
                exit = scaleOut()
            )
            {
                FloatingActionButton(
                    onClick = {
                        showActions = !showActions
                        toExpand = !toExpand
                    },
                    backgroundColor = colorResource(id = R.color.colorTest)
                ) {
                    Icon(Icons.Filled.ExpandLess, "", tint = Color.White)
                }
            }

        }
    )


}