package com.example.leetcode.sqlite;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {AppDataInfo.class}, version = 1, exportSchema = false)
public abstract class AppLibraryDatabase extends RoomDatabase {
    public abstract AppDataDao appDataDao();
}
