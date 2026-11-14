package com.busol.habittracker.core.data.db

import com.busol.habittracker.core.data.db.dao.InfoDao
import com.busol.habittracker.core.data.db.entities.InfoEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        InfoEntity::class,
    ],
    version = 13,
)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun infoDao() : InfoDao
}