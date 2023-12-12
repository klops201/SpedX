package TMSv3.SpedX.presentation.orders.edit_order.components

import TMSv3.SpedX.components.DataTextField
import TMSv3.SpedX.components.NumberField
import TMSv3.SpedX.components.SmallSpacer
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.orders.edit_order.EditOrderViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun EditOrderContent(
    padding: PaddingValues,
    editOrder: (orderTitle: String, orderID: String, position: String, finalDest: String,
               startDest: String, cargoName: String, cargoWeight: Int,
               driverID: String, cmrID: String, createAt: String) -> Unit,
    navigateToOrderDetails: () -> Unit,
    orderID: String,
    viewModel: EditOrderViewModel = hiltViewModel(),
){


    LaunchedEffect(orderID) {
        viewModel.getOrderDetails(orderID)
    }


    val scrollState = rememberScrollState()
//    var orderTitle by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = orderTitleFB
//                )
//            )
//        }
//    )
//    var orderID by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = Constants.EMPTY_STRING
//                )
//            )
//        }
//    )
//    var position by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = Constants.EMPTY_STRING
//                )
//            )
//        }
//    )
//    var finalDest by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = Constants.EMPTY_STRING
//                )
//            )
//        }
//    )
//    var startDest by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = Constants.EMPTY_STRING
//                )
//            )
//        }
//    )
//    var cargoName by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = Constants.EMPTY_STRING
//                )
//            )
//        }
//    )
//    var cargoWeight by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = Constants.EMPTY_STRING
//                )
//            )
//        }
//    )
//    var driverID by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = Constants.EMPTY_STRING
//                )
//            )
//        }
//    )
//    var cmrID by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = Constants.EMPTY_STRING
//                )
//            )
//        }
//    )
//    var createAt by rememberSaveable(
//        stateSaver = TextFieldValue.Saver,
//        init = {
//            mutableStateOf(
//                value = TextFieldValue(
//                    text = Constants.EMPTY_STRING
//                )
//            )
//        }
//    )


    OrderData {item ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val orderTitleFB = item.orderTitle ?: ""
            val orderIdFB = item.orderId ?: ""
            val positionFB = item.position ?: ""
            val finaldestinationFB = item.finaldestination ?: ""
            val startDestFB = item.startdestination ?: ""
            val cargoWeightFB = item.cargoWeight ?: 0
            val cargoNameFB = item.cargoName ?: ""
            val driverIDFB = item.driverId ?: ""
            val cmrIDFB = item.cmrId ?: ""
            val createAtFB = item.createAt ?: ""

            var orderTitle by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = orderTitleFB
                        )
                    )
                }
            )
            var orderID by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = orderIdFB
                        )
                    )
                }
            )
            var position by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = positionFB
                        )
                    )
                }
            )
            var finalDest by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = finaldestinationFB
                        )
                    )
                }
            )
            var startDest by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = startDestFB
                        )
                    )
                }
            )
            var cargoName by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = cargoNameFB
                        )
                    )
                }
            )
            var cargoWeight by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = cargoWeightFB.toString()
                        )
                    )
                }
            )
            var driverID by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = driverIDFB
                        )
                    )
                }
            )
            var cmrID by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = cmrIDFB
                        )
                    )
                }
            )
            var createAt by rememberSaveable(
                stateSaver = TextFieldValue.Saver,
                init = {
                    mutableStateOf(
                        value = TextFieldValue(
                            text = createAtFB
                        )
                    )
                }
            )


            DataTextField(
                labelValue = "Tytuł zlecenia:",
                data = orderTitle,
                onDataValueChange = { newValue ->
                    orderTitle = newValue
                }
            )
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(
                labelValue = "Numer zlecenia:",
                data = orderID,
                onDataValueChange = { newValue ->
                    orderID = newValue
                }
            )
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(
                labelValue = "Aktualna pozycja:",
                data = position,
                onDataValueChange = { newValue ->
                    position = newValue
                }
            )
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(
                labelValue = "Miejsce załadowania:",
                data = startDest,
                onDataValueChange = { newValue ->
                    startDest = newValue
                }
            )
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(
                labelValue = "Miejsce przeznaczenia:",
                data = finalDest,
                onDataValueChange = { newValue ->
                    finalDest = newValue
                }
            )
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(
                labelValue = "Rodzaj ładunku:",
                data = cargoName,
                onDataValueChange = { newValue ->
                    cargoName = newValue
                }
            )
            Spacer(modifier = Modifier.height(17.dp))
            NumberField(
                labelValue = "Waga ładunku:",
                value = cargoWeight,
                onValueChange = { newValue ->
                    cargoWeight = newValue
                }
            )
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(
                labelValue = "Identyfikator kierowcy:",
                data = driverID,
                onDataValueChange = { newValue ->
                    driverID = newValue
                }
            )
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(
                labelValue = "Identyfikator CMR:",
                data = cmrID,
                onDataValueChange = { newValue ->
                    cmrID = newValue
                }
            )
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(
                labelValue = "Data utworzenia:",
                data = createAt,
                onDataValueChange = { newValue ->
                    createAt = newValue
                }
            )
            SmallSpacer()
            Button(
                onClick = {
                    editOrder(
                        orderTitle.text,
                        orderID.text,
                        position.text,
                        finalDest.text,
                        startDest.text,
                        cargoName.text,
                        cargoWeight.text.toInt(),
                        driverID.text,
                        cmrID.text,
                        createAt.text
                    )
                    navigateToOrderDetails()

                }
            ) {
                Text(
                    text = Constants.EDIT_ORDER,
                    fontSize = 15.sp
                )
            }
        }

    }
}