package com.advagroup.genie.views.splash

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.advagroup.genie.R
import com.advagroup.genie.ui.theme.GenieTheme
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.main.MainActivity
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GenieTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainView(modifier: Modifier) {

    val context = LocalContext.current.applicationContext

    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            1f,
            animationSpec = tween(1500, easing = EaseIn)
        )
        delay(2000)

        val intent = Intent(context, MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        (context as? Activity)?.finish()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoImageComposable()
            AppNameComposable()

        }
        BottomTextComposable(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        )
    }

}

@Composable
fun LogoImageComposable() {
    Image(
        painter = painterResource(R.drawable.app_icon),
        contentDescription = "Logo Image Resource"
    )
}

@Composable
fun AppNameComposable() {
    Text(
        text = stringResource(R.string.app_name),
        textAlign = TextAlign.Center,
        fontFamily = QuickSand,
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp,
        modifier = Modifier
            .padding(top = 10.dp)
    )
}

@Composable
fun BottomTextComposable(modifier: Modifier) {
    Text(
        text = stringResource(R.string.app_splash_sub_title),
        modifier = modifier,
        fontFamily = SFPro,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    )
}