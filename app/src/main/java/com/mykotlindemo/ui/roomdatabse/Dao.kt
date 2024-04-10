package com.mykotlindemo.ui.roomdatabse

import androidx.room.*

@androidx.room.Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(dataEntity: DataEntity)

    @Query("SELECT * FROM MovieData")
    fun getAllData(): MutableList<DataEntity>

    @Delete
    fun delete(dataEntity: DataEntity)

}