package com.advagroup.genie.views.screens.onboardingScreens.signup

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
import androidx.compose.ui.platform.LocalContext
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.dataModels.uiData.FormValidationModel
import com.advagroup.genie.db.DB
import com.advagroup.genie.db.entities.UserEntity
import com.advagroup.genie.db.repositories.UserRepository
import com.advagroup.genie.helpers.Validator
import com.advagroup.genie.helpers.convertTimeMillisLongToString
import com.advagroup.genie.ui.theme.DangerColor
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

    val mContext = LocalContext.current.applicationContext
    val db = DB.getInstance(mContext)
    val repository = UserRepository(db)

    val viewModel : SignUpScreenViewModel = viewModel(
        factory = SignUpScreenViewModelFactory(repository)
    )


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

        ContentView(viewModel, navController)
    }
}

@Composable
private fun ContentView(viewModel: SignUpScreenViewModel, navController: NavController) {

    val scrollState = rememberScrollState()

    var firstName by remember { mutableStateOf("") }
    var firstNameErrorHandler by remember { mutableStateOf(FormValidationModel(false)) }
    val firstNameEmptyErrorMessage = stringResource(R.string.signup_first_name_empty)

    var lastName by remember { mutableStateOf("") }
    var lastNameErrorHandler by remember { mutableStateOf(FormValidationModel(false)) }
    val lastNameEmptyErrorMessage = stringResource(R.string.signup_last_name_empty)

    var dateOfBirth by remember { mutableStateOf("") }
    var dateOfBirthErrorHandler by remember { mutableStateOf(FormValidationModel(false)) }
    val dateOfEmptyErrorMessage = stringResource(R.string.signup_dob_empty)

    val radioOptions = listOf("Male", "Female")
    val (selectedGenderItem, onOptionSelected) = remember {
        mutableStateOf(radioOptions[0])
    }

    var userName by remember { mutableStateOf("") }
    var userNameErrorHandler by remember { mutableStateOf(FormValidationModel(false)) }
    val userNameEmptyErrorMessage = stringResource(R.string.signup_username_empty)


    var password by remember { mutableStateOf("") }
    var passwordErrorHandler by remember { mutableStateOf(FormValidationModel(false)) }
    val passwordEmptyErrorMessage = stringResource(R.string.signup_password_empty)
    val passwordSizeErrorMessage = stringResource(R.string.signup_password_size)

    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordErrorHandler by remember { mutableStateOf(FormValidationModel(false)) }
    val confirmPasswordEmptyErrorMessage = stringResource(R.string.signup_confirm_password_empty)
    val confirmPasswordMismatchErrorMessage = stringResource(R.string.signup_password_mismatched)

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

                firstNameErrorHandler = if(Validator.getInstance().isStringEmpty(firstName)) {
                    FormValidationModel(true, firstNameEmptyErrorMessage)
                } else {
                    FormValidationModel(false)
                }
            },
            firstNameErrorHandler,
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
                lastNameErrorHandler = if(Validator.getInstance().isStringEmpty(lastName)) {
                    FormValidationModel(true, lastNameEmptyErrorMessage)
                } else {
                    FormValidationModel(false)
                }
            },
            lastNameErrorHandler,
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
            errorHandler = dateOfBirthErrorHandler,
            onClicked = {
                showDatePicker = true
            }
        )

        if (showDatePicker) {
            DatePickerComposable(
                onDateSelected = { selectedDate ->
                    selectedDate?.let {
                        dateOfBirth = it.convertTimeMillisLongToString("yyyy-MM-dd")

                        dateOfBirthErrorHandler = if(Validator.getInstance().isStringEmpty(dateOfBirth)) {
                            FormValidationModel(true, dateOfEmptyErrorMessage)
                        } else {
                            FormValidationModel(false)
                        }
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
            selectedItem = selectedGenderItem,
            onOptionSelected = { item ->
                onOptionSelected(item)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = userName,
            onValueChange = {
                userName = it

                userNameErrorHandler = if(Validator.getInstance().isStringEmpty(userName)) {
                    FormValidationModel(true, userNameEmptyErrorMessage)
                } else {
                    FormValidationModel(false)
                }
            },
            userNameErrorHandler,
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

                passwordErrorHandler = if(Validator.getInstance().isStringEmpty(password)) {
                    FormValidationModel(true, passwordEmptyErrorMessage)
                } else {
                    if(Validator.getInstance().textCount(password, 8)) {
                        FormValidationModel(false)
                    } else {
                        FormValidationModel(true, passwordSizeErrorMessage)
                    }
                }
            },
            passwordErrorHandler,
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

                confirmPasswordErrorHandler = if (Validator.getInstance().isStringEmpty(confirmPassword)) {
                    FormValidationModel(true, confirmPasswordEmptyErrorMessage)
                } else {

                    if(Validator.getInstance().textCount(confirmPassword, 8)) {
                        if (Validator.getInstance().isPasswordSame(password, confirmPassword)) {
                            FormValidationModel(false)
                        } else {
                            FormValidationModel(true, confirmPasswordMismatchErrorMessage)
                        }
                    } else {
                        FormValidationModel(true, passwordSizeErrorMessage)
                    }
                }
            },
            confirmPasswordErrorHandler,
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
            paddingValues = PaddingValues()
        ) {

            var validationFlag = 0

            firstNameErrorHandler = if (Validator.getInstance().isStringEmpty(firstName)) {
                validationFlag = 1
                FormValidationModel(true, firstNameEmptyErrorMessage)
            } else {
                FormValidationModel(false)
            }

            lastNameErrorHandler = if (Validator.getInstance().isStringEmpty(lastName)) {
                validationFlag = 1
                FormValidationModel(true, lastNameEmptyErrorMessage)
            } else {
                FormValidationModel(false)
            }

            dateOfBirthErrorHandler = if (Validator.getInstance().isStringEmpty(dateOfBirth)) {
                validationFlag = 1
                FormValidationModel(true, dateOfEmptyErrorMessage)
            } else {
                FormValidationModel(false)
            }

            userNameErrorHandler = if (Validator.getInstance().isStringEmpty(userName)) {
                validationFlag = 1
                FormValidationModel(true, userNameEmptyErrorMessage)
            } else {
                FormValidationModel(false)
            }

            passwordErrorHandler = if (Validator.getInstance().isStringEmpty(password)) {
                validationFlag = 1
                FormValidationModel(true, passwordEmptyErrorMessage)
            } else {
                if(Validator.getInstance().textCount(confirmPassword, 8)) {
                    FormValidationModel(false)
                } else {
                    validationFlag = 1
                    FormValidationModel(true, passwordSizeErrorMessage)
                }
            }

            confirmPasswordErrorHandler = if (Validator.getInstance().isStringEmpty(confirmPassword)) {
                validationFlag = 1
                FormValidationModel(true, confirmPasswordEmptyErrorMessage)
            } else {
                if(Validator.getInstance().textCount(confirmPassword, 8)) {
                    if (Validator.getInstance().isPasswordSame(password, confirmPassword)) {
                        FormValidationModel(false)
                    } else {
                        validationFlag = 1
                        FormValidationModel(true, confirmPasswordMismatchErrorMessage)
                    }
                } else {
                    validationFlag = 1
                    FormValidationModel(true, passwordSizeErrorMessage)
                }
            }

            if(validationFlag == 0) {

                val userEntity = UserEntity(
                    0,
                    firstName,
                    lastName,
                    dateOfBirth,
                    selectedGenderItem,
                    userName,
                    password
                )

                viewModel.saveUser(userEntity)
            }

            //navController.navigate(Destinations.EmergencyContactInformationScreen.route)
        }

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
private fun TextFieldComposable(value: String, onValueChange: (String) -> Unit, errorHandler: FormValidationModel, title: String, placeholder: String, isPassword: Boolean = false, keyboardOptions: KeyboardOptions) {

    val visualTransformation: VisualTransformation = if(isPassword){
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
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
            onValueChange = {
                onValueChange(it)
            },
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

        if(errorHandler.isError) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = errorHandler.errorMessage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp),
                fontFamily = SFPro,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = DangerColor
            )
        }

    }
}

@Composable
private fun DateOfBirthComposable(title: String, value: String, errorHandler: FormValidationModel, onClicked: () -> Unit) {

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

        if(errorHandler.isError) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = errorHandler.errorMessage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp),
                fontFamily = SFPro,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = DangerColor
            )
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


