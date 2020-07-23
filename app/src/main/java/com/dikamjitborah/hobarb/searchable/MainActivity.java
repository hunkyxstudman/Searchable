package com.dikamjitborah.hobarb.searchable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ItemsBean> itemsBeans = new ArrayList<>();
        itemsBeans.add(new ItemsBean("Ajay", "Sharma"));
        itemsBeans.add(new ItemsBean("Rahul", "Sharma"));
        itemsBeans.add(new ItemsBean("Ram", "Sharma"));
        itemsBeans.add(new ItemsBean("Shyam", "Sharma"));
        itemsBeans.add(new ItemsBean("Raghav", "Sharma"));

        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ItemsAdapter(itemsBeans);


        searchView = findViewById(R.id.search_main);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



    }
}