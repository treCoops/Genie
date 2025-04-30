package com.advagroup.genie.views.reusableComposables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro

@Composable
fun DefaultFormButtonWithFill(title: String, onClicked : () -> Unit) {

    Button(
        onClick = onClicked,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 30.dp)
            .border(
                width = 2.dp,
                color = LightGreenColor,
                shape = RoundedCornerShape(18.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            LightGreenColor,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Text(
            text = title,
            fontFamily = SFPro,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }

}
