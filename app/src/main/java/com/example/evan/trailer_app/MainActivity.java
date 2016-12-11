package com.example.evan.trailer_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_description, et_thumb, et_video;
    Button btn_save;
    MovieDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        dbHelper = MovieDBHelper.getInstance(getApplicationContext());

        et_name = (EditText) findViewById(R.id.et_name);
        et_description = (EditText) findViewById(R.id.et_description);
        et_thumb = (EditText) findViewById(R.id.et_posterurl);
        et_video = (EditText) findViewById(R.id.et_youtubeurl);
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
                }

                dbHelper.insertMovieData(movie);

                Intent intent=new Intent(MainActivity.this, ShowMovies.class);
                startActivity(intent);

            }
        });

    }

}
