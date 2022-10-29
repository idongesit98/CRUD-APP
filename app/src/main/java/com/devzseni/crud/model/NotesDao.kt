package com.devzseni.crud.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Query("Select * from notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("Select * FROM Notes WHERE Priority = 3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("Select * FROM Notes WHERE Priority = 2")
    fun getMediumNotes():LiveData<List<Notes>>

    @Query("Select * FROM Notes WHERE Priority = 1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Delete
    fun deleteNotes(note: Notes)
}