package com.busol.habittracker.core.testcommon.fakes.habit

import com.busol.habittracker.core.model.Habit
import com.busol.habittracker.core.model.Streak
import com.busol.habittracker.core.model.Vacation
import kotlinx.coroutines.flow.MutableStateFlow

class HabitData {
    var listOfHabits = MutableStateFlow(emptyList<Habit>())
    val completionHistory = MutableStateFlow(emptyList<Pair<Long, Habit.CompletionRecord>>())
    val vacationHistory = MutableStateFlow(emptyList<Pair<Long, Vacation>>())
    val streaks = MutableStateFlow(emptyList<Pair<Long, Streak>>())
}