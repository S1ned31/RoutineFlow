package com.busol.habittracker.core.domain.streak

import com.busol.habittracker.core.data.completionhistory.CompletionHistoryRepository
import com.busol.habittracker.core.data.vacation.VacationRepository
import com.busol.habittracker.core.domain.schedule.expandPeriodToScheduleBounds
import com.busol.habittracker.core.domain.streak.computer.StreakComputer
import com.busol.habittracker.core.logic.time.LocalDateRange
import com.busol.habittracker.core.model.Habit
import com.busol.habittracker.core.model.Streak
import kotlinx.datetime.LocalDate

class GetStreaksInPeriodUseCase(
    completionHistoryRepository: CompletionHistoryRepository,
    vacationRepository: VacationRepository,
    streakComputer: StreakComputer,
) : StreakManager(
    completionHistoryRepository,
    vacationRepository,
    streakComputer,
) {
    suspend operator fun invoke(
        habit: Habit,
        period: LocalDateRange,
        today: LocalDate,
    ): List<Streak> {
        val expandedPeriod = period.expandPeriodToScheduleBounds(
            schedule = habit.schedule,
            getPeriodRange = { date -> getPeriodRange(habit.schedule, date) },
        )
        return computeStreaks(habit, expandedPeriod, today)
    }
}