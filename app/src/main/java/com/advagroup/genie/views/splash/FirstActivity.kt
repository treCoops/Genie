package com.advagroup.genie.views.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.advagroup.genie.R
import com.advagroup.genie.ui.theme.GinieTheme
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.main.MainActivity

class FirstActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GinieTheme {
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

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}

@Composable
fun MainView(modifier: Modifier) {

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