package com.advagroup.genie.views.screens.onboardingScreens.familyMember

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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFill
import com.advagroup.genie.views.reusableComposables.textFields.DefaultTextField
import com.advagroup.genie.views.reusableComposables.titleBar.DefaultNavigationTopBarWithIcon

@Composable
fun AddFamilyMemberScreen(navController: NavController) {

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
            headingTitle = stringResource(R.string.add_member_heading),
            modifier = Modifier
                .size(30.dp)
        )

        ContentView(navController)
    }
}

@Composable
private fun ContentView(navController: NavController) {

    val scrollState = rememberScrollState()

    var memberName by remember { mutableStateOf("") }
    var memberEmail by remember { mutableStateOf("") }
    var memberPhoneNumber by remember { mutableStateOf("") }

    val relationshipTypes = listOf(
        "Select the relationship",
        "Father",
        "Mother",
        "Son",
        "Daughter",
        "Brother",
        "Sister",
        "Spouse",
        "Child",
        "Grandparent"
    )

    var selectedOption by remember {
        mutableStateOf(relationshipTypes[0])
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState)
    ) {

        Text(
            text = stringResource(R.string.family_member_details),
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
            value = memberName,
            onValueChange = {
                memberName = it
            },
            stringResource(R.string.family_member_name_enter),
            stringResource(R.string.enter_name_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        RelationshipTypeSelectComposable(
            stringResource(R.string.relationship_type_full),
            selectedRelationshipType = selectedOption,
            optionList = relationshipTypes,
            onItemChanged = {
                selectedOption = it
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = memberEmail,
            onValueChange = {
                memberEmail = it
            },
            stringResource(R.string.email_address),
            stringResource(R.string.enter_email_address_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldComposable(
            value = memberPhoneNumber,
            onValueChange = {
                memberPhoneNumber = it
            },
            stringResource(R.string.phone_number),
            stringResource(R.string.enter_phone_number_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
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
private fun RelationshipTypeSelectComposable(title: String, selectedRelationshipType: String, optionList: List<String>, onItemChanged: (selectedItem: String) -> Unit) {

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
                        value = selectedRelationshipType,
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
                            color = if(selectedRelationshipType == stringResource(R.string.select_relationship_type)) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground,
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
                                        color = if(item == stringResource(R.string.select_relationship_type)) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground,
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