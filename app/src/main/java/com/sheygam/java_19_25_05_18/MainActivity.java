package com.sheygam.java_19_25_05_18;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyAdapter.MyAdapterCallback {

    private RecyclerView myList;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = findViewById(R.id.myList);

//        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this,2);
        adapter = new MyAdapter();
        adapter.setCallback(this);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        myList.setAdapter(adapter);
        myList.setLayoutManager(manager);
        myList.addItemDecoration(divider);


        findViewById(R.id.addBtn)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addPerson();
            }
        });

        findViewById(R.id.delBtn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.remove();
                    }
                });

        ItemTouchHelper touchHelper = new ItemTouchHelper(new MyItemTouchCallback());
        touchHelper.attachToRecyclerView(myList);
    }

    @Override
    public void onRowClicked(int position, Person person) {
        Toast.makeText(this, "Clicked " + person.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImgClicked(int position) {
        Toast.makeText(this, "Image " + position, Toast.LENGTH_SHORT).show();
    }

    private void removeRow(final int index){
        new AlertDialog.Builder(this)
                .setTitle("Remove item?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Are you sure you want remove this item!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(index);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.cancelRemove(index);
                    }
                })
                .setCancelable(false)
                .create()
                .show();

    }


    class MyItemTouchCallback extends ItemTouchHelper.Callback{

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,0);
//            return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.START|ItemTouchHelper.END);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            Log.d("MY_TAG", "onMove: ");
            adapter.move(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            if(direction == ItemTouchHelper.END || direction == ItemTouchHelper.START){
                removeRow(viewHolder.getAdapterPosition());
            }
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
//            return true;
            return false;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    }
}
