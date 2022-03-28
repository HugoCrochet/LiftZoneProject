package com.example.liftzone.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    //change version when upgraded
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Workout.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + DBContract.Workout.TABLE_NAME + " (" +
                DBContract.Workout._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.Workout.COLUMN_NAME + " TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DBContract.Workout.TABLE_NAME;
        onCreate(db);
    }

    public void insertWorkout(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(DBContract.Workout.COLUMN_NAME,name);

        // return row primary key
        long newRowId = db.insert(DBContract.Workout.TABLE_NAME,null,row);
    }

    public List<Response> selectAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection = {
                DBContract.Workout.COLUMN_NAME
        };
        String selection = DBContract.Workout.COLUMN_NAME+"=workout_name";
        String[] selectionArgs = {};
        String sortOrder = DBContract.Workout.COLUMN_NAME + " DESC";
        Cursor cursor = db.query(DBContract.Workout.TABLE_NAME,projection, selection, selectionArgs, null, null, sortOrder);
        List<Response> responses = new ArrayList<>();
        while(cursor.moveToNext()){
            String name = cursor.getString((int)cursor.getColumnIndex(DBContract.Workout.COLUMN_NAME));
            int i = 0;
            Response tmp = new Response (name, i);
            responses.add(tmp);
            i++;
        }
        cursor.close();
        return responses;
    }



}