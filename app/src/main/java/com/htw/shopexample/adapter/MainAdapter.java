package com.htw.shopexample.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.htw.shopexample.R;
import com.htw.shopexample.db.Note;


public class MainAdapter extends ListAdapter<Note, MainAdapter.NoteHolder> {

    public MainAdapter(@NonNull AsyncDifferConfig<Note> config, boolean isMarked) {
        super(config);

    }

    public MainAdapter() {
        super(DIFF_CALLBACK);
    }


    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {

            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {

            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDesc().equals(newItem.getDesc()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        Note currentNote = getItem(position);
        holder.title.setText(currentNote.getTitle());
        holder.desc.setText(currentNote.getDesc());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));

        if (getNotePossition(position).isMarkedTask() == true) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#00e676"));
            holder.title.setTextColor(Color.parseColor("#ffffff"));
            holder.desc.setTextColor(Color.parseColor("#ffffff"));
            holder.priority.setTextColor(Color.parseColor("#ffffff"));

        }
    }


    public Note getNotePossition(int possition) {
        return getItem(possition);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView desc;
        private TextView priority;
        private CardView cardView;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            desc = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

}
