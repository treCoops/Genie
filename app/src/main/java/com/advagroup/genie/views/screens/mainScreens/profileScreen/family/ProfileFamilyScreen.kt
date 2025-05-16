package com.advagroup.genie.views.screens.mainScreens.profileScreen.family

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
import com.advagroup.genie.dataModels.uiData.FamilyModel
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.LightPrimary
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.views.reusableComposables.buttons.DefaultFormButtonWithoutFillWithLeadingIcon
import com.advagroup.genie.views.reusableComposables.buttons.DefaultNavigationCircleButton
import com.advagroup.genie.views.reusableComposables.textGroups.ListItemTextGroupComposable


@Composable
fun ProfileFamilyScreen(navController: NavController) {

    var scrollScope = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollScope)
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = stringResource(R.string.medication),
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
                items(getCurrentFamilyList().size) { index ->
                    FamilyItemComposable(
                        getCurrentFamilyList()[index],
                        onPressed = {},
                        onDeleted = {}
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        DefaultFormButtonWithoutFillWithLeadingIcon(
            title = stringResource(R.string.add_member),
            height = 60.dp,
            iconVector = Icons.Filled.Add,
            paddingValues = PaddingValues()
        ) {

        }

        Spacer(modifier = Modifier.height(15.dp))

    }

}

@Composable
private fun FamilyItemComposable(dataSet: FamilyModel, onPressed: () -> Unit, onDeleted: () -> Unit) {

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
                    value = stringResource(R.string.member_name_and_relationship, dataSet.name, dataSet.relationship)
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

            ListItemTextGroupComposable(
                title = stringResource(R.string.email),
                value = dataSet.email
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                ListItemTextGroupComposable(
                    title = stringResource(R.string.phone_number),
                    value = dataSet.phoneNo
                )

                IconButton(
                    onClick = onDeleted,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        "Delete Icon",
                        modifier = Modifier.size(30.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

}

fun getCurrentFamilyList(): List<FamilyModel> {
    return listOf(
        FamilyModel(
            "Hishara",
            "Grandparent",
            "hishara@gmail.com",
            "+61233423431"
        ),
        FamilyModel(
            "Tharindu",
            "Brother",
            "tharindu@gmail.com",
            "+61237657689"
        ),
        FamilyModel(
            "Kalani",
            "Sister",
            "kalani@gmail.com",
            "+94768764562"
        )
    )
}