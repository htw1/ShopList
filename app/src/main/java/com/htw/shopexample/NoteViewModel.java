package com.htw.shopexample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private com.htw.shopexample.NoteRepository repository;
    private LiveData<List<com.htw.shopexample.Note>> allNotes;

    public LiveData<List<com.htw.shopexample.Note>> getAllSortedNotes() {
        return allSortedNotes;
    }

    private LiveData<List<com.htw.shopexample.Note>> allSortedNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new com.htw.shopexample.NoteRepository(application);
        allNotes = repository.getAllNotes();
        allSortedNotes = repository.getAllNotesSorted();
    }

    public void insert (com.htw.shopexample.Note note){
        repository.insert(note);
    }

    public void delete (com.htw.shopexample.Note note){
        repository.delete(note);
    }

    public void update (com.htw.shopexample.Note note){
        repository.update(note);
    }

    public void deleteAllNotes (){
        repository.getAllNotes();
    }

    public LiveData <List<com.htw.shopexample.Note>> getAllNotes (){
        return allNotes;
    }


}


