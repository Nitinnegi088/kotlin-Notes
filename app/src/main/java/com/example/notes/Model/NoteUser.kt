package com.example.notes.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteUser")
data class NoteUser(val name:String,val age: Int) {

    @PrimaryKey(autoGenerate = true)
    val id:Int?=null
}