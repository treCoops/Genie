package com.advagroup.genie.views.reusableComposables.titleBar


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultNavigationCircleButton

@Composable
fun DefaultNavigationTopBarWithIcon(imageVector: ImageVector, modifier: Modifier, headingTitle: String, onIconPressed: () -> Unit) {

    val windowSize = LocalWindowInfo.current.containerSize
    val height = with(LocalDensity.current) { windowSize.height.toDp() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height((height / 4) - 110.dp ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            30.dp
        ),
        shape = RectangleShape
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
        ) {
            DefaultNavigationCircleButton(
                onClick = onIconPressed,
                modifier = Modifier
                    .align(Alignment.CenterStart),
                iconVector = imageVector,
                iconModifier = modifier
            )

            Text(
                text = headingTitle,
                modifier = Modifier
                    .align(Alignment.Center),
                fontFamily = SFPro,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}

