package com.advagroup.genie.views.reusableComposables.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro

@Composable
fun ReminderDateButton(value: String, checkedStates: MutableMap<String, Boolean>, modifier: Modifier, onClick : () -> Unit) {
    Surface(
        shape = CircleShape,
        color = if (checkedStates[value] == true) LightGreenColor else EditTextBackgroundColor,
        modifier = modifier
            .size(45.dp)
            .clickable(
                onClick = {
                    val currentStatus = checkedStates[value] == true
                    checkedStates[value] = !currentStatus
                    onClick
                },
                role = Role.Button,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        tonalElevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = value.substring(0, 1),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                fontFamily = SFPro,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}