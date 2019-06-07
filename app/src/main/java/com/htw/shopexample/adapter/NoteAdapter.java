package com.htw.shopexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<com.htw.shopexample.Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
            com.htw.shopexample.Note currentNote = notes.get(position);
            holder.title.setText(currentNote.getTitle());
            holder.desc.setText(currentNote.getDesc());
            holder.priority.setText(String.valueOf(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes (List<com.htw.shopexample.Note> notes){

        this.notes = notes;
        notifyDataSetChanged();
    }

    public com.htw.shopexample.Note getNotePossition (int possition){
        return notes.get(possition);
    }


    class NoteHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView desc;
        private TextView priority;
        private CardView cardViewColor;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            desc = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);
            cardViewColor = itemView.findViewById(R.id.card_view);
        }
    }

}
