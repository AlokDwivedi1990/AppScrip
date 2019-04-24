package com.example.alokdwivedi.appscrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class SqlHelperClass extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "trivia.db";
    public static String TABLE_NAME = "trivia";

    public SqlHelperClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      //  db.execSQL(" create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, NAME TEXT, CRICKETER_NAME TEXT, FLAG_COLOR TEXT, TIME TEXT)");
        db.execSQL(" create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, TIME TEXT, NAME TEXT, CRICKETER_NAME TEXT, FLAG_COLOR TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean isInserted(String time, String name, String cricketer_name, String flag_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TIME", time);
        contentValues.put("NAME", name);
        contentValues.put("CRICKETER_NAME", cricketer_name);
        contentValues.put("FLAG_COLOR", flag_name);
        long inserted = db.insert(TABLE_NAME, null, contentValues);

        if (inserted == -1)
            return false;
        else
            return true;
    }

    public Cursor showData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME, null);
        return res;
    }
}
