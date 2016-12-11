package com.example.evan.trailer_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Evan on 12/9/2016.
 */

public class AddMovie extends AppCompatActivity{

    EditText et_name, et_description, et_thumb, et_video, et_rating;
    Button btn_save;
    MovieDBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        dbHelper = MovieDBHelper.getInstance(getApplicationContext());

        et_name = (EditText) findViewById(R.id.et_name);
        et_description = (EditText) findViewById(R.id.et_description);
        et_thumb = (EditText) findViewById(R.id.et_posterurl);
        et_video = (EditText) findViewById(R.id.et_youtubeurl);
        et_rating = (EditText) findViewById(R.id.et_rating);
        btn_save = (Button) findViewById(R.id.btn_next);

        btn_save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                MovieData movie = new MovieData();

                if (!et_name.getText().toString().isEmpty()) {
                    movie.name = et_name.getText().toString();
                } else {
                    movie.name = "";
                }
                if (!et_description.getText().toString().isEmpty()) {
                    movie.description = et_description.getText().toString();
                } else {
                    movie.description = "";
                }
                if (!et_thumb.getText().toString().isEmpty()) {
                    movie.thumbnail = et_thumb.getText().toString();
                } else {
                    movie.thumbnail = "";
                }
                if (!et_video.getText().toString().isEmpty()) {
                    movie.video = et_video.getText().toString();
                } else {
                    movie.video = "";
                } if (!et_rating.getText().toString().isEmpty()) {
                    movie.rating = Integer.parseInt(et_rating.getText().toString());
                } else {
                    movie.video = "";
                }

                dbHelper.insertMovieData(movie);

                Intent intent=new Intent(AddMovie.this, ShowMovies.class);
                startActivity(intent);

            }
        });

    }


}
