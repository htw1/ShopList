package com.htw.shopexample.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.htw.shopexample.NoteViewModel;
import com.htw.shopexample.R;
import com.htw.shopexample.adapter.HistoryAdapter;
import com.htw.shopexample.db.Note;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        recyclerView.setHasFixedSize(true);

        HistoryAdapter adapter = new HistoryAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel.getAllSortedNotes().observe(this, (List<Note> notes) -> {
            adapter.setNotes(notes);
            adapter.notifyDataSetChanged();
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
