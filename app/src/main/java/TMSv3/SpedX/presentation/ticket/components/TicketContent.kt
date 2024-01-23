package TMSv3.SpedX.presentation.ticket.components

import TMSv3.SpedX.R
import TMSv3.SpedX.core.Utils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketContent(
    paddingValues: PaddingValues
){
    val currentDate = LocalDate.now()
    val getTime = LocalTime.now()
    val currentTime = getTime.plusHours(1).format(DateTimeFormatter.ofPattern("HH:mm"))
    val scrollState = rememberScrollState()
    val scrollStateDialog = rememberScrollState()
    val countries = listOf("Łotwa", "Estonia", "Litwa")
    var expanded by remember { mutableStateOf(false) }
    var currentCountry by remember { mutableStateOf(countries[0]) }
    var dateResult by remember { mutableStateOf(currentDate.toString()) }
    var openCalendar by remember { mutableStateOf(false) }
    var showInfoGMT by remember { mutableStateOf(false) }
    var showGMTdialog by remember { mutableStateOf(false) }
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    var selectedHour by remember {
        mutableIntStateOf(0) // or use  mutableStateOf(0)
    }

    var selectedMinute by remember {
        mutableIntStateOf(0) // or use  mutableStateOf(0)
    }


    var showDialog by remember {
        mutableStateOf(false)
    }


    var showTutorial by remember {
        mutableStateOf(false)
    }


    var showSMSoptions by remember {
        mutableStateOf(false)
    }


    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )

    var TimeResult by remember { mutableStateOf(currentTime.toString()) }

    var contentSMS by remember { mutableStateOf("Nie dokonano wyboru") }

    val context = LocalContext.current




    Box (modifier = Modifier){
        Column (modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorViniet))
            .padding(10.dp)
            .verticalScroll(scrollState)) {
            Box (modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)) {
                Image(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.smsviniet),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            Row {

                TextField(modifier = Modifier
                    .clip(AlertDialogDefaults.shape)
                    , value = currentCountry
                    , onValueChange = {}, label = { Text(fontSize = 10.sp, text = "Wybierz kraj") }
                    , readOnly = true
                    , colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent)
                    , trailingIcon ={
                        IconButton(onClick = {expanded =!expanded}) {
                            Icon( imageVector = Icons.Filled.ArrowDropDown,contentDescription = null)

                        }
                    } )
                
                    DropdownMenu(modifier = Modifier
                        .background(color = Color.White),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        countries.forEach {
                            DropdownMenuItem(onClick = {
                                currentCountry = it
                                expanded = false
                            }) {
                                Text(text = it)
                            }
                        }
                    }

            }
            Spacer(modifier = Modifier.height(35.dp))
            TextField(modifier = Modifier
                .clip(AlertDialogDefaults.shape)
                , value = dateResult
                , onValueChange = {}, label = { Text(fontSize = 10.sp, text = "Wybierz datę") }
                , readOnly = true
                , colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent)
                , trailingIcon ={
                    IconButton(onClick = {openCalendar =!openCalendar}) {
                        Icon( imageVector = Icons.Filled.CalendarMonth,contentDescription = null)

                    }
                } )
            val datePickerState = rememberDatePickerState()
            if(openCalendar){

                val confirmEnabled by remember {derivedStateOf {datePickerState.selectedDateMillis != null}}

                DatePickerDialog(
                    onDismissRequest = { openCalendar = false },
                    confirmButton = {
                        TextButton(onClick = {
                            openCalendar = false
                            var date = "Nie wybrano daty"
                            if(datePickerState.selectedDateMillis!= null){
                                date = Utils.convertDate(datePickerState.selectedDateMillis!!)
//                                val selectedLocalDate: LocalDate? = datePickerState.selectedDateMillis?.let {
//                                    // Konwersja timestamp na LocalDate
//                                    LocalDate.ofEpochDay(it / (24 * 60 * 60 * 1000))
//                                }
                            }
                            dateResult = date

                        }){Text(text = "Zatwierdź")}
                    },
                    dismissButton = {
                        TextButton(onClick = { openCalendar = false }) {
                            Text(text = "Anuluj")
                        }
                    }) {
                        DatePicker(state = datePickerState)

                }




            }
            Spacer(modifier = Modifier.height(35.dp))
            TextField(modifier = Modifier
                .clip(AlertDialogDefaults.shape)
                , value = TimeResult
                , onValueChange = {}, label = { Text(fontSize = 10.sp, text = "Wybierz godzinę") }
                , readOnly = true
                , colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent)
                , trailingIcon ={
                    IconButton(onClick = {showDialog =!showDialog}) {
                        Icon( imageVector = Icons.Filled.AccessTime,contentDescription = null)

                    }
                } )
            if (showDialog) {
                AlertDialog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(size = 12.dp)
                        ),
                    onDismissRequest = { showDialog = false }
                ) {
                    Column(
                        modifier = Modifier
                            .background(
                                color = Color.LightGray.copy(alpha = 0.3f)
                            )
                            .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // time picker
                        TimePicker(state = timePickerState)


                        // buttons
                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            // dismiss button
                            TextButton(onClick = { showDialog = false }) {
                                Text(text = "Anuluj")
                            }

                            // confirm button
                            TextButton(
                                onClick = {
                                    showDialog = false
                                    showInfoGMT = true
                                    selectedHour = timePickerState.hour
                                    selectedMinute = timePickerState.minute
                                    TimeResult = "${selectedHour+1}:${selectedMinute+2}"
                                }
                            ) {
                                Text(text = "Zatwierdź")
                            }
                        }
                    }
                }
            }

            if(showInfoGMT) {
                TextButton(onClick = { showGMTdialog = !showGMTdialog }) {
                    Text(
                        text = "---> Dlaczego wybrany czas jest inny? <---",
                        color = Color.White,
                        fontSize = 15.sp,
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                }
            }
            Spacer(modifier = Modifier.height(35.dp))
            Row (modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick = {
                        contentSMS = Utils.generateSMS(
                            currentCountry,
                            datePickerState.selectedDateMillis!!,
                            selectedHour,
                            selectedMinute
                        )
                        showSMSoptions = true
                    }) {
                    Text(text = "generuj SMS", color = Color.Black)

                }
                TextButton(onClick = { showTutorial = !showTutorial }) {
                    Text(
                        text = "Poradnik kupna winiety przez SMS",
                        color = Color.White,
                        fontSize = 15.sp,
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box (modifier = Modifier
                .width(240.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Color.White)
                .padding(5.dp),) {///tresc sms
                Row (modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = contentSMS, modifier = Modifier)
                    Column {
                        Button(onClick = { clipboardManager.setText(AnnotatedString((contentSMS)))},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                            Text(text = "Kopiuj", color = Color.Black, fontSize = 15.sp)
                        }
                        Button(onClick = {Utils.sendSMS(contentSMS,context )},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                            Text(text = "Wyślij SMS", color = Color.Black, fontSize = 15.sp)
                        }
                    }
                }
            }



            if(showTutorial) {
                Dialog(onDismissRequest = {showTutorial = !showTutorial}, properties = DialogProperties(usePlatformDefaultWidth = false)) {
                    // Draw a rectangle shape with rounded corners inside the dialog
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                                .verticalScroll(scrollStateDialog),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.guide1),
                                contentDescription = "imageDescription",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                            ) {
                                TextButton(
                                    onClick = { showTutorial = false },
                                    modifier = Modifier.padding(8.dp),
                                ) {
                                    Text("Zamknij")
                                }
                            }
                        }
                    }
                }
            }










            if(showGMTdialog) {
                Dialog(onDismissRequest = {showGMTdialog = !showGMTdialog}) {
                    // Draw a rectangle shape with rounded corners inside the dialog
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .padding(16.dp),
                            shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(scrollStateDialog),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.gmt),
                                contentDescription = "imageDescription",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .height(160.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Text(
                                text = "Wybrany czas jest zwiększony o 1 godzinę, ponieważ kraje bałtyckie znajdują się w innej strefie czasowej niż Polska. System kupna winiet działa w strefie GMT+2 i jest to szczególnie istotna informacja, " +
                                        "jeśli użytkownik chce mieć aktywną winitę zaraz po zakupie. " +
                                        "Dwie dodatkowe minuty są dla bezpieczeństwa w razie natychmiastowego kupna, aby płatność została zaksięgowana na czas. W przeciwnym wypadku," +
                                        " zlecenie kupna z nieważnym terminem zostanie odrzucone i kupno winiety nie zostanie sfinalizowane. " ,
                                modifier = Modifier.padding(16.dp),
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                            ) {
                                TextButton(
                                    onClick = { showGMTdialog = false },
                                    modifier = Modifier.padding(8.dp),
                                ) {
                                    Text("Zamknij")
                                }
                            }
                        }
                    }
                }
            }










        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun checkPRevvv(){
    TicketContent(paddingValues = PaddingValues(0.dp))
}