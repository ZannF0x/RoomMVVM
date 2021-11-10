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

    //@Update(entity = Notes::class) notes: Notes
    @Query("UPDATE notes_table SET notes_title = :title, notes_description = :description WHERE notes_id =:id")
    suspend fun update(title: String, description: String, id: Int)

    //@Query("DELETE FROM yourDatabaseTable WHERE id = :id")
    @Delete
    fun delete(notes: Notes)

}