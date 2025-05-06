package com.advagroup.genie.views.reusableComposables.pickers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.SFPro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(timePickerState: TimePickerState, onTimeSelected: (Int, Int) -> Unit, onDismiss: () -> Unit) {

    BasicAlertDialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            tonalElevation = 6.dp,
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(20.dp)
        ) {

            Box(
                modifier = Modifier
                    .wrapContentHeight()
            ) {

                Text(
                    text = "Select time",
                    fontFamily = SFPro,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 20.dp, top = 15.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(50.dp))

                    TimePicker(
                        state = timePickerState,
                        colors = TimePickerDefaults.colors(
                            clockDialColor = EditTextBackgroundColor,
                            timeSelectorSelectedContainerColor = LightGreenColor,
                            timeSelectorSelectedContentColor = MaterialTheme.colorScheme.onBackground,
                            timeSelectorUnselectedContainerColor = EditTextBackgroundColor,
                            timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onBackground,
                            clockDialUnselectedContentColor = MaterialTheme.colorScheme.onBackground,
                            periodSelectorSelectedContentColor = MaterialTheme.colorScheme.onBackground,
                            periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onBackground,
                            periodSelectorUnselectedContainerColor = EditTextBackgroundColor,
                            periodSelectorSelectedContainerColor = LightGreenColor,
                            periodSelectorBorderColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = {
                            onTimeSelected(timePickerState.hour, timePickerState.minute)
                        }) {
                            Text(
                                text = "OK",
                                color = LightGreenColor,
                                fontFamily = SFPro,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 17.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = "Cancel",
                                color = LightGreenColor,
                                fontFamily = SFPro,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 17.sp
                            )
                        }
                    }
                }

            }
        }
    }

}