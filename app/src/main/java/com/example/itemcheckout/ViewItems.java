package com.example.itemcheckout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewItems extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<ItemDetailHostActivity> itemModalArrayList;
    private SQLiteOperations sqliteOperations;
    private ItemRVAdapter itemRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        // initializing our all variables.
        itemModalArrayList = new ArrayList<>();
        sqliteOperations = new SQLiteOperations(ViewItems.this);

        // getting our item array
        // list from db handler class.
        itemModalArrayList = sqliteOperations.readItems();

        // on below line passing our array lost to our adapter class.
        itemRVAdapter = new ItemRVAdapter(itemModalArrayList, ViewItems.this);
        coursesRV = findViewById(R.id.idRVItems);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewItems.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(itemRVAdapter);
    }
}