package com.htw.shopexample.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.htw.shopexample.db.Note;
import com.htw.shopexample.NoteViewModel;
import com.htw.shopexample.R;
import com.htw.shopexample.adapter.MainAdapter;
import java.util.Date;
import it.sephiroth.android.library.numberpicker.NumberPicker;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity   {

    private NoteViewModel noteViewModel;
    private NumberPicker numberpicker;

    public static int index = -1;
    public static int top = -1;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onPause() {
        super.onPause();
        //read current recyclerview position
        index = layoutManager.findFirstVisibleItemPosition();
        View v = recyclerView.getChildAt(0);
        top = (v == null) ? 0 : (v.getTop() - recyclerView.getPaddingTop());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //set recyclerview position
        if(index != -1)
        {
            layoutManager.scrollToPositionWithOffset( index, top);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        //RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager((layoutManager));
        recyclerView.setHasFixedSize(true);

        MainAdapter adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton myFab = findViewById(R.id.button_add);
        myFab.setOnClickListener(v -> {

            ViewDialog alert = new ViewDialog();
            alert.showDialog(MainActivity.this);

        });

        noteViewModel.getAllNotes().observe(this, notes -> {

            adapter.setNotes(notes);
            adapter.notifyDataSetChanged();

        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if (direction == ItemTouchHelper.RIGHT) {
                    Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                    noteViewModel.delete(adapter.getNotePossition(viewHolder.getAdapterPosition()));
                } else if (direction == ItemTouchHelper.LEFT) {

                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#00e676"));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(MainActivity.this, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftActionIcon(R.drawable.ic_done_black_24dp)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.achived))
                        .addSwipeRightActionIcon(R.drawable.ic_delete_black_24dp)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.red))
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.archived_list_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(MainActivity.this, HistoryActivity.class);
        MainActivity.this.startActivity(myIntent);
        return false;

    }

    public class ViewDialog {

        public void showDialog(Activity activity) {

            final Dialog dialog = new Dialog(activity);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_add_item_show);

            EditText shopItem = dialog.findViewById(R.id.edit_text_title);
            EditText shopDesc = dialog.findViewById(R.id.edit_text_description);

            numberpicker = dialog.findViewById(R.id.numberPicker1);

            MaterialButton dialogButton = dialog.findViewById(R.id.material_button);

            dialogButton.setOnClickListener(v -> {

                String savedShopItem = shopItem.getText().toString();
                String savedShopDesc = shopDesc.getText().toString();
                int quantity = numberpicker.getProgress();

                if (savedShopItem.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Product Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Date date = new Date();
                Note note = new Note(savedShopItem, savedShopDesc, quantity, date);
                noteViewModel.insert(note);
                dialog.dismiss();
            });


            dialog.show();

        }
    }


}
