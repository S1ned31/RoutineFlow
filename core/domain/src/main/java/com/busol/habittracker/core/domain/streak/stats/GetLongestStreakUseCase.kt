package com.busol.habittracker.core.domain.streak.stats

import com.busol.habittracker.core.data.completionhistory.CompletionHistoryRepository
import com.busol.habittracker.core.data.streaks.StreakRepository
import com.busol.habittracker.core.data.vacation.VacationRepository
import com.busol.habittracker.core.domain.streak.StreakManager
import com.busol.habittracker.core.domain.streak.computer.StreakComputer
import com.busol.habittracker.core.logic.getDurationInDays
import com.busol.habittracker.core.logic.joinAdjacentStreaks
import com.busol.habittracker.core.model.Habit
import com.busol.habittracker.core.model.Streak
import kotlinx.datetime.LocalDate

class GetLongestStreakUseCase(
    private val streakRepository: StreakRepository,
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
        today: LocalDate,
    ): Streak? {
        formStreaks(habit = habit, today = today, completion = null)?.let { (period, streaks) ->
            streakRepository.upsertStreaks(habit.id!!, period, streaks)
        }
        val currentPeriod = getPeriodRange(habit.schedule, today)
        val notCachedStreaks = computeStreaks(
            habit = habit,
            period = currentPeriod,
            today = today,
            completion = null,
        )
        val cachedStreaks = streakRepository.getLongestStreaks(habit.id!!)
        return (cachedStreaks + notCachedStreaks)
            .joinAdjacentStreaks()
            .maxByOrNull { it.getDurationInDays() }
    }
}