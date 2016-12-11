package com.example.evan.trailer_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Evan on 12/9/2016.
 */

public class ShowMovies extends AppCompatActivity implements Listener {

    RecyclerView recyclerView;
    MovieDBHelper dbHelper;
    ListAdapter adapter;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_movies);

        dbHelper = MovieDBHelper.getInstance(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.rv_movielist);
        adapter = new ListAdapter(this, dbHelper.getAllMovies());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void movieToDelete(String name) {
        dbHelper.deleteRow(name);

        adapter = new ListAdapter(this, dbHelper.getAllMovies());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
