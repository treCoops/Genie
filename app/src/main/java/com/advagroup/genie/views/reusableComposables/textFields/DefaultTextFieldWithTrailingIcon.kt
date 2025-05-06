package com.advagroup.genie.views.reusableComposables.textFields

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.SFPro

@Composable
fun DefaultTextFieldWithTrailingIcon(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    icon: ImageVector,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    visualTransformation: VisualTransformation,
    modifier: Modifier
) {

    TextField(
        value = value,
        onValueChange = { text ->
            onValueChange( text )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = SFPro,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp
            )
        },
        trailingIcon = {
           Icon(
               imageVector = icon,
               contentDescription = "Person Icon",
               tint = MaterialTheme.colorScheme.onSurfaceVariant,
               modifier = Modifier
                   .size(25.dp)
           )
        },
        textStyle = TextStyle(
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textDecoration = TextDecoration.None
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        modifier = modifier
            .height(70.dp),
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = EditTextBackgroundColor,
            unfocusedContainerColor = EditTextBackgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            //focusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
            //unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        singleLine = true
    )

}