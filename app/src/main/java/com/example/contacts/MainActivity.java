package com.example.contacts;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    DataBaseAccessor DBA;
    SimpleCursorAdapter noteAdapter;

    final public static String KEY_ID = "ID";
    final public static String KEY_NAME = "name";
    final public static String KEY_NEMBER = "nember";
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {



                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBA = new DataBaseAccessor(this);
        listView = findViewById(R.id.List);
        DBA.onCreate(db);
        noteAdapter = AdapterUpdate();
        setList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //обработка нажития на каждый конкретный эленемт
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Transition(position);  // вызывает метод для передачи данных в дочернию активити
            }
        });
        

    }

    private void Transition(int position)
    {
        String name = ((Cursor) noteAdapter.getItem(position)).getString(1);

        String nember = ((Cursor) noteAdapter.getItem(position)).getString(2);
        Intent Intent = new Intent(this, SecondActivity.class);
        Intent.putExtra(KEY_NAME, name);
        Intent.putExtra(KEY_NEMBER, nember);
        Intent.putExtra(KEY_ID, position);
        mStartForResult.launch(Intent);

    }

    private void setList()
    {
        ListView listView = findViewById(R.id.List);
    }

    private SimpleCursorAdapter AdapterUpdate() {
        // получить адаптер из класса
        SimpleCursorAdapter adapter = DBA.getCursorAdapter(this,
                android.R.layout.two_line_list_item, // Разметка одного элемента ListView
                new int[]{android.R.id.text1,android.R.id.text2}); // текст этого элемента

        // установить адаптер в listview
        listView.setAdapter(adapter);
        return adapter;
    }


}