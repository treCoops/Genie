package com.advagroup.genie.views.bottomNavigation


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.advagroup.genie.dataModels.uiData.BottomNavigationItemDataModel
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavigationItemDataModel.HomeScreen,
        BottomNavigationItemDataModel.ProfileScreen,
        BottomNavigationItemDataModel.SettingsScreen
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 30.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = LightGreenColor.copy(alpha = 0.9f),
                spotColor = LightGreenColor.copy(alpha = 0.9f),
                clip = true
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                .background(Color.Transparent)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }


}

@Composable
fun RowScope.AddItem(screen: BottomNavigationItemDataModel, currentDestination: NavDestination?, navController: NavHostController) {

    val selected = currentDestination?.hierarchy?.any {it.route == screen.route}

    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (selected == true) LightGreenColor else Color.Transparent,
        animationSpec = tween(durationMillis = 300)
    )

    val animatedContentColor by animateColorAsState(
        targetValue = if (selected == true) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = tween(durationMillis = 300)
    )

    val scale by animateFloatAsState(
        targetValue = if (selected == true) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .height(50.dp)
            .clip(CircleShape)
            .background(animatedBackgroundColor)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.animateContentSize()
        ) {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = "icon",
                tint = animatedContentColor,
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .padding(2.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            AnimatedVisibility(
                visible = selected == true,
                enter = fadeIn(tween(300)) + slideInHorizontally { fullWidth -> fullWidth / 2 },
                exit = fadeOut(tween(200)) + slideOutHorizontally { fullWidth -> fullWidth / 2 }
            ) {
                Text(
                    text = stringResource(screen.title),
                    fontFamily = SFPro,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = animatedContentColor,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}