package com.advagroup.genie.views.reusableComposables.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro

@Composable
fun DefaultFormButtonWithoutFillWithLeadingIcon(title: String, height: Dp, iconVector: ImageVector, paddingValues: PaddingValues, onClicked : () -> Unit) {

    Button(
        onClick = onClicked,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(paddingValues)
            .clip(RoundedCornerShape(18.dp))
            .border(
                width = 2.dp,
                color = LightGreenColor,
                shape = RoundedCornerShape(18.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Surface(
                shape = CircleShape,
                color = EditTextBackgroundColor,
                modifier = Modifier
                    .size(40.dp),
                tonalElevation = 2.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = iconVector,
                        contentDescription = "Icon",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }

            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 15.dp),
                fontFamily = SFPro,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

        }
    }

}
