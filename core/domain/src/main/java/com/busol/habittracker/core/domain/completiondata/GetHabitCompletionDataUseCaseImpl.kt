package com.busol.habittracker.core.domain.completiondata

import com.busol.habittracker.core.data.completionhistory.CompletionHistoryRepository
import com.busol.habittracker.core.data.vacation.VacationRepository
import com.busol.habittracker.core.domain.di.GetHabitUseCase
import com.busol.habittracker.core.domain.habitstatus.HabitStatusComputer
import com.busol.habittracker.core.domain.schedule.expandPeriodToScheduleBounds
import com.busol.habittracker.core.domain.schedule.getPeriodRange
import com.busol.habittracker.core.logic.time.LocalDateRange
import com.busol.habittracker.core.logic.time.rangeTo
import com.busol.habittracker.core.model.Habit
import com.busol.habittracker.core.model.HabitCompletionData
import com.busol.habittracker.core.model.Schedule
import com.busol.habittracker.core.model.Vacation
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

class GetHabitCompletionDataUseCaseImpl(
    private val getHabit: GetHabitUseCase,
    private val vacationRepository: VacationRepository,
    private val completionHistoryRepository: CompletionHistoryRepository,
    private val habitStatusComputer: HabitStatusComputer,
    private val defaultDispatcher: CoroutineContext,
) : GetHabitCompletionDataUseCase {
    override suspend operator fun invoke(
        habitId: Long,
        validationDate: LocalDate,
        today: LocalDate,
    ): HabitCompletionData = invoke(
        habitId = habitId,
        validationDates = validationDate..validationDate,
        today = today,
    ).values.first()

    override suspend operator fun invoke(
        habitId: Long,
        validationDates: LocalDateRange,
        today: LocalDate,
    ): Map<LocalDate, HabitCompletionData> = withContext(defaultDispatcher) {
        val habit = getHabit(habitId)

        val completionHistory: List<Habit.CompletionRecord>
        val vacationHistory: List<Vacation>
        val period = when (val schedule = habit.schedule) {
            is Schedule.PeriodicSchedule -> validationDates.expandPeriodToScheduleBounds(
                schedule = schedule,
                getPeriodRange = { date -> schedule.getPeriodRange(date) },
            )

            is Schedule.NonPeriodicSchedule -> validationDates
        }

        completionHistory = completionHistoryRepository.getRecordsInPeriod(habit, period)
        vacationHistory = vacationRepository.getVacationsInPeriod(habitId, period)

        validationDates.associateWith { date ->
            val habitStatus = habitStatusComputer.computeStatus(
                validationDate = date,
                today = today,
                habit = habit,
                completionHistory = completionHistory,
                vacationHistory = vacationHistory,
            )
            val numOfTimesCompleted =
                completionHistory.find { it.date == date }?.numOfTimesCompleted ?: 0f
            HabitCompletionData(
                habitStatus = habitStatus,
                numOfTimesCompleted = numOfTimesCompleted,
            )
        }
    }
}