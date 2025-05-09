package com.advagroup.genie.views.screens.onboardingScreens.reminder

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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.dataModels.uiData.ReminderModel
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFill
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFillWithLeadingIcon
import com.advagroup.genie.views.reusableComposables.buttons.DefaultNavigationCircleButton
import com.advagroup.genie.views.reusableComposables.textGroups.ListItemTextGroupComposable
import com.advagroup.genie.views.reusableComposables.titleBar.DefaultNavigationTopBarWithIcon

@Composable
fun ReminderScreen(navController: NavController) {

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
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            onIconPressed = {
                navController.popBackStack()
            },
            headingTitle = stringResource(R.string.onboarding),
            modifier = Modifier
                .size(40.dp)
        )

        ContentView(navController)
    }

}

@Composable
private fun ContentView(navController: NavController) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState)
    ) {

        Text(
            text = stringResource(R.string.reminder_info),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds()
        ) {

            LazyColumn(
                modifier = Modifier
                    .height(400.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val dataSet = getCurrentReminderList()
                items(dataSet.size){
                    ReminderItemComposable(
                        dataSet[it],
                        onPressed = {},
                        onDeleted = {}
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(30.dp))

        DefaultFormButtonWithoutFillWithLeadingIcon(
            title = stringResource(R.string.add_reminder),
            iconVector = Icons.Filled.Add,
            paddingValues = PaddingValues()
        ) {
            navController.navigate(Destinations.AddReminderScreen.route)
        }

        Spacer(modifier = Modifier.height(60.dp))

        DefaultFormButtonWithFill(
            title = "Next",
            paddingValues = PaddingValues()
        ) {
            //navController.navigate(Destinations.FamilyScreen.route)
        }

        Spacer(modifier = Modifier.height(15.dp))

        DefaultFormButtonWithoutFill(
            "Skip",
            paddingValues = PaddingValues()
        ) {
            //navController.navigate(Destinations.FamilyScreen.route)
        }

        Spacer(modifier = Modifier.height(30.dp))

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