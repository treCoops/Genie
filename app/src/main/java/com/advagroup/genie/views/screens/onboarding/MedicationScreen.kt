package com.advagroup.genie.views.screens.onboarding

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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.advagroup.genie.R
import com.advagroup.genie.dataModels.uiData.MedicationModel
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithFill
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFill
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFillWithLeadingIcon
import com.advagroup.genie.views.reusableComposables.titleBar.DefaultNavigationTopBarWithIcon

@Composable
fun MedicationScreen(navController: NavController) {

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
            text = stringResource(R.string.current_medications),
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
                val dataSet = getCurrentMedicationsList()
                items(dataSet.size){
                    MedicationItemComposable(dataSet[it])
                }
            }

        }

        Spacer(modifier = Modifier.height(30.dp))

        DefaultFormButtonWithoutFillWithLeadingIcon(
            title = stringResource(R.string.add_medication),
            iconVector = Icons.Filled.Add,
            paddingValues = PaddingValues()
        ) {
            navController.navigate(Destinations.AddMedicationScreen.route)
        }

        Spacer(modifier = Modifier.height(60.dp))

        DefaultFormButtonWithFill(
            title = "Next",
            paddingValues = PaddingValues()
        ) {

        }

        Spacer(modifier = Modifier.height(15.dp))

        DefaultFormButtonWithoutFill(
            "Skip",
            paddingValues = PaddingValues()
        ) {

        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun MedicationItemComposable(dataSet: MedicationModel) {

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
            MedicationTextGroupComposable(
                title = stringResource(R.string.medication_name),
                value = dataSet.medicationName
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(LightGreenColor)
                    .padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            MedicationTextGroupComposable(
                title = stringResource(R.string.medication_take_and_dosage),
                value = stringResource(R.string.medication_take_and_dosage_value, dataSet.medicationTake, dataSet.medicationDosage)
            )

            Spacer(modifier = Modifier.height(10.dp))

            MedicationTextGroupComposable(
                title = stringResource(R.string.medication_frequency),
                value = dataSet.medicationFrequency
            )

            Spacer(modifier = Modifier.height(10.dp))

            MedicationTextGroupComposable(
                title = stringResource(R.string.medication_type),
                value = dataSet.medicationType
            )
        }
    }
}

@Composable
fun MedicationTextGroupComposable(title: String, value: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = title,
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = value,
            fontFamily = SFPro,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }
}

fun getCurrentMedicationsList(): List<MedicationModel> {
    return listOf(
        MedicationModel(
            "Amlodipine",
            "Syrup",
            "After the meal",
            "Morning, Night",
            "10ml"
        ),
        MedicationModel(
            "Lisinopril",
            "Tablets",
            "After the meal",
            "Morning",
            "2 tablets"
        ),
        MedicationModel(
            "Metformin",
            "Tablets",
            "Before the meal",
            "Evening",
            "1 tablet"
        )
    )
}