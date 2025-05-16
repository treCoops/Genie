package com.advagroup.genie.views.screens.mainScreens.profileScreen.healthCondition

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.advagroup.genie.dataModels.uiData.ProfileHealthConditionModel
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.ExtraLightGreenColor
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.LightPrimary
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.ui.theme.SFPro
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFillWithLeadingIcon
import com.advagroup.genie.views.reusableComposables.buttons.DefaultNavigationCircleButton

@Composable
fun ProfileHealthConditionScreen(navController: NavController) {

    var scrollScope = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollScope)
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = stringResource(R.string.health_conditions),
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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(getConditionList().size) { index ->
                HealthConditionItemComposable(getConditionList()[index])
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        DefaultFormButtonWithoutFillWithLeadingIcon(
            title = stringResource(R.string.add_condition),
            height = 60.dp,
            iconVector = Icons.Filled.Add,
            paddingValues = PaddingValues()
        ) {

        }

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

                    DetailFieldComposable(stringResource(R.string.note), value = "The patient presents with a medical " +
                            "condition requiring ongoing monitoring and treatment. Symptoms have been assessed, and appropriate " +
                            "interventions have been initiated. Follow-up care and adherence to the prescribed treatment plan are" +
                            " essential to support recovery and manage the condition effectively.")

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

@Composable
private fun HealthConditionItemComposable(dataSet: ProfileHealthConditionModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = ExtraLightGreenColor,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(
            width = 1.dp,
            color = LightGreenColor
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = dataSet.conditionName,
                fontFamily = SFPro,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Icon(
                imageVector = Icons.Outlined.Delete,
                modifier = Modifier
                    .size(25.dp),
                contentDescription = "Delete Icon",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }

    }
}

private fun getConditionList() = listOf(
    ProfileHealthConditionModel(
        1,
        "Hypertension"
    ),
    ProfileHealthConditionModel(
        2,
        "Diabetes"
    ),
    ProfileHealthConditionModel(
        3,
        "Arthritis"
    ),
    ProfileHealthConditionModel(
        4,
        "Osteoporosis"
    ),
    ProfileHealthConditionModel(
        5,
        "Cancer"
    )
)