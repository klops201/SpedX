package TMSv3.SpedX.presentation.settings.puesc

import TMSv3.SpedX.R
import TMSv3.SpedX.components.DataTextField
import TMSv3.SpedX.presentation.settings.SettingsViewModel
import TMSv3.SpedX.presentation.settings.components.GetGlbData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun PuescContent(
    padding: PaddingValues,
    goBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getPuescData()
    }

    fun replaceWithAsterisks(input: String): String {
        return input.map { '*' }.joinToString("")
    }


    val scrollState = rememberScrollState()


    GetSentForm {item ->

        val login = item.log ?: "błedne dane"
        val pass = item.gate ?: "błędne dane"
        val nip = item.NIP ?: "błędne dane"
        val address = item.address ?: "błędne dane"
        val borderPosit = item.borderPosition ?: "błędne dane"
        val borderRoad = item.borderRoad ?: "błędne dane"
        val cargoPermit = item.cargoPermit ?: "błędne dane"
        val companyName = item.companyName ?: "błędne dane"
        val emailNotif = item.emailConfirmation ?: "błędne dane"
        val gpsId = item.gpsID ?: "błędne dane"
        val trailerId = item.trailerID ?: "błędne dane"
        val truckId = item.truckID ?: "błędne dane"

        var newLogin by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = login
                    )
                )
            }
        )

        var newNip by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = nip
                    )
                )
            }
        )

        var newAddress by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = address
                    )
                )
            }
        )

        var newBorderPosit by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = borderPosit
                    )
                )
            }
        )

        var newBorderRoad by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = borderRoad
                    )
                )
            }
        )

        var newCargoPermit by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = cargoPermit
                    )
                )
            }
        )

        var newCompanyName by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = companyName
                    )
                )
            }
        )

        var newEmailNotif by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = emailNotif
                    )
                )
            }
        )

        var newGpsId by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = gpsId
                    )
                )
            }
        )

        var newTrailerId by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = trailerId
                    )
                )
            }
        )

        var newTruckId by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = truckId
                    )
                )
            }
        )

        var newPass by rememberSaveable(
            stateSaver = TextFieldValue.Saver,
            init = {
                mutableStateOf(
                    value = TextFieldValue(
                        text = pass
                    )
                )
            }
        )





        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .clip(RoundedCornerShape(18.dp))
                        .background(colorResource(id = R.color.colorTest))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 30.dp, horizontal = 15.dp)
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Email:",
                            data = newLogin,
                            onDataValueChange = { newValue ->
                                newLogin = newValue
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))
//                        DataTextField(modifier = Modifier
//                            .clip(RoundedCornerShape(18.dp))
//                            .background(color = colorResource(id = R.color.colorBg)),
//                            labelValue = "Hasło:",
//                            data = newPass,
//                            onDataValueChange = { newValue ->
//                                newPass = newValue
//                            },
//
//                        )

                        TextField(
                            modifier = Modifier
                                .clip(RoundedCornerShape(18.dp))
                                .background(color = colorResource(id = R.color.colorBg)),
                            value = newPass,
                            onValueChange = { newValue ->
                                newPass = newValue
                            },
                            label = { Text("Hasło:") },
                            visualTransformation = PasswordVisualTransformation()
                        )




                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Nip:",
                            data = newNip,
                            onDataValueChange = { newValue ->
                                newNip = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Adres:",
                            data = newAddress,
                            onDataValueChange = { newValue ->
                                newAddress = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Pełna nazwa firmy:",
                            data = newCompanyName,
                            onDataValueChange = { newValue ->
                                newCompanyName = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Przejście graniczne:",
                            data = newBorderPosit,
                            onDataValueChange = { newValue ->
                                newBorderPosit = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Nr drogi (przejście graniczne):",
                            data = newBorderRoad,
                            onDataValueChange = { newValue ->
                                newBorderRoad = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Nr pozwolenia na transport drogowy:",
                            data = newCargoPermit,
                            onDataValueChange = { newValue ->
                                newCargoPermit = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Nr urządzenia GPS:",
                            data = newGpsId,
                            onDataValueChange = { newValue ->
                                newGpsId = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Nr rejestracyjny ciężarówki:",
                            data = newTruckId,
                            onDataValueChange = { newValue ->
                                newTruckId = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Nr rejestracyjny naczepy:",
                            data = newTrailerId,
                            onDataValueChange = { newValue ->
                                newTrailerId = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        DataTextField(modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = colorResource(id = R.color.colorBg)),
                            labelValue = "Email do wysyłki potwierdzeń:",
                            data = newEmailNotif,
                            onDataValueChange = { newValue ->
                                newEmailNotif = newValue
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
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
                                onClick = { goBack() },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(colorResource(id = R.color.colorTest))
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 15.dp),
                                    text = "Cofnij",
                                    fontSize = 15.sp
                                )
                            }

                            Button(shape = CircleShape, colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id = R.color.colorBg),
                                contentColor = colorResource(id = R.color.colorTest)
                            ),
                                onClick = { viewModel.updatePuesc(newLogin.text, newPass.text, newNip.text, newAddress.text, newBorderPosit.text, newBorderRoad.text,
                                    newCargoPermit.text, newCompanyName.text, newEmailNotif.text, newGpsId.text, newTrailerId.text, newTruckId.text)
                                    goBack()},
                                modifier = Modifier
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(colorResource(id = R.color.colorTest))
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 15.dp),
                                    text = "Aktualizuj",
                                    fontSize = 15.sp
                                )
                            }


                        }

                    }


                }
            }
        }
    }
}