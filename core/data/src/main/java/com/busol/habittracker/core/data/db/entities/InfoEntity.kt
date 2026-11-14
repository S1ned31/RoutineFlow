package com.busol.habittracker.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("info")
data class InfoEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("image")
    val image: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("text")
    val text: String,
    @ColumnInfo("readingTime")
    val readingTime: String,
)

