package com.advagroup.genie.views.screens.welcomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.DefaultGrayDark
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.DefaultFormButtonWithoutFill

@Composable
fun WelcomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LoginMainScreen(
            modifier = Modifier.padding(innerPadding),
            navController
        )
    }
}

@Composable
private fun LoginMainScreen(modifier: Modifier, navController: NavController) {
    val scrollState = rememberScrollState()

    /* Column(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageViewComposable()
        WelcomeHeadingTextComposable()
        WelcomeTextScrollerComposable()
        ButtonContainerComposable()
    } */

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageViewComposable()
            WelcomeHeadingTextComposable()
            WelcomeTextScrollerComposable()
        }

        ButtonContainerComposable(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp)
                .align(Alignment.BottomCenter),
            navController
        )
    }
}

@Composable
private fun ImageViewComposable() {
    Image(
        painter = painterResource(R.drawable.welcome),
        contentDescription = "Main image resources",
    )
}

@Composable
private fun WelcomeHeadingTextComposable(){
    Text(
        text = stringResource(R.string.title_welcome_screen),
        fontFamily = SFPro,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    )
}

@Composable
private fun ButtonContainerComposable(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        DefaultFormButtonWithoutFill(
            "Skip",
            paddingValues = PaddingValues(horizontal = 20.dp),
            {
                navController.navigate(Destinations.LoginScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        DefaultFormButtonWithFill(
            "Next",
            paddingValues = PaddingValues(horizontal = 20.dp),
            {
                navController.navigate(Destinations.LoginScreen.route)
            }
        )
    }
}

@Composable
private fun WelcomeTextScrollerComposable() {

    val welcomeMessages = stringArrayResource(R.array.welcome_screen_sentences).toList()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { welcomeMessages.size }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = welcomeMessages[page],
                    fontFamily = SFPro,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp, start = 50.dp, end = 50.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(welcomeMessages.size) { index ->
                val selected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .size(if (selected) 12.dp else 12.dp)
                        .background(
                            color = if (selected) LightGreenColor else DefaultGrayDark,
                            shape = MaterialTheme.shapes.small
                        )
                )
            }
        }
    }
}