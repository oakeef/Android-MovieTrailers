package com.example.evan.trailer_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 12/7/2016.
 */

public class MovieDBHelper extends SQLiteOpenHelper{

    private static final String TAG = "MovieDBHelper";

    //Database Info
    private static final String DB_NAME = "movies.db";
    private static final int DB_VERSION = 1;

    //Table Name
    private static final String MOVIES_TABLE = "MOVIES";

    //Table Columns
    private static final String COLUMN_MOVIE_NAME = "NAME";
    private static final String COLUMN_MOVIE_DESC = "DESCRIPTION";
    private static final String COLUMN_MOVIE_THUMB = "THUMBNAIL";
    private static final String COLUMN_MOVIE_VIDEO = "VIDEO";
    private static final String COLUMN_MOVIE_RATING = "RATING";



    private static MovieDBHelper mDbHelper;

    //return an instance of this class since it has a private constructor
    public static synchronized MovieDBHelper getInstance(Context context) {
        // Use the application context, which will ensure that the
        // Activity's context doesn't accidentally leak.

        if (mDbHelper == null) {
            mDbHelper = new MovieDBHelper(context.getApplicationContext());
        }
        return mDbHelper;
    }

    private MovieDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES = "CREATE TABLE " + MOVIES_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_MOVIE_NAME + " TEXT NOT NULL," +
                COLUMN_MOVIE_DESC + " TEXT," +
                COLUMN_MOVIE_THUMB + " TEXT NOT NULL DEFAULT '?'," +
                COLUMN_MOVIE_VIDEO + " TEXT NOT NULL DEFAULT '?'," +
                COLUMN_MOVIE_RATING + " INTEGER NOT NULL DEFAULT 0)";
        db.execSQL(CREATE_MOVIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + MOVIES_TABLE);

            onCreate(db);
        }
    }


    //Insert Movie Data into database
    public void insertMovieData(MovieData movieData){

        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_MOVIE_NAME, movieData.name);
            values.put(COLUMN_MOVIE_DESC, movieData.description);
            values.put(COLUMN_MOVIE_THUMB, movieData.thumbnail);
            values.put(COLUMN_MOVIE_VIDEO, movieData.video);
            values.put(COLUMN_MOVIE_RATING, movieData.rating);

            db.insertOrThrow(MOVIES_TABLE, null, values);
            db.setTransactionSuccessful();
        }catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add post to database");
        }  finally {

            db.endTransaction();
        }

    }//end insertMovieData

    //Fetch all data from Movies table
    public List<MovieData> getAllMovies() {

        List<MovieData> moviesList = new ArrayList<>();

        String MOVIE_DATA_SELECT_QUERY = "SELECT * FROM " + MOVIES_TABLE;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(MOVIE_DATA_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    MovieData movie = new MovieData();
                    movie.name = cursor.getString(cursor.getColumnIndex(COLUMN_MOVIE_NAME));
                    movie.description = cursor.getString(cursor.getColumnIndex(COLUMN_MOVIE_DESC));
                    movie.thumbnail = cursor.getString(cursor.getColumnIndex(COLUMN_MOVIE_THUMB));
                    movie.video = cursor.getString(cursor.getColumnIndex(COLUMN_MOVIE_VIDEO));

                    moviesList.add(movie);

                } while (cursor.moveToNext());
            }//end if

        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return moviesList;
    }//end getAllMovies

    //Delete single row from Movies Table
    void deleteRow(String name){

        SQLiteDatabase db = getWritableDatabase();

        try{
            db.beginTransaction();
            db.execSQL("DELETE FROM " + MOVIES_TABLE + " WHERE NAME ='" + name + "'");
            db.setTransactionSuccessful();
        }catch(SQLException e){
            Log.d(TAG, "Error while trying to delete movie");
        }finally{
            db.endTransaction();
        }

    }//end deleteRow
    
}

