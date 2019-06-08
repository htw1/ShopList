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

    @Query("SELECT * FROM note_table  WHERE created_date>=datetime ('now', :duration ) ")
    LiveData<List<Note>> getAllNotes(String duration);

    @Query("SELECT * FROM note_table ORDER BY created_date DESC ")
    LiveData<List<Note>> getAllNotesSorted();

}