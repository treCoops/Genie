package com.advagroup.genie.views.screens.mainScreens.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.dataModels.uiData.FeelingMoodModel
import com.advagroup.genie.dataModels.uiData.UpcomingModel
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.GoldLight
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.LightPrimary
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.views.reusableComposables.titleBar.DefaultHomeScreenTopBar
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun HomeScreen(navController: NavController) {

    Surface (
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
        DefaultHomeScreenTopBar(navController, isNotificationsAvailable = true)

        ContentView(navController)
    }

}

@Composable
private fun ContentView(navController: NavController) {

    var scrollState = rememberScrollState()
    val moodList = getMoodList()

    var upcomingList = getUpcomingList()

    var selectedMoodItem by remember {
        mutableStateOf(moodList[0])
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            elevation = CardDefaults.cardElevation(
                20.dp
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 15.dp)
            ) {
                Text(
                    text = stringResource(R.string.how_is_your_mood_today),
                    fontWeight = FontWeight.Medium,
                    fontFamily = QuickSand,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    items(moodList.size){ index ->
                        MoodItemComposable(
                            moodList[index],
                            selectedMoodItem,
                            onClick = { selectedItem ->
                                selectedMoodItem = selectedItem
                            }
                        )
                    }

                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = stringResource(R.string.calm_and_connect),
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

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            LottieWithTitleComposable(
                backgroundColor = EditTextBackgroundColor,
                backgroundModifier = Modifier
                    .weight(1f)
                    .height(200.dp),
                lottieModifier = Modifier
                    .height(150.dp),
                lottieAnimationJson = R.raw.breath,
                stringResource(R.string.relay_me)
            )

            LottieWithTitleComposable(
                backgroundColor = GoldLight,
                backgroundModifier = Modifier
                    .weight(1f)
                    .height(200.dp),
                lottieModifier = Modifier
                    .height(150.dp),
                lottieAnimationJson = R.raw.bot,
                stringResource(R.string.lets_talk)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = stringResource(R.string.upcoming),
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

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(upcomingList.size) { index ->
                UpcomingItemComposable(upcomingList[index])
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

    }

}

@Composable
private fun LottieWithTitleComposable(backgroundColor: Color, backgroundModifier: Modifier, lottieModifier: Modifier, lottieAnimationJson: Int, title: String) {

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(20.dp),
        modifier = backgroundModifier
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieViewComposable(
                lottieAnimationJson,
                modifier = lottieModifier
            )

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontFamily = QuickSand,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 26.sp
            )
        }

    }

}

@Composable
private fun LottieViewComposable(lottieJson: Int, modifier: Modifier) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(lottieJson))

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier,
        speed = 2.5f
    )

}

@Composable
private fun UpcomingItemComposable(dataSet: UpcomingModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            20.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(IntrinsicSize.Min)
        ) {

            Column(
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .background(LightGreenColor)
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = dataSet.date,
                    fontSize = 19.sp,
                    fontFamily = QuickSand,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = dataSet.time,
                    fontSize = 19.sp,
                    fontFamily = QuickSand,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight(),
                thickness = 2.dp,
                color = LightPrimary
            )

            Text(
                text = dataSet.title,
                fontSize = 19.sp,
                fontFamily = QuickSand,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 5.dp)
                    .align(Alignment.CenterVertically)
            )

        }

    }
}

@Composable
private fun MoodItemComposable(moodItem: FeelingMoodModel, selectedMoodItem: FeelingMoodModel, onClick: (selectedItem: FeelingMoodModel) -> Unit) {

    val isSelected = selectedMoodItem.name == moodItem.name

    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .width(75.dp)
            .selectable(
                selected = (selectedMoodItem.name == moodItem.name),
                onClick = { onClick(moodItem) },
                role = Role.Button,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        color = if (isSelected) LightGreenColor else EditTextBackgroundColor,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(moodItem.icon),
                contentDescription = "Mood Icon Drawable",
                modifier = Modifier.size(45.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = moodItem.name,
                fontFamily = QuickSand,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

}

private fun getMoodList(): List<FeelingMoodModel> {
    return listOf(
        FeelingMoodModel("Happy", R.drawable.emotion_happy),
        FeelingMoodModel("Okay", R.drawable.emotion_okay),
        FeelingMoodModel("Down", R.drawable.emotion_down),
        FeelingMoodModel("Sad", R.drawable.emotion_sad),
        FeelingMoodModel("Angry", R.drawable.emotion_angry)
    )
}

private fun getUpcomingList(): List<UpcomingModel> {
    return listOf(
        UpcomingModel("Today", "10:30", "Watch football match"),
        UpcomingModel("Today", "14:00", "Talk with son about school"),
        UpcomingModel("Tomorrow", "11:00", "Call alice about her new house")
    )
}