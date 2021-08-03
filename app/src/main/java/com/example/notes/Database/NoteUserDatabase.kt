package com.example.notes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.Dao.NoteUserDao
import com.example.notes.Model.NoteUser

@Database(entities = [NoteUser::class],version = 1,exportSchema = false)
abstract class NoteUserDatabase : RoomDatabase() {

    abstract fun getNoteUserDao(): NoteUserDao

    companion object{
        private const val DATABASE_NAME = "NoteUserDatabase"

        @Volatile
        var noteUserDatabase: NoteUserDatabase?= null

        fun getNoteUserDatabase(context: Context): NoteUserDatabase?{
            if (noteUserDatabase == null)
            {
            synchronized(NoteUserDatabase::class.java){
                if(noteUserDatabase == null)
                {
                    noteUserDatabase = Room.databaseBuilder(context,NoteUserDatabase::class.java,
                        DATABASE_NAME).build()
                }
            }
            }
                return noteUserDatabase
        }
    }
}