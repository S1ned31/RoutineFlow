package com.busol.habittracker.core.database.di

import com.busol.habittracker.core.database.habit.HabitLocalDataSource
import com.busol.habittracker.core.database.habit.HabitLocalDataSourceImpl
import com.busol.habittracker.core.database.habit.WeekDaysMonthRelatedLocalDataSource
import com.busol.habittracker.core.database.habit.WeekDaysMonthRelatedLocalDataSourceImpl
import com.busol.habittracker.core.database.habit.duedates.DueDateLocalDataSource
import com.busol.habittracker.core.database.habit.duedates.DueDateLocalDataSourceImpl
import com.busol.habittracker.core.database.habit.schedule.ScheduleLocalDataSource
import com.busol.habittracker.core.database.habit.schedule.ScheduleLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val habitLocalDataModule = module {
    single<DueDateLocalDataSource> {
        DueDateLocalDataSourceImpl(
            db = get(),
        )
    }

    single<WeekDaysMonthRelatedLocalDataSource> {
        WeekDaysMonthRelatedLocalDataSourceImpl(
            db = get(),
        )
    }

    single<ScheduleLocalDataSource> {
        ScheduleLocalDataSourceImpl(
            db = get(),
            dueDateLocalDataSource = get(),
            weekDaysMonthRelatedLocalDataSource = get(),
        )
    }

    single<HabitLocalDataSource> {
        HabitLocalDataSourceImpl(
            db = get(),
            scheduleLocalDataSource = get(),
            ioDispatcher = get(qualifier = named("ioDispatcher")),
        )
    }
}