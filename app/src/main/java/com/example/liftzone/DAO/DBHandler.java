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
    public static final String DATABASE_NAME = "Form.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + DBContract.Form.TABLE_NAME + " (" +
                DBContract.Form._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.Form.COLUMN_NAME + " TEXT," +
                DBContract.Form.COLUMN_DUREE + " INTEGER)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DBContract.Form.TABLE_NAME;
        onCreate(db);
    }

    public void insertMovie(String name, int duree){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(DBContract.Form.COLUMN_NAME,name);
        row.put(DBContract.Form.COLUMN_DUREE,duree);

        // return row primary key
        long newRowId = db.insert(DBContract.Form.TABLE_NAME,null,row);
    }

    public List<Response> selectAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection = {
                DBContract.Form.COLUMN_NAME, DBContract.Form.COLUMN_DUREE
        };
        String selection = DBContract.Form.COLUMN_NAME+"=nom";
        String[] selectionArgs = {};
        String sortOrder = DBContract.Form.COLUMN_NAME + " DESC";
        Cursor cursor = db.query(DBContract.Form.TABLE_NAME,projection, selection, selectionArgs, null, null, sortOrder);
        List<Response> responses = new ArrayList<>();
        while(cursor.moveToNext()){
            String name = cursor.getString((int)cursor.getColumnIndex(DBContract.Form.COLUMN_NAME));
            int note = cursor.getInt((int)cursor.getColumnIndex(DBContract.Form.COLUMN_DUREE));
            int i = 0;
            Response tmp = new Response (name, i);
            responses.add(tmp);
            i++;
        }
        cursor.close();
        return responses;
    }



}