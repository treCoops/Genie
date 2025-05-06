package com.advagroup.genie.views.screens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.ui.theme.SecondaryColor
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.textFields.DefaultTextFieldWithLeadingIcon

@Composable
fun LoginScreen(navController: NavController) {
    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        MainView(navController)
    }
}

@Composable
private fun MainView(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundImageComposable()
        ContentBackgroundComposable(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            navController
        )
    }
}


@Composable
private fun BackgroundImageComposable() {

    val windowSize = LocalWindowInfo.current.containerSize
    val height = with(LocalDensity.current) { windowSize.height.toDp() }

    Image(
        painterResource(R.drawable.login_background),
        contentDescription = "Login Screen Background Image Resource",
        modifier = Modifier
            .fillMaxWidth()
            .height((height / 2) - 100.dp ),
        contentScale = ContentScale.FillHeight
    )
}

@Composable
private fun ContentBackgroundComposable(modifier: Modifier, navController: NavController) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp),
        elevation = CardDefaults.cardElevation(
            30.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.66f)
                .padding(vertical = 30.dp, horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            WelcomeTextComposable()
            HeadingTextComposable()

            Spacer(modifier = Modifier.height(20.dp))

            DefaultTextFieldWithLeadingIcon(
                value = userName,
                onValueChange = {
                    userName = it
                },
                placeholder = "Username",
                icon = Icons.Outlined.Person,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                visualTransformation = VisualTransformation.None,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            DefaultTextFieldWithLeadingIcon(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = "Password",
                icon = Icons.Outlined.Lock,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
            )

            ForgetPasswordComposable()

            Spacer(modifier = Modifier.height(10.dp))

            DefaultFormButtonWithFill(
                title = "Login",
                paddingValues = PaddingValues(horizontal = 0.dp),
                { }
            )

            Spacer(modifier = Modifier.height(20.dp))

            OrDividerComposable()

            Spacer(modifier = Modifier.height(20.dp))

            NoAccountTextComposable()
            Spacer(modifier = Modifier.height(5.dp))
            RegisterTextComposable(onPressed = {
                println("sdfdsfsf")
                navController.navigate(Destinations.SignUpScreen.route)
            })
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
private fun ForgetPasswordComposable(){

    Text(
        text = stringResource(R.string.forget_password),
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        fontFamily = SFPro,
        fontWeight = FontWeight.Medium,
        color = SecondaryColor,
        fontSize = 16.sp
    )
}

@Composable
private fun WelcomeTextComposable() {
    Text(
        text = stringResource(R.string.welcome_back),
        fontFamily = SFPro,
        fontWeight = FontWeight.Light,
        fontSize = 27.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
private fun HeadingTextComposable() {
    Text(
        text = stringResource(R.string.login),
        fontFamily = SFPro,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(
            top = 15.dp
        )
    )
}

@Composable
private fun NoAccountTextComposable() {
    Text(
        text = stringResource(R.string.no_account),
        fontFamily = SFPro,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun RegisterTextComposable(onPressed: () -> Unit) {
    Text(
        text = stringResource(R.string.register_here),
        fontFamily = SFPro,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline,
        color = SecondaryColor,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onPressed,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    )
}

@Composable
private fun OrDividerComposable(){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        HorizontalDivider(
            color = SecondaryColor,
            thickness = 2.dp,
            modifier = Modifier
                .weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = stringResource(R.string.or),
            fontSize = 20.sp,
            fontFamily = SFPro,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(16.dp))

        HorizontalDivider(
            color = SecondaryColor,
            thickness = 2.dp,
            modifier = Modifier
                .weight(1f)
        )

    }
}