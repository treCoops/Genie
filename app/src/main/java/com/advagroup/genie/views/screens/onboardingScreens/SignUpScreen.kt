package com.advagroup.genie.views.screens.onboardingScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.helpers.convertTimeMillisLongToString
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.pickers.CalenderDatePickerDialog
import com.advagroup.genie.views.reusableComposables.titleBar.DefaultNavigationTopBarWithIcon
import com.advagroup.genie.views.reusableComposables.textFields.DefaultTextField
import java.util.Calendar

@Composable
fun SignUpScreen(navController: NavController) {
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
            headingTitle = stringResource(R.string.signup),
            modifier = Modifier
                .size(30.dp)
        )

        ContentView(navController)
    }
}

@Composable
private fun ContentView(navController: NavController) {

    val scrollState = rememberScrollState()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }

    val radioOptions = listOf("Male", "Female")

    val (selectedItem, onOptionSelected) = remember {
        mutableStateOf(radioOptions[0])
    }

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(R.string.create_your_account),
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
            value = firstName,
            onValueChange = {
                firstName = it
            },
            stringResource(R.string.first_name),
            stringResource(R.string.first_name_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            stringResource(R.string.last_name),
            stringResource(R.string.last_name_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        DateOfBirthComposable(
            title = stringResource(R.string.date_of_birth),
            value = dateOfBirth,
            onClicked = {
                showDatePicker = true
            }
        )

        if (showDatePicker) {
            DatePickerComposable(
                onDateSelected = { selectedDate ->
                    selectedDate?.let {
                        dateOfBirth = it.convertTimeMillisLongToString("yyyy-MM-dd")
                    }

                    showDatePicker = false
                },
                onDismiss = {
                    showDatePicker = false
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        GenderRadioButtonGroupComposable(
            title = stringResource(R.string.gender),
            radioOptions = radioOptions,
            selectedItem = selectedItem,
            onOptionSelected = { item ->
                onOptionSelected(item)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = userName,
            onValueChange = {
                userName = it
            },
            stringResource(R.string.username),
            stringResource(R.string.username_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = password,
            onValueChange = {
                password = it
            },
            stringResource(R.string.password),
            stringResource(R.string.password_placeholder),
            isPassword = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            stringResource(R.string.confirm_password),
            stringResource(R.string.confirm_password_placeholder),
            isPassword = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        DefaultFormButtonWithFill(
            title = "Next",
            paddingValues = PaddingValues(),
            {
                navController.navigate(Destinations.EmergencyContactInformationScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(50.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerComposable(onDateSelected: (Long?) -> Unit, onDismiss: () -> Unit) {
    val calendar = Calendar.getInstance()
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val currentTimeMillis = calendar.timeInMillis

    val datePickerState = rememberDatePickerState(
        yearRange = 1900..currentYear,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= currentTimeMillis
            }
        }
    )

    CalenderDatePickerDialog(stringResource(R.string.title_select_birthday), datePickerState, onDateSelected, onDismiss)
}

@Composable
private fun TextFieldComposable(value: String, onValueChange: (String) -> Unit, title: String, placeholder: String, isPassword: Boolean = false, keyboardOptions: KeyboardOptions) {

    val visualTransformation: VisualTransformation

    if(isPassword){
        visualTransformation = PasswordVisualTransformation()
    } else {
        visualTransformation = VisualTransformation.None
    }

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
            visualTransformation = visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun DateOfBirthComposable(title: String, value: String, onClicked: () -> Unit) {

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
                    DateOfBirthTextComposable(
                        value = stringResource(R.string.date_of_birth_placeholder),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )
                } else {
                    DateOfBirthTextComposable(
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
private fun DateOfBirthTextComposable(value: String, modifier: Modifier, color: Color = MaterialTheme.colorScheme.onSurfaceVariant){
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
private fun GenderRadioButtonGroupComposable(title: String, radioOptions: List<String>, selectedItem: String, onOptionSelected: (label: String) -> Unit) {

    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth(),
        fontFamily = SFPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Row(
        modifier = Modifier
            .selectableGroup()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        radioOptions.forEach { label ->
            Row(modifier = Modifier
                .selectable(
                    selected = (selectedItem == label),
                    onClick = { onOptionSelected(label) },
                    role = Role.RadioButton,
                    indication = null,
                    interactionSource = MutableInteractionSource()
                )
                .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (selectedItem == label),
                    onClick = {onOptionSelected(label)},
                    colors = RadioButtonDefaults.colors(
                        selectedColor = LightGreenColor,
                        unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
                Text(
                    text = label,
                    modifier = Modifier.padding(top = 13.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

}


