package com.busol.habittracker.core.database.habit.schedule

import com.busol.habittracker.core.model.Schedule

interface ScheduleLocalDataSource {
    fun insertSchedule(schedule: Schedule)
    fun getScheduleById(habitId: Long): Schedule
    fun deleteSchedule(scheduleId: Long)
}