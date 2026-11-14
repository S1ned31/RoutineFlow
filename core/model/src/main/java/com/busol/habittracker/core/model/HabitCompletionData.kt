package com.busol.habittracker.core.model

data class HabitCompletionData(
    val habitStatus: HabitStatus,
    val numOfTimesCompleted: Float,
)