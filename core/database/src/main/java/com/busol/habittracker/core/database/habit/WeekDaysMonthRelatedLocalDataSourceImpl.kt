package com.busol.habittracker.core.database.habit

import com.busol.habittracker.core.database.RoutineTrackerDatabase
import com.busol.habittracker.core.database.di.toDayOfWeek
import com.busol.habittracker.core.database.di.toInt
import com.busol.habittracker.core.model.WeekDayMonthRelated

internal class WeekDaysMonthRelatedLocalDataSourceImpl(
    private val db: RoutineTrackerDatabase,
) : WeekDaysMonthRelatedLocalDataSource {
    override fun insertWeekDaysMonthRelated(
        scheduleId: Long,
        weekDaysMonthRelated: List<WeekDayMonthRelated>,
    ) = db.weekDayMonthRelatedEntityQueries.transaction {
        for (weekDayMonthRelated in weekDaysMonthRelated) {
            db.weekDayMonthRelatedEntityQueries.insertWeekDayMonthRelatedEntry(
                id = null,
                scheduleId = scheduleId,
                weekDayIndex = weekDayMonthRelated.dayOfWeek.toInt(),
                weekDayNumberMonthRelated = weekDayMonthRelated.weekDayNumberMonthRelated,
            )
        }
    }

    override fun getWeekDayMonthRelatedDays(scheduleId: Long): List<WeekDayMonthRelated> =
        db.weekDayMonthRelatedEntityQueries
            .getWeekDayMonthRelatedDays(scheduleId)
            .executeAsList()
            .map { entity ->
                WeekDayMonthRelated(
                    dayOfWeek = entity.weekDayIndex.toDayOfWeek(),
                    weekDayNumberMonthRelated = entity.weekDayNumberMonthRelated,
                )
            }

    override fun deleteWeekDayMonthRelatedDays(scheduleId: Long) {
        db.weekDayMonthRelatedEntityQueries.deleteWeekDayMonthRelatedDays(scheduleId)
    }
}