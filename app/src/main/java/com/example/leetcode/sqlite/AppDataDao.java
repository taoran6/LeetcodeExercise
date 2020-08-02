package com.example.leetcode.sqlite;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface AppDataDao {

    @Query("SELECT * FROM app_data")
    List<AppDataInfo> getAll();

    @Query("SELECT * FROM app_data WHERE package_name = :packageName")
    AppDataInfo findByPackageName(String packageName);

    @Insert
    void insertAll(AppDataInfo... appDataInfo);

    @Update
    void updateInfo(AppDataInfo... appDataInfo);

    @Query("DELETE FROM app_data")
    void clear();

}
