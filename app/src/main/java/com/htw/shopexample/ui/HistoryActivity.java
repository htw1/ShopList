package com.htw.shopexample.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.htw.shopexample.NoteViewModel;
import com.htw.shopexample.R;
import com.htw.shopexample.adapter.HistoryAdapter;
import com.htw.shopexample.db.Note;
import com.htw.shopexample.util.Constants;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener {

    private NoteViewModel noteViewModel;
    HistoryAdapter adapter;


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

        adapter = new HistoryAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel.getAllSortedNotes().observe(this, (List<Note> notes) -> {
            adapter.setNotes(notes);
            adapter.notifyDataSetChanged();
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter_list_menu, menu);
        return true;

    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showTimePickerDialog(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    String time = "month/day/year: " + hourOfDay + "/" + minute ;
                    Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.date_item:
                showDatePickerDialog();
                break;
            // action with ID action_settings was selected
            case R.id.time_item:
                showTimePickerDialog();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Sun Aug 25 18:12:11 GMT+00:00 2019
        String date =  dayOfMonth + "/" + month + "/" + year;
        noteViewModel.getUserSortedDateNotes(date).observe(this, (List<Note> notes) -> {
                    adapter.setNotes(notes);
                    adapter.notifyDataSetChanged();
        });
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
    }
}
