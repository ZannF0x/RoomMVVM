package com.zannardyapps.roommvvm.repository

import androidx.annotation.WorkerThread
import com.zannardyapps.roommvvm.database.daos.NotesDao
import com.zannardyapps.roommvvm.database.model.Notes
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDao: NotesDao) {

    val allNotes: Flow<List<Notes>> = notesDao.getNotes()

    @Suppress("RedundantSuspendModifier")
   // @WorkerThread
    suspend fun insert(notes: Notes){
        notesDao.insert(notes)
    }

    @Suppress("RedundantSuspendModifier")
    suspend fun delete(notes: Notes){
        notesDao.delete(notes)
    }
}