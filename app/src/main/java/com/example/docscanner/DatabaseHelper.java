package com.example.docscanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Appusers.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Details(username text, email text primary key, password text, occupation text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Details");
    }
    public boolean insert(String username, String email, String password, String occupation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("occupation",occupation);
        long ins = db.insert("Details",null,contentValues);
        if (ins==-1 )return false;
        else return true;
    }
    public boolean checkemail(String email){
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Details where email=? ", new String [] {email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }
    public Boolean emailpass (String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Details where email=? and password=?", new String [] {email,password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
}
