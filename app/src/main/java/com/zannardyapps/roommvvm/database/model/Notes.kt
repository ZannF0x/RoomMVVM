package com.zannardyapps.roommvvm.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(

    @PrimaryKey @ColumnInfo(name = "notes_id")
    val notesId: String,

    @ColumnInfo(name = "notes_title")
    val notesTitle: String,

    @ColumnInfo(name = "notes_description")
    val notesDescription: String

    )
