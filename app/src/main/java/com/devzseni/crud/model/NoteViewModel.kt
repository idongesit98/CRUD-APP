package com.devzseni.crud.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val getAllNotes: LiveData<List<Notes>>
    val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        getAllNotes = repository.getAllNotes()
    }

    fun addNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNotes(notes)
    }

    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()

    fun getHighNotes():LiveData<List<Notes>> = repository.getHighNotes()

    fun getMediumNotes():LiveData<List<Notes>> = repository.getMediumNotes()

    fun deleteNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNotes(notes)
    }


    fun updateNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNotes(notes)
    }
}