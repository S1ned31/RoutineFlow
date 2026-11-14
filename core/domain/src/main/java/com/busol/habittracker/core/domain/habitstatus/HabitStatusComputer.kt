package com.busol.habittracker.core.domain.habitstatus

import com.busol.habittracker.core.model.Habit
import com.busol.habittracker.core.model.HabitStatus
import com.busol.habittracker.core.model.Vacation
import kotlinx.datetime.LocalDate

interface HabitStatusComputer {
    fun computeStatus(
        validationDate: LocalDate,
        today: LocalDate,
        habit: Habit,
        completionHistory: List<Habit.CompletionRecord>,
        vacationHistory: List<Vacation>,
    ): HabitStatus
}