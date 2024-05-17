package TMSv3.SpedX.presentation.settings.viniet.components

import TMSv3.SpedX.R
import TMSv3.SpedX.components.DataTextField
import TMSv3.SpedX.core.Constants
import TMSv3.SpedX.presentation.settings.SettingsViewModel
import TMSv3.SpedX.presentation.settings.components.GetGlbData
import android.util.Log
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun VinietContent(
    padding: PaddingValues,
    goBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getVinietData()
    }

    fun replaceWithAsterisks(input: String): String {
        return input.map { '*' }.joinToString("")
    }


    GetGlbData { item ->

        val login = item.log ?: "błedne dane"
        val pass = item.gate ?: "błędne dane"

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



                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                shape = CircleShape, colors = ButtonDefaults.buttonColors(
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

                            Button(
                                shape = CircleShape, colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(id = R.color.colorBg),
                                    contentColor = colorResource(id = R.color.colorTest)
                                ),
                                onClick = {
                                    viewModel.updateViniet(newLogin.text, newPass.text)
                                    goBack()
                                },
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



