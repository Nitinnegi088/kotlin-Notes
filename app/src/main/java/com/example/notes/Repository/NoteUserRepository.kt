package com.example.notes.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notes.Database.NoteUserDatabase
import com.example.notes.Model.NoteUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NoteUserRepository {

    companion object {

         var noteUserDatabase:NoteUserDatabase? = null

        fun initialiseDB(context: Context):NoteUserDatabase?{
            return NoteUserDatabase.getNoteUserDatabase(context)
        }

        fun insert(context: Context,noteUser: NoteUser) {
            noteUserDatabase = initialiseDB(context)
                CoroutineScope(IO).launch {
                noteUserDatabase?.getNoteUserDao()?.insert(noteUser)
            }
        }

        fun getNoteUserData(context: Context): LiveData<List<NoteUser>>? {
            noteUserDatabase = initialiseDB(context)
            return noteUserDatabase?.getNoteUserDao()?.getNoteUserData()
        }
    }
}