package TMSv3.SpedX.presentation.orders.orders_list.components

import TMSv3.SpedX.core.Constants
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import TMSv3.SpedX.presentation.orders.orders_list.OrdersViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import TMSv3.SpedX.domain.model.Order
import android.util.Log
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.LaunchedEffect

@Composable
fun OrdersContent(padding: PaddingValues,
    viewModel: OrdersViewModel = hiltViewModel(),
          orderClicked: (String?) -> Unit
) {


    val scrollState = rememberScrollState()
    LaunchedEffect(viewModel) {
        viewModel.getOrdersListFirestore()
    }

        getOrders { orders ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(orders) { order ->
                    Log.d(Constants.TAG, "exe funct lazy column -------------------: $order")
                    val clickecOrderID = order.firestoreID
                    ShowOrder(order = order, onClick = {
                        Log.d(Constants.TAG, "ORDER CLICKED---------CHECK ORDERID---------->>>>: $order")
                        orderClicked(clickecOrderID)
                        } )
                }
            }

        }

    }

@Composable
fun ShowOrder(order: Order, onClick: (String?) -> Unit) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .clickable { onClick(order.firestoreID) }
            .padding(vertical = 4.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Zamówienie :        ",
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = "${order.orderTitle}",
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )
        Log.d(Constants.TAG, "exe function ShowOrder----------------------------------: $order")
    }
}