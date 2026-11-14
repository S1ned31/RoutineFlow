package com.busol.habittracker.core.database.model

import com.busol.habittracker.core.database.CompletionHistoryEntity
import com.busol.habittracker.core.model.Habit

fun CompletionHistoryEntity.toExternalModel(habit: Habit): Habit.CompletionRecord = when (habit) {
    is Habit.YesNoHabit -> Habit.YesNoHabit.CompletionRecord(
        date = date,
        numOfTimesCompleted = numOfTimesCompleted,
    )
}