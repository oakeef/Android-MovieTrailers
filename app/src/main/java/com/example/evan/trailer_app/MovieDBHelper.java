package com.example.evan.trailer_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Evan on 12/7/2016.
 */

public class MovieDBHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "movies.db";
    private static final int DB_VERSION = 1;

    //Table functionality
    public static final String MOVIES_TABLE = "MOVIES";
    public static final String COLUMN_MOVIE_NAME = "NAME";
    public static final String COLUMN_MOVIE_DESC = "DESCRIPTION";
    public static final String COLUMN_MOVIE_THUMB = "THUMBNAIL";
    public static final String COLUMN_MOVIE_VIDEO = "VIDEO";
    public static final String COLUMN_MOVIE_RATING = "RATING";
    private static String CREATE_MOVIES =
            "CREATE TABLE" + MOVIES_TABLE + "(" +
                    BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_MOVIE_NAME + " TEXT," +
                    COLUMN_MOVIE_DESC + " TEXT," +
                    COLUMN_MOVIE_THUMB + " TEXT," +
                    COLUMN_MOVIE_VIDEO + " TEXT," +
                    COLUMN_MOVIE_RATING + " INTEGER";


    public MovieDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //Movie Table
}
