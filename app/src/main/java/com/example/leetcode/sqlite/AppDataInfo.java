package com.example.leetcode.sqlite;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"package_name"}, unique = true)}, tableName = "app_data")
public class AppDataInfo {
    @PrimaryKey
    @ColumnInfo(name = "package_name")
    @NonNull
    public String packageName;

    @ColumnInfo(name = "cluster_index")
    public int clusterIndex;


    public String keywords;

    public String category;

    public String market;

    public AppDataInfo(String packageName, int clusterIndex, String keywords, String category, String market) {
        this.packageName = packageName;
        this.clusterIndex = clusterIndex;
        this.keywords = keywords;
        this.category = category;
        this.market = market;
    }
}
