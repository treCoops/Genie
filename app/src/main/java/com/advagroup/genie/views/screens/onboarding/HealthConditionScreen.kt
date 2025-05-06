package com.advagroup.genie.views.screens.onboarding

import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFill
import com.advagroup.genie.views.reusableComposables.titleBar.DefaultNavigationTopBarWithIcon
import com.advagroup.genie.views.reusableComposables.textFields.DefaultTextFieldMultiline

@Composable
fun HealthConditionScreen(navController: NavController) {

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
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            onIconPressed = {
                navController.popBackStack()
            },
            headingTitle = stringResource(R.string.onboarding),
            modifier = Modifier
                .size(40.dp)
        )

        ContentView(navController)
    }

}

@Composable
private fun ContentView(navController: NavController) {

    val scrollState = rememberScrollState()

    val healthConditionsList = listOf(
        "Hypertension",
        "Diabetes",
        "Arthritis",
        "Heart Disease",
        "Osteoporosis",
        "Dementia (including Alzheimer)",
        "Vision Problems",
        "Kidney Disease",
        "Cancer",
        "Stroke",
        "Sleep Disorders",
    )

    val checkedStatesHealthConditions = remember { mutableStateMapOf<String, Boolean>() }

    var notes by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(R.string.health_conditions),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.please_select_health_conditions),
            modifier = Modifier
                .fillMaxWidth(),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(10.dp))

        ConditionCheckBoxesComposable(
            healthConditions = healthConditionsList,
            checkedStates = checkedStatesHealthConditions
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = notes,
            onValueChange = {
                notes = it
            },
            title = stringResource(R.string.notes),
            placeholder = stringResource(R.string.additional_notes),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )

        )

        Spacer(modifier = Modifier.height(50.dp))

        DefaultFormButtonWithFill(
            title = "Next",
            paddingValues = PaddingValues()
        ) {
            navController.navigate(Destinations.MedicationScreen.route)
        }

        Spacer(modifier = Modifier.height(15.dp))

        DefaultFormButtonWithoutFill(
            "Skip",
            paddingValues = PaddingValues()
        ) {
            navController.navigate(Destinations.MedicationScreen.route)
        }

        Spacer(modifier = Modifier.height(50.dp))
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

        DefaultTextFieldMultiline(
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

@Composable
private fun ConditionCheckBoxesComposable(healthConditions: List<String>, checkedStates: MutableMap<String, Boolean>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        healthConditions.forEach { condition ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = checkedStates[condition] == true,
                        role = Role.Checkbox,
                        onClick = {
                            val currentStatus = checkedStates[condition] == true
                            checkedStates[condition] = !currentStatus
                        }
                    )
            ) {
                Box(modifier = Modifier.size(40.dp)) {
                    Checkbox(
                        checked = checkedStates[condition] == true,
                        onCheckedChange = { isChecked ->
                            checkedStates[condition] = isChecked
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