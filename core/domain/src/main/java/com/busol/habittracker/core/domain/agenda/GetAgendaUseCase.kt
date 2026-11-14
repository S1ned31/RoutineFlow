package com.busol.habittracker.core.domain.agenda

import com.busol.habittracker.core.model.Habit
import com.busol.habittracker.core.model.HabitCompletionData
import kotlinx.datetime.LocalDate

interface GetAgendaUseCase {
    suspend operator fun invoke(
        validationDate: LocalDate,
        today: LocalDate,
    ): Map<Habit, HabitCompletionData>
}