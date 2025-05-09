package com.advagroup.genie.views.screens.onboardingScreens.medication

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.helpers.get12HoursTime
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFill
import com.advagroup.genie.views.reusableComposables.pickers.TimePickerDialog
import com.advagroup.genie.views.reusableComposables.titleBar.DefaultNavigationTopBarWithIcon
import com.advagroup.genie.views.reusableComposables.textFields.DefaultTextField
import java.util.Calendar
import kotlin.collections.set

@Composable
fun AddMedicationScreen(navController: NavController) {

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
            headingTitle = stringResource(R.string.add_medication_heading),
            modifier = Modifier
                .size(30.dp)
        )

        ContentView(navController)
    }
}

@Composable
private fun ContentView(navController: NavController) {

    val scrollState = rememberScrollState()

    val medicationTypes = listOf("Select the medication type","Tablets", "Syrup", "Inhaler")
    var selectedOption by remember {
        mutableStateOf(medicationTypes[0])
    }

    var medicationName by remember { mutableStateOf("") }

    val radioOptions = listOf("After the meal", "Before the meal")

    val (selectedItem, onOptionSelected) = remember {
        mutableStateOf(radioOptions[0])
    }

    val frequencyConditionsList = listOf(
        "Morning",
        "Afternoon",
        "Evening",
        "Night",
        "Specific Time"
    )

    val checkedStatesFrequencyConditions = remember { mutableStateMapOf<String, Boolean>() }

    var medicineTime by remember { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }
    var showTimePickerComponent by remember { mutableStateOf(false) }
    var medicineDosage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(R.string.medication_details),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(30.dp))

        MedicationTypeSelectComposable(
            stringResource(R.string.medication_type_full),
            selectedMedicationType = selectedOption,
            optionList = medicationTypes,
            onItemChanged = {
                selectedOption = it
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = medicationName,
            onValueChange = {
                medicationName = it
            },
            stringResource(R.string.medication_name),
            stringResource(R.string.enter_medication_name_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        TakeRadioButtonGroupComposable(
            title = stringResource(R.string.medication_take),
            radioOptions = radioOptions,
            selectedItem = selectedItem,
            onOptionSelected = { item ->
                onOptionSelected(item)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.medication_frequency_full),
            modifier = Modifier
                .fillMaxWidth(),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(10.dp))

        FrequencyCheckBoxesComposable(
            frequencyConditions = frequencyConditionsList,
            checkedStates = checkedStatesFrequencyConditions,
            onChecked = {
                if(it == "Specific Time"){
                    showTimePickerComponent = (checkedStatesFrequencyConditions[it] == true)
                }

            }
        )

        if(showTimePickerComponent){
            Spacer(modifier = Modifier.height(15.dp))

            MedicineTimeComposable(
                value = medicineTime,
                onClicked = {
                    showTimePicker = true
                }
            )

            if (showTimePicker) {
                TimePickerComposable(
                    onTimeSelected = { hour, minutes ->
                        medicineTime = get12HoursTime(hour, minutes)
                        showTimePicker = false
                    },
                    onDismiss = {
                        showTimePicker = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = medicineDosage,
            onValueChange = {
                medicineDosage = it
            },
            stringResource(R.string.medication_dosage_full),
            stringResource(R.string.enter_medication_dosage_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

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
private fun MedicineTimeComposable(value: String, onClicked: () -> Unit) {
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
                    value = stringResource(R.string.select_the_time),
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
                imageVector = Icons.Filled.DateRange,
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
private fun FrequencyCheckBoxesComposable(frequencyConditions: List<String>, checkedStates: MutableMap<String, Boolean>, onChecked: (item: String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        frequencyConditions.forEach { condition ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = checkedStates[condition] == true,
                        role = Role.Checkbox,
                        onClick = {
                            val currentStatus = checkedStates[condition] == true
                            checkedStates[condition] = !currentStatus
                            onChecked(condition)
                        }
                    )
            ) {
                Box(modifier = Modifier.size(40.dp)) {
                    Checkbox(
                        checked = checkedStates[condition] == true,
                        onCheckedChange = { isChecked ->
                            checkedStates[condition] = isChecked
                            onChecked(condition)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = LightGreenColor,
                            uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }

                Text(
                    text = condition,
                    modifier = Modifier
                        .padding(top = 9.dp, start = 10.dp)
                        .fillMaxWidth(),
                    fontFamily = SFPro,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

        }
    }
}

@Composable
private fun TakeRadioButtonGroupComposable(title: String, radioOptions: List<String>, selectedItem: String, onOptionSelected: (label: String) -> Unit) {

    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth(),
        fontFamily = SFPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Column (
        modifier = Modifier
            .selectableGroup()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
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
                .fillMaxWidth()
            ) {
                Box(modifier = Modifier.size(40.dp)) {
                    RadioButton(
                        selected = (selectedItem == label),
                        onClick = {onOptionSelected(label)},
                        colors = RadioButtonDefaults.colors(
                            selectedColor = LightGreenColor,
                            unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
                Text(
                    text = label,
                    modifier = Modifier.padding(top = 8.dp, start = 10.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MedicationTypeSelectComposable(title: String, selectedMedicationType: String, optionList: List<String>, onItemChanged: (selectedItem: String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }

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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = EditTextBackgroundColor
            )
        ) {
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .fillMaxSize(),
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ){

                    TextField(
                        value = selectedMedicationType,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontFamily = SFPro,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            color = if(selectedMedicationType == stringResource(R.string.select_medication_type)) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground,
                            textDecoration = TextDecoration.None
                        ),
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        optionList.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = item,
                                        fontFamily = SFPro,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 17.sp,
                                        color = if(item == stringResource(R.string.select_medication_type)) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground,
                                        textDecoration = TextDecoration.None
                                    )
                                },
                                onClick = {
                                    onItemChanged(item)
                                    expanded = false
                                }
                            )
                        }
                    }

                }
            }

        }
    }
}