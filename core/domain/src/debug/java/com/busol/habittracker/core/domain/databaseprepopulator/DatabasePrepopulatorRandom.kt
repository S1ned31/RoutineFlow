package com.busol.habittracker.core.domain.databaseprepopulator

import com.busol.habittracker.core.data.streaks.StreakRepository
import com.busol.habittracker.core.database.completionhistory.CompletionHistoryLocalDataSource
import com.busol.habittracker.core.database.habit.HabitLocalDataSource
import com.busol.habittracker.core.database.vacation.VacationLocalDataSource
import com.busol.habittracker.core.domain.RandomHabitsGenerator
import com.busol.habittracker.core.domain.streak.computer.StreakComputer
import com.busol.habittracker.core.logic.time.today
import com.busol.habittracker.core.model.Habit
import com.busol.habittracker.core.model.Streak
import com.busol.habittracker.core.model.Vacation

class DatabasePrepopulatorRandom(
    private val habitLocalDataSource: HabitLocalDataSource,
    private val completionHistoryLocalDataSource: CompletionHistoryLocalDataSource,
    private val vacationLocalDataSource: VacationLocalDataSource,
    private val streakComputer: StreakComputer,
    private val streakRepository: StreakRepository,
) : DatabasePrepopulator {
    override suspend fun prepopulateDatabase(numOfHabits: Int) {
        val dbIsNotEmpty = !habitLocalDataSource.checkIfIsEmpty()
        if (dbIsNotEmpty) return

        val habits = RandomHabitsGenerator.generateRandomHabits(numOfHabits)
        habitLocalDataSource.insertHabits(habits)

        val insertedHabits = habitLocalDataSource.getAllHabits()

        val completionHistory: Map<Long, List<Habit.CompletionRecord>> = insertedHabits
            .associateWith { habit ->
                RandomHabitsGenerator.generateCompletionHistory(habit)
            }
            .mapKeys { it.key.id!! }
        completionHistoryLocalDataSource.insertCompletions(completionHistory)

        val vacationHistory: Map<Long, List<Vacation>> = insertedHabits
            .associateWith { habit ->
                RandomHabitsGenerator.generateVacationHistory(habit)
            }
            .mapKeys { it.key.id!! }
        vacationLocalDataSource.insertVacations(vacationHistory)

        val streaksMap: Map<Long, List<Streak>> = insertedHabits.associateBy(
            keySelector = { it.id!! },
            valueTransform = { habit ->
                val habitId = habit.id!!
                streakComputer.computeStreaks(
                    habit = habit,
                    completionHistory = completionHistory[habitId]!!,
                    vacationHistory = vacationHistory[habitId]!!,
                    today = today,
                )
            },
        )
        streakRepository.insertStreaks(streaksMap)
    }
}