package com.busol.habittracker.core.database.habit.schedule

import com.busol.habittracker.core.database.RoutineTrackerDatabase
import com.busol.habittracker.core.database.di.toInt
import com.busol.habittracker.core.database.habit.WeekDaysMonthRelatedLocalDataSource
import com.busol.habittracker.core.database.habit.duedates.DueDateLocalDataSource
import com.busol.habittracker.core.database.model.schedule.ScheduleType
import com.busol.habittracker.core.database.model.schedule.toAlternateDaySchedule
import com.busol.habittracker.core.database.model.schedule.toAnnualScheduleByDueDates
import com.busol.habittracker.core.database.model.schedule.toAnnualScheduleByNumOfDueDays
import com.busol.habittracker.core.database.model.schedule.toCustomDateSchedule
import com.busol.habittracker.core.database.model.schedule.toEveryDaySchedule
import com.busol.habittracker.core.database.model.schedule.toMonthlyScheduleByDueDatesIndices
import com.busol.habittracker.core.database.model.schedule.toMonthlyScheduleByNumOfDueDays
import com.busol.habittracker.core.database.model.schedule.toScheduleEntity
import com.busol.habittracker.core.database.model.schedule.toWeeklyScheduleByDueDaysOfWeek
import com.busol.habittracker.core.database.model.schedule.toWeeklyScheduleByNumOfDueDays
import com.busol.habittracker.core.database.schedule.ScheduleEntity
import com.busol.habittracker.core.model.Schedule

internal class ScheduleLocalDataSourceImpl(
    private val db: RoutineTrackerDatabase,
    private val dueDateLocalDataSource: DueDateLocalDataSource,
    private val weekDaysMonthRelatedLocalDataSource: WeekDaysMonthRelatedLocalDataSource,
) : ScheduleLocalDataSource {
    override fun insertSchedule(schedule: Schedule) = db.scheduleEntityQueries.transaction {
        when (schedule) {
            is Schedule.EveryDaySchedule ->
                insertScheduleEntity(schedule.toScheduleEntity())

            is Schedule.WeeklyScheduleByDueDaysOfWeek -> {
                insertScheduleEntity(schedule.toScheduleEntity())
                val scheduleId = db.scheduleEntityQueries.lastInsertRowId().executeAsOne()
                dueDateLocalDataSource.insertDueDates(
                    dueDates = schedule.dueDaysOfWeek.map { it.toInt() },
                    scheduleId = scheduleId,
                )
            }

            is Schedule.WeeklyScheduleByNumOfDueDays ->
                insertScheduleEntity(schedule.toScheduleEntity())

            is Schedule.MonthlyScheduleByDueDatesIndices -> {
                insertScheduleEntity(schedule.toScheduleEntity())
                val scheduleId = db.scheduleEntityQueries.lastInsertRowId().executeAsOne()
                if (schedule.dueDatesIndices.isNotEmpty()) {
                    dueDateLocalDataSource.insertDueDates(
                        scheduleId = scheduleId,
                        dueDates = schedule.dueDatesIndices,
                    )
                }
                if (schedule.weekDaysMonthRelated.isNotEmpty()) {
                    weekDaysMonthRelatedLocalDataSource.insertWeekDaysMonthRelated(
                        scheduleId = scheduleId,
                        weekDaysMonthRelated = schedule.weekDaysMonthRelated,
                    )
                }
            }

            is Schedule.MonthlyScheduleByNumOfDueDays ->
                insertScheduleEntity(schedule.toScheduleEntity())

            is Schedule.AlternateDaysSchedule ->
                insertScheduleEntity(schedule.toScheduleEntity())

            is Schedule.CustomDateSchedule -> {
                insertScheduleEntity(schedule.toScheduleEntity())
                val scheduleId = db.scheduleEntityQueries.lastInsertRowId().executeAsOne()
                dueDateLocalDataSource.insertDueDates(
                    dueDates = schedule.dueDates.map { it.toInt() },
                    scheduleId = scheduleId,
                )
            }

            is Schedule.AnnualScheduleByDueDates -> {
                insertScheduleEntity(schedule.toScheduleEntity())
                val scheduleId = db.scheduleEntityQueries.lastInsertRowId().executeAsOne()
                dueDateLocalDataSource.insertDueDates(
                    dueDates = schedule.dueDates.map { it.toInt() },
                    scheduleId = scheduleId,
                )
            }

            is Schedule.AnnualScheduleByNumOfDueDays ->
                insertScheduleEntity(schedule.toScheduleEntity())
        }
    }

    override fun getScheduleById(habitId: Long): Schedule = db.scheduleEntityQueries.transactionWithResult {
        val scheduleEntity = db.scheduleEntityQueries.getScheduleById(habitId).executeAsOne()
        when (scheduleEntity.type) {
            ScheduleType.EveryDaySchedule -> scheduleEntity.toEveryDaySchedule()

            ScheduleType.WeeklyScheduleByNumOfDueDays ->
                scheduleEntity.toWeeklyScheduleByNumOfDueDays()

            ScheduleType.WeeklyScheduleByDueDaysOfWeek ->
                scheduleEntity.toWeeklyScheduleByDueDaysOfWeek(
                    dueDates = dueDateLocalDataSource.getDueDates(habitId),
                )

            ScheduleType.MonthlyScheduleByNumOfDueDays ->
                scheduleEntity.toMonthlyScheduleByNumOfDueDays()

            ScheduleType.MonthlyScheduleByDueDatesIndices ->
                scheduleEntity.toMonthlyScheduleByDueDatesIndices(
                    dueDatesIndices = dueDateLocalDataSource.getDueDates(habitId),
                    weekDaysMonthRelated = weekDaysMonthRelatedLocalDataSource.getWeekDayMonthRelatedDays(habitId),
                )

            ScheduleType.AnnualScheduleByNumOfDueDays ->
                scheduleEntity.toAnnualScheduleByNumOfDueDays()

            ScheduleType.AnnualScheduleByDueDates ->
                scheduleEntity.toAnnualScheduleByDueDates(
                    dueDates = dueDateLocalDataSource.getDueDates(habitId),
                )

            ScheduleType.AlternateDaysSchedule -> scheduleEntity.toAlternateDaySchedule()

            ScheduleType.CustomDateSchedule -> scheduleEntity.toCustomDateSchedule(
                dueDatesIndices = dueDateLocalDataSource.getDueDates(habitId),
            )
        }
    }

    override fun deleteSchedule(scheduleId: Long) {
        db.scheduleEntityQueries.deleteSchedule(scheduleId)
        dueDateLocalDataSource.deleteDueDates(scheduleId)
        weekDaysMonthRelatedLocalDataSource.deleteWeekDayMonthRelatedDays(scheduleId)
    }

    @Suppress("ktlint:standard:max-line-length")
    private fun insertScheduleEntity(schedule: ScheduleEntity) {
        db.scheduleEntityQueries.insertSchedule(
            id = null,
            type = schedule.type,
            startDate = schedule.startDate,
            endDate = schedule.endDate,
            backlogEnabled = schedule.backlogEnabled,
            cancelDuenessIfDoneAhead = schedule.cancelDuenessIfDoneAhead,
            startDayOfWeekInWeeklySchedule = schedule.startDayOfWeekInWeeklySchedule,
            startFromHabitStartInMonthlyAndAnnualSchedule = schedule.startFromHabitStartInMonthlyAndAnnualSchedule,
            includeLastDayOfMonthInMonthlySchedule = schedule.includeLastDayOfMonthInMonthlySchedule,
            periodicSeparationEnabledInPeriodicSchedule = schedule.periodicSeparationEnabledInPeriodicSchedule,
            numOfDueDaysInByNumOfDueDaysSchedule = schedule.numOfDueDaysInByNumOfDueDaysSchedule,
            numOfDaysInAlternateDaysSchedule = schedule.numOfDaysInAlternateDaysSchedule,
            numOfDueDaysInFirstPeriodInByNumOfDueDaysSchedule = schedule.numOfDueDaysInFirstPeriodInByNumOfDueDaysSchedule,
        )
    }
}