package TMSv3.SpedX.presentation.orders.add_order.components


import TMSv3.SpedX.R
import TMSv3.SpedX.components.SmallSpacer
import TMSv3.SpedX.core.Constants
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import TMSv3.SpedX.components.DataTextField
import TMSv3.SpedX.components.NumberField
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun AddOrderContent(
    padding: PaddingValues,
    addOrder: (
        orderTitle: String, orderID: String, position: String?, finalDest: String, startDest: String, cargoName: String, cargoWeight: Int, driverID: String, cmrID: String?, createAt: String
    ) -> Unit,
    navigateToOrdersScr: () -> Unit,
    showSnackBar: () -> Unit,
    goBack: () -> Unit
) {


    val scrollState = rememberScrollState()
    var orderTitle by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })
    var orderID by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })
    var position by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })
    var finalDest by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })
    var startDest by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })
    var cargoName by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })
    var cargoWeight by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })
    var driverID by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })
    var cmrID by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })
    var createAt by rememberSaveable(stateSaver = TextFieldValue.Saver, init = {
        mutableStateOf(
            value = TextFieldValue(
                text = Constants.EMPTY_STRING
            )
        )
    })

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(18.dp))
            .background(colorResource(id = R.color.colorTest))
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxSize()
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DataTextField(modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = colorResource(id = R.color.colorBg)),
                labelValue = "Tytuł zlecenia",
                data = orderTitle,
                onDataValueChange = { newValue ->
                    orderTitle = newValue
                })
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = colorResource(id = R.color.colorBg)),
                labelValue = "Numer zlecenia",
                data = orderID,
                onDataValueChange = { newValue ->
                    orderID = newValue
                })
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = colorResource(id = R.color.colorBg)),
                labelValue = "Aktualna pozycja",
                data = position,
                onDataValueChange = { newValue ->
                    position = newValue
                })
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = colorResource(id = R.color.colorBg)),
                labelValue = "Miejsce załadowania",
                data = startDest,
                onDataValueChange = { newValue ->
                    startDest = newValue
                })
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = colorResource(id = R.color.colorBg)),
                labelValue = "Miejsce przeznaczenia",
                data = finalDest,
                onDataValueChange = { newValue ->
                    finalDest = newValue
                })
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = colorResource(id = R.color.colorBg)),
                labelValue = "Rodzaj ładunku",
                data = cargoName,
                onDataValueChange = { newValue ->
                    cargoName = newValue
                })
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = colorResource(id = R.color.colorBg)),
                labelValue = "Waga ładunku",
                data = cargoWeight,
                onDataValueChange = { newValue ->
                    cargoWeight = newValue
                })
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = colorResource(id = R.color.colorBg)),
                labelValue = "Identyfikator kierowcy",
                data = driverID,
                onDataValueChange = { newValue ->
                    driverID = newValue
                })
//            Spacer(modifier = Modifier.height(17.dp))
//            DataTextField(modifier = Modifier
//                .clip(RoundedCornerShape(18.dp))
//                .background(color = colorResource(id = R.color.colorBg)),
//                labelValue = "Identyfikator CMR",
//                data = cmrID,
//                onDataValueChange = { newValue ->
//                    cmrID = newValue
//                }
//            )
            Spacer(modifier = Modifier.height(17.dp))
            DataTextField(modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = colorResource(id = R.color.colorBg)),
                labelValue = "Data utworzenia dd-mm-rrrr",
                data = createAt,
                onDataValueChange = { newValue ->
                    createAt = newValue
                })
            SmallSpacer()
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(shape = CircleShape, colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.colorBg),
                    contentColor = colorResource(id = R.color.colorTest)
                ),

                    onClick = {
                        goBack()
                        Log.d(Constants.TAG, "KLIIIIIK w anuluj ")
                    }
                ) {
                    Text(
                        fontSize = 15.sp,
                        text = "Anuluj"
                    )
                }


                Button(shape = CircleShape, colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.colorBg),
                    contentColor = colorResource(id = R.color.colorTest)
                ), onClick = {
                    if (areFieldsFilled(
                            orderTitle,
                            orderID,
                            finalDest,
                            startDest,
                            cargoName,
                            cargoWeight,
                            driverID,
                            createAt
                        )
                    ) {
                        addOrder(
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
                        navigateToOrdersScr()
                    } else {
                        showSnackBar()
                    }
                }) {
                    Text(
                        text = Constants.ADD_ORDER, fontSize = 15.sp
                    )
                }
            }
        }
    }

}


//@Preview
//@Composable
//fun PreviewAddOrderContent() {
//    AddOrderContent(padding = PaddingValues(0.dp),
//        addOrder = { _, _, _, _, _, _, _, _, _, _ -> },
//        navigateToOrdersScr = {},
//        showSnackBar = {})
//}


private fun areFieldsFilled(
    orderTitle: TextFieldValue,
    orderID: TextFieldValue,
    finalDest: TextFieldValue,
    startDest: TextFieldValue,
    cargoName: TextFieldValue,
    cargoWeight: TextFieldValue,
    driverID: TextFieldValue,
    createAt: TextFieldValue
): Boolean {
    return orderTitle.text.isNotEmpty() && orderID.text.isNotEmpty() && startDest.text.isNotEmpty() && finalDest.text.isNotEmpty() && cargoName.text.isNotEmpty() && cargoWeight.text.isNotEmpty() && driverID.text.isNotEmpty() && createAt.text.isNotEmpty()
}