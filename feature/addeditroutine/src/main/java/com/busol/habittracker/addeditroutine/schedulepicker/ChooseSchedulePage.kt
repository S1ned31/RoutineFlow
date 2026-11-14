package com.busol.habittracker.addeditroutine.schedulepicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busol.habittracker.addeditroutine.AddHabitDestinationTopAppBar
import com.busol.habittracker.addeditroutine.navigation.AddRoutineDestination
import com.busol.habittracker.addeditroutine.schedulepicker.components.AlternateDaysSchedulePicker
import com.busol.habittracker.addeditroutine.schedulepicker.components.EveryDaySchedulePicker
import com.busol.habittracker.addeditroutine.schedulepicker.components.MonthlySchedulePicker
import com.busol.habittracker.addeditroutine.schedulepicker.components.ScheduleTypeUi
import com.busol.habittracker.addeditroutine.schedulepicker.components.WeeklySchedulePicker

@Composable
fun ChooseSchedulePage(
    modifier: Modifier = Modifier,
    chooseSchedulePageState: ChooseSchedulePageState,
) {
    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        AddHabitDestinationTopAppBar(destination = AddRoutineDestination.ChooseSchedule)

        EveryDaySchedulePicker(
            everyDaySchedulePickerState = chooseSchedulePageState.everyDaySchedulePickerState,
            selectSchedule = {
                chooseSchedulePageState.selectSchedule(ScheduleTypeUi.EveryDaySchedule)
            },
        )
        WeeklySchedulePicker(
            weeklySchedulePickerState = chooseSchedulePageState.weeklySchedulePickerState,
            selectSchedule = {
                chooseSchedulePageState.selectSchedule(ScheduleTypeUi.WeeklySchedule)
            },
        )
        MonthlySchedulePicker(
            monthlySchedulePickerState = chooseSchedulePageState.monthlySchedulePickerState,
            selectSchedule = {
                chooseSchedulePageState.selectSchedule(ScheduleTypeUi.MonthlySchedule)
            },
        )
        AlternateDaysSchedulePicker(
            alternateDaysSchedulePickerState = chooseSchedulePageState.alternateDaysSchedulePickerState,
            selectSchedule = {
                chooseSchedulePageState.selectSchedule(ScheduleTypeUi.AlternateDaysSchedule)
            },
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ChooseSchedulePagePreview() {
    ChooseSchedulePage(
        chooseSchedulePageState = rememberChooseSchedulePageState(),
    )
}