package com.busol.habittracker.core.data.db.dao

import com.busol.habittracker.core.data.db.entities.InfoEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data Access Object (DAO) for accessing [InfoEntity] data in the database.
 *
 * This interface defines the methods for querying, inserting, and deleting records
 * from the `info` table in the database.
 */
@Dao
interface InfoDao {
    /**
     * Retrieves an [InfoEntity] from the database by its unique identifier.
     *
     * @param id The unique identifier of the info entry.
     * @return The [InfoEntity] corresponding to the specified ID.
     */
    @Query("SELECT * FROM info WHERE id=:id")
    fun getInfoById(id: Long): InfoEntity

    /**
     * Retrieves a list of all [InfoEntity] entries from the database.
     * @return A list of all [InfoEntity] records in the `info` table.
     */
    @Query("SELECT * FROM INFO")
    fun getInfoList(): List<InfoEntity>

    /**
     * Inserts or replaces an [InfoEntity] in the database.
     * If an entry with the same ID already exists, it will be replaced.
     * @param entity The [InfoEntity] to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfo(entity: InfoEntity)

    /**
     * Deletes all [InfoEntity] entries from the database.
     */
    @Query("DELETE FROM info")
    fun deleteAll()
}