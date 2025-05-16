package com.advagroup.genie.views.screens.mainScreens.profileScreen.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.advagroup.genie.ui.theme.LightPrimary
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultNavigationCircleButton

@Composable
fun ProfileAccountScreen(navController: NavController) {

    var scrollScope = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollScope)
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = stringResource(R.string.account),
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
        ){

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                DefaultNavigationCircleButton(
                    onClick = {

                    },
                    iconVector = Icons.Filled.Edit,
                    iconModifier = Modifier.size(30.dp),
                    modifier = Modifier.align(Alignment.TopEnd)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 15.dp)
                ) {

                    DetailFieldComposable(stringResource(R.string.first_name), value = "John")

                    Spacer(modifier = Modifier.height(20.dp))

                    DetailFieldComposable(stringResource(R.string.last_name), value = "Paul")

                    Spacer(modifier = Modifier.height(20.dp))

                    DetailFieldComposable(stringResource(R.string.birthday), value = "28/10/1970")

                    Spacer(modifier = Modifier.height(20.dp))

                    DetailFieldComposable(stringResource(R.string.gender), value = "Male")

                }
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.emergency_contact),
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
        ){

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                DefaultNavigationCircleButton(
                    onClick = {

                    },
                    iconVector = Icons.Filled.Edit,
                    iconModifier = Modifier.size(30.dp),
                    modifier = Modifier.align(Alignment.TopEnd)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 15.dp)
                ) {

                    DetailFieldComposable(stringResource(R.string.name), value = "Micheal Stark")

                    Spacer(modifier = Modifier.height(20.dp))

                    DetailFieldComposable(stringResource(R.string.email_address), value = "michealstark@gmail.com")

                    Spacer(modifier = Modifier.height(20.dp))

                    DetailFieldComposable(stringResource(R.string.contact_number), value = "+61876590843")

                }
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.care_giver_details),
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
        ){

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                DefaultNavigationCircleButton(
                    onClick = {

                    },
                    iconVector = Icons.Filled.Edit,
                    iconModifier = Modifier.size(30.dp),
                    modifier = Modifier.align(Alignment.TopEnd)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 15.dp)
                ) {

                    DetailFieldComposable(stringResource(R.string.name), value = "Steve Smith")

                    Spacer(modifier = Modifier.height(20.dp))

                    DetailFieldComposable(stringResource(R.string.email_address), value = "stevesmith@gmail.com")

                    Spacer(modifier = Modifier.height(20.dp))

                    DetailFieldComposable(stringResource(R.string.contact_number), value = "+61879645632")

                }
            }

        }

        Spacer(modifier = Modifier.height(20.dp))
    }

}

@Composable
private fun DetailFieldComposable(title: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = title,
            fontFamily = SFPro,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            fontFamily = QuickSand,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(4.dp))

        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            thickness = 1.dp
        )

    }
}