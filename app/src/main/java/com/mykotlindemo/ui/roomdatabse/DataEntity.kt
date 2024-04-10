package com.mykotlindemo.ui.roomdatabse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieData")
data class DataEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "movieName")
    val movieName: String,

    @ColumnInfo(name = "movieUrl")
    val movieUrl: String,

    @ColumnInfo(name = "movieDesc")
    val movieDescription: String
)
