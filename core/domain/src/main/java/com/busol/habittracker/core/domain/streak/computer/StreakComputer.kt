package com.busol.habittracker.core.domain.streak.computer

import com.busol.habittracker.core.model.Habit
import com.busol.habittracker.core.model.Streak
import com.busol.habittracker.core.model.Vacation
import kotlinx.datetime.LocalDate

interface StreakComputer {
    fun computeStreaks(
        today: LocalDate,
        habit: Habit,
        completionHistory: List<Habit.CompletionRecord>,
        vacationHistory: List<Vacation>,
    ): List<Streak>
}