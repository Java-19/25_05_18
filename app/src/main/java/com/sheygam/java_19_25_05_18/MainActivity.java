package com.sheygam.java_19_25_05_18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
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
    }

    @Override
    public void onRowClicked(int position, Person person) {
        Toast.makeText(this, "Clicked " + person.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImgClicked(int position) {
        Toast.makeText(this, "Image " + position, Toast.LENGTH_SHORT).show();
    }
}
