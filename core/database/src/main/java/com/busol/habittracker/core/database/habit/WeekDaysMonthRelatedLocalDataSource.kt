package com.busol.habittracker.core.database.habit

import com.busol.habittracker.core.model.WeekDayMonthRelated

interface WeekDaysMonthRelatedLocalDataSource {
    fun insertWeekDaysMonthRelated(
        scheduleId: Long,
        weekDaysMonthRelated: List<WeekDayMonthRelated>,
    )
    fun getWeekDayMonthRelatedDays(scheduleId: Long): List<WeekDayMonthRelated>
    fun deleteWeekDayMonthRelatedDays(scheduleId: Long)
}