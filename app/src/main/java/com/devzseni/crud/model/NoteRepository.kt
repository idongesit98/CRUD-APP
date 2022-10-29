package com.devzseni.crud.model

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> = notesDao.getNotes()

    fun getLowNotes():LiveData<List<Notes>> = notesDao.getLowNotes()

    fun getHighNotes():LiveData<List<Notes>> = notesDao.getHighNotes()

    fun getMediumNotes():LiveData<List<Notes>> = notesDao.getMediumNotes()

    suspend fun insertNotes(notes: Notes){
        notesDao.insert(notes)
    }

    fun deleteNotes(notes: Notes){
        notesDao.deleteNotes(notes)
    }

    suspend fun updateNotes(notes: Notes){
        notesDao.updateNotes(notes)
    }
}