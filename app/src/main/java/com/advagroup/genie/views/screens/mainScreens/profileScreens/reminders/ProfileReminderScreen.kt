package com.advagroup.genie.views.screens.mainScreens.profileScreens.reminders

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.dataModels.uiData.ReminderModel
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.LightPrimary
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFillWithLeadingIcon
import com.advagroup.genie.views.reusableComposables.buttons.DefaultNavigationCircleButton
import com.advagroup.genie.views.reusableComposables.textGroups.ListItemTextGroupComposable

@Composable
fun ProfileReminderScreen(navComparator: NavController) {

    var scrollScope = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollScope)
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.reminders),
            fontWeight = FontWeight.Bold,
            fontFamily = QuickSand,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 26.sp
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp),
            color = LightPrimary,
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(getCurrentReminderList().size) { index ->
                    ReminderItemComposable(
                        getCurrentReminderList()[index],
                        onPressed = {},
                        onDeleted = {}
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(15.dp))

        DefaultFormButtonWithoutFillWithLeadingIcon(
            title = stringResource(R.string.add_reminder),
            height = 60.dp,
            iconVector = Icons.Filled.Add,
            paddingValues = PaddingValues()
        ) {

        }

        Spacer(modifier = Modifier.height(15.dp))
    }

}

@Composable
private fun ReminderItemComposable(dataSet: ReminderModel, onPressed: () -> Unit, onDeleted: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(1.dp, LightGreenColor),
        modifier = Modifier
            .padding(bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                ListItemTextGroupComposable(
                    title = stringResource(R.string.name),
                    value = dataSet.name
                )

                DefaultNavigationCircleButton(
                    onClick = onPressed,
                    iconVector = Icons.AutoMirrored.Filled.ArrowForward,
                    iconModifier = Modifier.size(30.dp),
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(LightGreenColor)
                    .padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            ListItemTextGroupComposable(
                title = stringResource(R.string.reminder_days_heading),
                value = dataSet.dates
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                ListItemTextGroupComposable(
                    title = stringResource(R.string.time),
                    value = dataSet.time
                )

                IconButton(
                    onClick = onDeleted,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        "Delete Icon",
                        modifier = Modifier.size(30.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

fun getCurrentReminderList(): List<ReminderModel> {
    return listOf(
        ReminderModel(
            "Watch News",
            "Sun, Mon, Tue",
            "08:00 PM"
        ),
        ReminderModel(
            "Check Blood Glucose",
            "Sun",
            "10:00 AM"
        ),
        ReminderModel(
            "Check Blood Pressure",
            "Fri, Sat",
            "06:30 PM"
        )
    )
}