package com.example.contacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.widget.SimpleCursorAdapter;

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
        AddContact(db);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion)
    {

    }

    public void AddContact(SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                        + ", " + COLUMN_NEMBER  + ") VALUES (name, nember);");
    }

    public void TransformContact(SQLiteDatabase db, int id, String name, int nember)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_NEMBER, nember);
        db.update(TABLE, cv, COLUMN_ID + "=" + id, null);
    }

    public void DeleteContact(SQLiteDatabase db, int id)
    {
        db.delete(TABLE, "_id = ?", new String[]{String.valueOf(id)});
    }

    public SimpleCursorAdapter getCursorAdapter(Context context, int layout, int[] viewIds)
    {
        // запрос данных для отображения
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE,null);

        // какие столбцы и в каком порядке показывать в listview
        String[] columns = new  String[] {COLUMN_ID,COLUMN_NAME,COLUMN_NEMBER};

        // создание адаптера
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,layout,cursor,columns,viewIds,0);
        return  adapter;
    }

}


