package com.htw.shopexample;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import java.util.List;

public class NoteRepository  {

    private com.htw.shopexample.NoteDao noteDao;
    private LiveData<List<com.htw.shopexample.Note>> allNotes;
    private LiveData<List<com.htw.shopexample.Note>> allNotesSorted;

    public LiveData<List<com.htw.shopexample.Note>> getAllNotesSorted() {
        return allNotesSorted;
    }

    public NoteRepository(Application application) {
        super();

        com.htw.shopexample.NoteDatabase database = com.htw.shopexample.NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes("-1 day");
        allNotesSorted = noteDao.getAllNotesSorted();
    }

    public void insert (com.htw.shopexample.Note note){
        new insertNoteAsynTask(noteDao).execute(note);
    }

    public void update (com.htw.shopexample.Note note){
        new uploadNoteAsynTask(noteDao).execute(note);
    }

    public  void delete (com.htw.shopexample.Note note){new deleteNoteAsynTask(noteDao).execute(note);}

    public void deleteAll (com.htw.shopexample.Note note){
        new deleteAllNotesNoteAsynTask(noteDao).execute();
    }


    public LiveData<List<com.htw.shopexample.Note>> getAllNotes() {
        return allNotes;
    }

    // I
    private static class insertNoteAsynTask extends AsyncTask <com.htw.shopexample.Note,Void, Void>{

        private com.htw.shopexample.NoteDao noteDao;

        public insertNoteAsynTask(com.htw.shopexample.NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(com.htw.shopexample.Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    // II
    private static class uploadNoteAsynTask extends AsyncTask <com.htw.shopexample.Note,Void, Void>{

        private com.htw.shopexample.NoteDao noteDao;

        public uploadNoteAsynTask(com.htw.shopexample.NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(com.htw.shopexample.Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }


    // III
    private static class deleteNoteAsynTask extends AsyncTask <com.htw.shopexample.Note,Void, Void>{

        private com.htw.shopexample.NoteDao noteDao;

        public deleteNoteAsynTask(com.htw.shopexample.NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(com.htw.shopexample.Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    // IV
    private static class deleteAllNotesNoteAsynTask extends AsyncTask <com.htw.shopexample.Note,Void, Void>{

        private com.htw.shopexample.NoteDao noteDao;

        public deleteAllNotesNoteAsynTask(com.htw.shopexample.NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(com.htw.shopexample.Note... notes) {
            noteDao.deleteAllNotes();
            return null;
        }
    }



}
