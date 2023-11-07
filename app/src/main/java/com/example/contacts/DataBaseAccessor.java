package com.example.contacts;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DataBaseAccessor extends SQLiteOpenHelper
{


    // Основные данные базы
    private static final String DATABASE_NAME = "db.db";
    private static final int DB_VERSION = 3;

    // таблицы
    private static final String TABLE = "CONTACTS";

    // колонки
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NEMBER = "nember";


    public DataBaseAccessor(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE users (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_NEMBER + " INTEGER);");
        // добавление начальных данных
        //db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
        //        + ", " + COLUMN_YEAR  + ") VALUES ('Том Смит', 1981);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion)
    {

    }



}


