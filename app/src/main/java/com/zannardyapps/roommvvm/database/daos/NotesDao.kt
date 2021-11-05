package com.zannardyapps.roommvvm.database.daos

import androidx.room.*
import com.zannardyapps.roommvvm.database.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table ORDER BY notes_id DESC")
    fun getNotes(): Flow<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()

    //@Query("DELETE FROM yourDatabaseTable WHERE id = :id")
    @Delete
    fun deleted(notes: Notes)

}