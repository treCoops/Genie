package com.advagroup.genie.views.reusableComposables.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.advagroup.genie.ui.theme.LightGreenColor

@Composable
fun DefaultNavigationCircleButton(modifier: Modifier, iconVector: ImageVector, iconModifier: Modifier, onClick : () -> Unit) {
    Surface(
        shape = CircleShape,
        color = LightGreenColor,
        modifier = modifier
            .size(40.dp)
            .clickable(
                onClick = onClick,
                role = Role.Button,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = iconVector,
                modifier = iconModifier,
                contentDescription = "Icon",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}