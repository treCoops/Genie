package com.advagroup.genie.views.reusableComposables.titleBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.helpers.getTodayDateForDashboard
import com.advagroup.genie.ui.theme.DarkPrimary
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.views.reusableComposables.buttons.DefaultNavigationCircleButtonWithStroke

@Composable
fun DefaultHomeScreenTopBar(navController: NavController, isNotificationsAvailable: Boolean) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            30.dp
        ),
        shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, top = 20.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier.wrapContentSize()
            ) {
                Text(
                    text = "Hello, John",
                    fontWeight = FontWeight.Bold,
                    fontFamily = QuickSand,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 32.sp
                )

                Text(
                    text = "Today, ${getTodayDateForDashboard()}",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = QuickSand,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 25.sp
                )
            }

            Box {
                DefaultNavigationCircleButtonWithStroke(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.CenterStart),
                    iconVector = Icons.Filled.NotificationsNone,
                    iconModifier = Modifier.size(35.dp)
                )

                if(isNotificationsAvailable){
                    Surface(
                        modifier = Modifier
                            .size(15.dp)
                            .align(Alignment.TopEnd),
                        shape = CircleShape,
                        color = DarkPrimary
                    ) {}
                }
            }

        }
    }

}