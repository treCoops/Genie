package com.advagroup.genie.views.screens.mainScreens.profileScreens.interest

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
import com.advagroup.genie.dataModels.uiData.InterestModel
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.LightPrimary
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFillWithLeadingIcon
import com.advagroup.genie.views.reusableComposables.buttons.DefaultNavigationCircleButton
import com.advagroup.genie.views.reusableComposables.textGroups.ListItemMultiLineTextGroupComposable
import com.advagroup.genie.views.reusableComposables.textGroups.ListItemTextGroupComposable

@Composable
fun ProfileInterestScreen(navController: NavController) {

    var scrollScope = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollScope)
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.interests),
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
                items(getCurrentInterestList().size) { index ->
                    InterestItemComposable(
                        getCurrentInterestList()[index],
                        onPressed = {},
                        onDeleted = {}
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(15.dp))

        DefaultFormButtonWithoutFillWithLeadingIcon(
            title = stringResource(R.string.add_interest),
            height = 60.dp,
            iconVector = Icons.Filled.Add,
            paddingValues = PaddingValues()
        ) {

        }

        Spacer(modifier = Modifier.height(15.dp))
    }

}

@Composable
private fun InterestItemComposable(dataSet: InterestModel, onPressed: () -> Unit, onDeleted: () -> Unit) {
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
                    value = dataSet.interestName
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

            ListItemMultiLineTextGroupComposable(
                title = stringResource(R.string.description),
                value = dataSet.interestNote
            )

            IconButton(
                onClick = onDeleted,
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Icon(
                    Icons.Filled.Delete,
                    "Delete Icon",
                    modifier = Modifier.size(30.dp)
                        .padding(),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

        }

    }
}

private fun getCurrentInterestList(): List<InterestModel> {
    return listOf(
        InterestModel(
            "Photography",
            "I have a keen eye for detail and loves freezing moments in time through my camera lens. Whether it's a busy street or a quiet sunset, I enjoy telling stories through visuals."
        ),
        InterestModel(
            "Traveling",
            "Wanderlust I drive to discover new places, cultures, and experiences. I find joy in spontaneous road trips, meeting locals, and collecting memories from every journey."
        ),
        InterestModel(
            "Reading sci-fi novels",
            "I Fascinated by futuristic worlds and imaginative storytelling, I often escape into sci-fi novels. I enjoy the thrill of exploring alternate realities and complex characters."
        ),
        InterestModel(
            "Playing guitar",
            "Music is my favorite way to relax. Playing the guitar help me unwind and express himself, whether I am learning a new song or improvising melodies late at night."
        )
    )
}