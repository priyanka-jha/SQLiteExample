package com.android.priyanka.sqliteexample.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Sqlite_db";

    // Labels table name
   // private static final String TABLE_LABELS = "details";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table if not exists details (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,phone TEXT,qualification TEXT,gender TEXT,hobbies TEXT,dob TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS details" );

        // Create tables again
        onCreate(db);
    }

    public boolean insertData(String name,String email,String phone,String qualification,String gender,String hobbies,String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("email",email);
        values.put("phone",phone);
        values.put("qualification",qualification);
        values.put("gender",gender);
        values.put("hobbies",hobbies);
        values.put("dob",dob);

        db.insert("details",null,values);
        db.close();

        return true;
    }

    public boolean updateData(int id, String name, String email, String phone, String qualification, String gender, String hobbies, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("email",email);
        values.put("phone",phone);
        values.put("qualification",qualification);
        values.put("gender",gender);
        values.put("hobbies",hobbies);
        values.put("dob",dob);
        db.update("details",values,"id=" + id,null);
        db.close();

        return true;
    }

    public int getCount(String tablename) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor cursor1 = db1.rawQuery("Select count(*) from " + tablename + ";", null);
        cursor1.moveToFirst();
        int count = cursor1.getInt(0);
        cursor1.close();
        db1.close();
        return count;
    }

    public int countTable(String tablenm, String wherecondition) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor cursor1 = db1.rawQuery("Select count(*) from " + tablenm + " where " + wherecondition + " ;", null);
        cursor1.moveToFirst();
        int count = cursor1.getInt(0);
        cursor1.close();
        db1.close();
        return count;
    }
}
