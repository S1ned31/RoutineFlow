package com.busol.habittracker.core.data.repository

import com.busol.habittracker.core.data.db.entities.InfoEntity

interface InfoRepository {
    fun getInfoList() : List<InfoEntity>
    fun getInfoPost(id: Long) : InfoEntity
    fun deleteAll()
    fun insert(infoPost: InfoEntity)
    suspend fun getInfoFromRemote() : List<InfoEntity>
}