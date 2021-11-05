package com.zannardyapps.roommvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zannardyapps.roommvvm.database.daos.NotesDao
import com.zannardyapps.roommvvm.database.model.Notes

@Database(entities = [Notes::class], version = 2, exportSchema = false)
abstract class NotesRoomDatabase: RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {

        private const val DATABASE_NAME: String = "notes_database"

        @Volatile
        private var INSTANCE: NotesRoomDatabase? = null

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NotesRoomDatabase::class.java,
                DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context):NotesRoomDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
            }


    }

}