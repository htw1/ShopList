package com.htw.shopexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.htw.shopexample.R;
import com.htw.shopexample.db.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item_history, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        Note currentNote = notes.get(position);
        //Date format
        SimpleDateFormat myFormat = new SimpleDateFormat("EEEE,  d MMMM, HH:mm");
        Date date = currentNote.getCreateDate();
        String outputDateStr = myFormat.format(date);

        holder.title.setText(currentNote.getTitle());
        holder.date.setText(outputDateStr);
        holder.priority.setText(String.valueOf(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {

        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNotePossition(int possition) {
        return notes.get(possition);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView date;
        private TextView priority;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            date = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);
        }
    }

}
