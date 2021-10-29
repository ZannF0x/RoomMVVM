package com.zannardyapps.roommvvm.application

import android.app.Application
import com.zannardyapps.roommvvm.database.NotesRoomDatabase
import com.zannardyapps.roommvvm.repository.NotesRepository

class NotesApplication: Application() {

    private val database by lazy {NotesRoomDatabase.getInstance(this)}
    val repository by lazy {NotesRepository(database.notesDao())}

}