package com.example.leetcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.example.leetcode.sqlite.AppLibraryDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String DATABASE_NAME = "app_library_database";


    private AppLibraryDatabase mAppLibraryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppLibraryDatabase = Room.databaseBuilder(getApplicationContext(), AppLibraryDatabase.class, DATABASE_NAME).build();

    }
}
