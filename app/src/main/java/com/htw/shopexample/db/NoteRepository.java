package com.htw.shopexample.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.htw.shopexample.util.RefreshLiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    private LiveData<List<Note>> allNotesSorted;
    public  LiveData<List<Note>> getAllNotesSorted() {
        return allNotesSorted;
    }

    public NoteRepository(Application application) {
        super();

        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes("-1 day");
        allNotesSorted = noteDao.getAllNotesSorted();

    }


    public void insert(Note note) {
        new insertNoteAsynTask(noteDao).execute(note);
    }

    public void update(int iD, boolean isMarked) {
        EditParams editParams = new EditParams(iD, isMarked);
        new uploadNoteAsynTask(noteDao).execute(editParams);
    }

    public void delete(Note note) {
        new deleteNoteAsynTask(noteDao).execute(note);
    }

    public void deleteAll(Note note) {
        new deleteAllNotesNoteAsynTask(noteDao).execute();
    }


    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    // I
    private static class insertNoteAsynTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        public insertNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    // II

    private static class EditParams {
        int iD;
        boolean isMarked;

        EditParams(int iDd, boolean isMarked) {
            this.iD = iDd;
            this.isMarked = isMarked;
        }
    }

    private static class uploadNoteAsynTask extends AsyncTask<EditParams, Boolean, Void> {

        private NoteDao noteDao;

        public uploadNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(EditParams... editkParams) {
            int iD = editkParams[0].iD;
            boolean isMarked = editkParams[0].isMarked;
            noteDao.updateMakredField(iD, isMarked);
            return null;
        }
    }


    // III
    private static class deleteNoteAsynTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        public deleteNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    // IV
    private static class deleteAllNotesNoteAsynTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        public deleteAllNotesNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteAllNotes();
            return null;
        }
    }


}
