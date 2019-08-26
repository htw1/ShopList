package com.htw.shopexample.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("UPDATE note_table SET markedTask = :marked_Task WHERE id= :id ")
    void updateMakredField(int id, boolean marked_Task);

    @Query("SELECT * FROM note_table WHERE created_date>=datetime ('now', :duration ) ORDER BY priority DESC ")
    LiveData<List<Note>> getAllNotes(String duration);

    @Query("SELECT * from note_table where created_date=Date(:date)")
    LiveData<List<Note>> getUserDateSortedNotes(String date);

    @Query("SELECT * FROM note_table ORDER BY created_date DESC ")
    LiveData<List<Note>> getAllNotesSorted();

}