package TMSv3.SpedX.presentation.add_order.components


import TMSv3.SpedX.R
import TMSv3.SpedX.components.EmailField
import TMSv3.SpedX.components.PasswordField
import TMSv3.SpedX.components.SmallSpacer
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.uiTheme.componentShapes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import TMSv3.SpedX.components.DataTextField
import TMSv3.SpedX.components.NumberField
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun AddOrderContent(
    padding: PaddingValues,
    addOrder: (orderTitle: String, orderID: String, position: String?, finalDest: String,
            startDest: String, cargoName: String, cargoWeight: Int,
            driverID: String, cmrID: String?, createAt: String) -> Unit,
            navigateToOrdersScr: () -> Unit,
){
    val scrollState = rememberScrollState()
    var orderTitle by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
    var orderID by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
    var position by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
    var finalDest by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
    var startDest by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
    var cargoName by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
    var cargoWeight by rememberSaveable {
        mutableStateOf<Int?>(null)
    }
    var driverID by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
    var cmrID by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )
    var createAt by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = Constants.EMPTY_STRING
                )
            )
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DataTextField(
            labelValue = "Tytuł zlecenia",
            data = orderTitle,
            onDataValueChange = { newValue ->
                orderTitle = newValue
            }
        )
        Spacer(modifier = Modifier.height(17.dp))
        DataTextField(
            labelValue = "Numer zlecenia",
            data = orderID,
            onDataValueChange = { newValue ->
                orderID = newValue
            }
        )
        Spacer(modifier = Modifier.height(17.dp))
        DataTextField(
            labelValue = "Aktualna pozycja",
            data = position,
            onDataValueChange = { newValue ->
                position = newValue
            }
        )
        Spacer(modifier = Modifier.height(17.dp))
        DataTextField(
            labelValue = "Miejsce załadowania",
            data = startDest,
            onDataValueChange = { newValue ->
                startDest = newValue
            }
        )
        Spacer(modifier = Modifier.height(17.dp))
        DataTextField(
            labelValue = "Miejsce przeznaczenia",
            data = finalDest,
            onDataValueChange = { newValue ->
                finalDest = newValue
            }
        )
        Spacer(modifier = Modifier.height(17.dp))
        DataTextField(
            labelValue = "Rodzaj ładunku",
            data = cargoName,
            onDataValueChange = { newValue ->
                cargoName = newValue
            }
        )
        Spacer(modifier = Modifier.height(17.dp))
        NumberField(
            labelValue = "Waga ładunku",
            value = cargoWeight?: 0,
            onValueChange = { newValue ->
                cargoWeight = newValue
            }
        )
        Spacer(modifier = Modifier.height(17.dp))
        DataTextField(
            labelValue = "Identyfikator kierowcy",
            data = driverID,
            onDataValueChange = { newValue ->
                driverID = newValue
            }
        )
        Spacer(modifier = Modifier.height(17.dp))
        DataTextField(
            labelValue = "Identyfikator CMR",
            data = cmrID,
            onDataValueChange = { newValue ->
                cmrID = newValue
            }
        )
        Spacer(modifier = Modifier.height(17.dp))
        DataTextField(
            labelValue = "Data utworzenia",
            data = createAt,
            onDataValueChange = { newValue ->
                createAt = newValue
            }
        )
        SmallSpacer()
        Button(
            onClick = {
                addOrder(orderTitle.text, orderID.text, position.text, finalDest.text,
                    startDest.text, cargoName.text, cargoWeight ?: 0, driverID.text, cmrID.text,
                    createAt.text)
                navigateToOrdersScr()
            }
        ) {
            androidx.compose.material.Text(
                text = Constants.ADD_ORDER,
                fontSize = 15.sp
            )
        }
    }

}

