package TMSv3.SpedX.presentation.orders_list.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Constants
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import TMSv3.SpedX.core.Constants.WELCOME_MESSAGE
import TMSv3.SpedX.presentation.orders_list.OrdersViewModel
import TMSv3.SpedX.presentation.profile.ProfileViewModel
import TMSv3.SpedX.presentation.uiTheme.componentShapes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.produceState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import TMSv3.SpedX.domain.model.Order
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun OrdersContent(padding: PaddingValues,
    viewModel: OrdersViewModel = hiltViewModel(),
) {

        getOrders { orders ->
            viewModel._ordersList = orders.toList()
            val testList = viewModel._ordersList
            Log.d(Constants.TAG, "test in ordersContent: $testList")


            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(testList) { order ->
                    Log.d(Constants.TAG, "exe funct lazy column -------------------: $order")
                    ShowOrder(order = order)
                }
            }

        }

    }

@Composable
fun ShowOrder(order: Order) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Zamówienie o nr:        ",
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = "${order.orderId}",
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )
        Log.d(Constants.TAG, "exe function ShowOrder----------------------------------: $order")
    }
}