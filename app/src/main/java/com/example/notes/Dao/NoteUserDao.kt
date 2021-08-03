package com.example.notes.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.Model.NoteUser

@Dao
interface NoteUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insert(noteUser:NoteUser)

     @Query("SELECT * FROM NoteUser ORDER BY id ASC")
     fun getNoteUserData(): LiveData<List<NoteUser>>
}