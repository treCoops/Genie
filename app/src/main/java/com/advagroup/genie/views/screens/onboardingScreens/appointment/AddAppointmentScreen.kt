package com.advagroup.genie.views.screens.onboardingScreens.appointment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.helpers.convertTimeMillisLongToString
import com.advagroup.genie.helpers.get12HoursTime
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFill
import com.advagroup.genie.views.reusableComposables.pickers.CalenderDatePickerDialog
import com.advagroup.genie.views.reusableComposables.pickers.TimePickerDialog
import com.advagroup.genie.views.reusableComposables.textFields.DefaultTextField
import com.advagroup.genie.views.reusableComposables.titleBar.DefaultNavigationTopBarWithIcon
import java.util.Calendar

@Composable
fun AddAppointmentScreen(navController: NavController) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MainView(navController)
    }

}

@Composable
private fun MainView(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DefaultNavigationTopBarWithIcon(
            imageVector = Icons.Filled.Clear,
            onIconPressed = {
                navController.popBackStack()
            },
            headingTitle = stringResource(R.string.add_appointment_heading),
            modifier = Modifier
                .size(30.dp)
        )

        ContentView(navController)
    }
}

@Composable
private fun ContentView(navController: NavController) {

    val scrollState = rememberScrollState()

    var appointmentName by remember {
        mutableStateOf("")
    }

    var appointmentLocation by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf("")
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var reminderTime by remember {
        mutableStateOf("")
    }

    var showTimePicker by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState)
    ) {

        Text(
            text = stringResource(R.string.appointment_details),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = appointmentName,
            onValueChange = {
                appointmentName = it
            },
            stringResource(R.string.appointment_name_enter),
            stringResource(R.string.appointment_name_enter_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = appointmentLocation,
            onValueChange = {
                appointmentLocation = it
            },
            stringResource(R.string.appointment_location_enter),
            stringResource(R.string.appointment_location_enter_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        DateComposable(
            title = stringResource(R.string.appointment_date_enter),
            value = date,
            onClicked = {
                showDatePicker = true
            }
        )

        if (showDatePicker) {
            DatePickerComposable(
                onDateSelected = { selectedDate ->
                    selectedDate?.let {
                        date = it.convertTimeMillisLongToString("dd MMM yyyy")
                    }

                    showDatePicker = false
                },
                onDismiss = {
                    showDatePicker = false
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.appointment_time_enter),
            modifier = Modifier
                .fillMaxWidth(),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(10.dp))

        ReminderTimeComposable(
            value = reminderTime,
            onClicked = {
                showTimePicker = true
            }
        )

        if (showTimePicker) {
            TimePickerComposable(
                onTimeSelected = { hour, minutes ->
                    reminderTime = get12HoursTime(hour, minutes)
                    showTimePicker = false
                },
                onDismiss = {
                    showTimePicker = false
                }
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        DefaultFormButtonWithFill(
            title = "Done",
            paddingValues = PaddingValues()
        ) {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(15.dp))

        DefaultFormButtonWithoutFill(
            "Cancel",
            paddingValues = PaddingValues()
        ) {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(50.dp))

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimePickerComposable(onTimeSelected: (Int, Int) -> Unit, onDismiss: () -> Unit) {

    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )

    TimePickerDialog(timePickerState, onTimeSelected, onDismiss)

}

@Composable
private fun ReminderTimeComposable(value: String, onClicked: () -> Unit) {
    Button(
        onClick = onClicked,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = ButtonDefaults.buttonColors(
            EditTextBackgroundColor,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(18.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {

            if(value == ""){
                SpecificTextComposable(
                    value = stringResource(R.string.appointment_time_enter_placeholder),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            } else {
                SpecificTextComposable(
                    value = value,
                    modifier = Modifier
                        .align(Alignment.CenterStart),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Icon(
                imageVector = Icons.Filled.AccessTime,
                contentDescription = "Person Icon",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
private fun SpecificTextComposable(value: String, modifier: Modifier, color: Color = MaterialTheme.colorScheme.onSurfaceVariant){
    Text(
        text = value,
        color = color,
        fontFamily = SFPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        textAlign = TextAlign.Start,
        modifier = modifier
    )
}

@Composable
private fun DateComposable(title: String, value: String, onClicked: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth(),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                EditTextBackgroundColor,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            shape = RoundedCornerShape(18.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {

                if(value == ""){
                    DateTextComposable(
                        value = stringResource(R.string.appointment_date_enter_placeholder),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )
                } else {
                    DateTextComposable(
                        value = value,
                        modifier = Modifier
                            .align(Alignment.CenterStart),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Date Icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.CenterEnd)
                )
            }

        }

    }
}

@Composable
private fun DateTextComposable(value: String, modifier: Modifier, color: Color = MaterialTheme.colorScheme.onSurfaceVariant){
    Text(
        text = value,
        color = color,
        fontFamily = SFPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        textAlign = TextAlign.Start,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerComposable(onDateSelected: (Long?) -> Unit, onDismiss: () -> Unit) {
    val calendar = Calendar.getInstance()
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val currentTimeMillis = calendar.timeInMillis

    val datePickerState = rememberDatePickerState(
        yearRange = currentYear..2050,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= currentTimeMillis
            }
        }
    )

    CalenderDatePickerDialog(stringResource(R.string.appointment_date_enter_placeholder), datePickerState, onDateSelected, onDismiss)
}

@Composable
private fun TextFieldComposable(value: String, onValueChange: (String) -> Unit, title: String, placeholder: String, keyboardOptions: KeyboardOptions) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth(),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(10.dp))

        DefaultTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {

                }
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}