package com.htw.shopexample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.htw.shopexample.db.Note;
import com.htw.shopexample.db.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;
    private LiveData<List<Note>> allSortedNotes;

    public LiveData<List<Note>> getAllSortedNotes() {
        return allSortedNotes;
    }

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
        allSortedNotes = repository.getAllNotesSorted();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void update(int iD, boolean isMarked) {
        repository.update(iD, isMarked);
    }

    public void deleteAllNotes() {
        repository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

}


