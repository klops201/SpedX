package TMSv3.SpedX.components

import TMSv3.SpedX.R
import TMSv3.SpedX.presentation.uiTheme.componentShapes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberField(  labelValue: String,
                  value: TextFieldValue,
                  onValueChange: (newValue: TextFieldValue) -> Unit) { // okna do wpisywania danych


    OutlinedTextField(
        label = {
            Text(text = labelValue)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = colorResource(id = R.color.colorText),
            focusedBorderColor = colorResource(id = R.color.colorRed), //kolor ramki przy wpisywaniu tekstu
            focusedLabelColor = colorResource(id = R.color.colorRed), // kolor etykiety przy wpisywaniu
            cursorColor = colorResource(id = R.color.colorPrimary),
            unfocusedLabelColor = colorResource(id = R.color.colorTextGray)
        ),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },)
}
