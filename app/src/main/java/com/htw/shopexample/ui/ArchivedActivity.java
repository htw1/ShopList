package com.htw.shopexample.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.htw.shopexample.NoteViewModel;
import com.htw.shopexample.R;
import com.htw.shopexample.adapter.ArchivedAdapter;

public class ArchivedActivity extends AppCompatActivity {


    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archived);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        recyclerView.setHasFixedSize(true);

        ArchivedAdapter adapter = new ArchivedAdapter();
        recyclerView.setAdapter(adapter);


        noteViewModel.getAllSortedNotes().observe(this, notes -> {
            adapter.setNotes(notes);
            adapter.notifyDataSetChanged();
        });

    }
}
