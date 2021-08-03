package com.example.notes.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notes.Model.NoteUser
import com.example.notes.Repository.NoteUserRepository


class NoteUserViewModel : ViewModel() {

    fun insert(context: Context,noteUser: NoteUser){
        return NoteUserRepository.insert(context,noteUser)
    }

    fun getNoteUserData(context: Context): LiveData<List<NoteUser>>? {
        return NoteUserRepository.getNoteUserData(context)
    }
}