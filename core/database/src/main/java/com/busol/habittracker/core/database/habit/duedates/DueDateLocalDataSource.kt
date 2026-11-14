package com.busol.habittracker.core.database.habit.duedates

interface DueDateLocalDataSource {
    fun insertDueDates(
        dueDates: List<Int>,
        scheduleId: Long,
    )
    fun getDueDates(scheduleId: Long): List<Int>
    fun deleteDueDates(scheduleId: Long)
}