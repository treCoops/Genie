package com.advagroup.genie.views.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.DefaultFormButtonWithoutFill
import com.advagroup.genie.views.reusableComposables.DefaultNavigationTopBarWithIcon
import com.advagroup.genie.views.reusableComposables.DefaultTextField

@Composable
fun EmergencyContactInformationScreen(navController: NavController) {

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
    var personName by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(R.string.emergency_contact_info),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = personName,
            onValueChange = {
                personName = it
            },
            stringResource(R.string.emergency_person_name),
            stringResource(R.string.enter_name_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            stringResource(R.string.phone_number),
            stringResource(R.string.enter_phone_number_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            stringResource(R.string.email_address),
            stringResource(R.string.enter_email_address_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        DefaultFormButtonWithFill(
            title = "Next",
            paddingValues = PaddingValues(),
            {
                navController.navigate(Destinations.CareGiverInformationScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        DefaultFormButtonWithoutFill(
            "Skip",
            paddingValues = PaddingValues(),
            {
                navController.navigate(Destinations.CareGiverInformationScreen.route)
            }
        )

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
            fontSize = 17.sp
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
