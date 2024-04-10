package com.mykotlindemo.ui.roomdatabse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun Dao(): Dao

    companion object {
        private var INSTANCE: MovieDatabase? = null
        fun getDatabase(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MovieDatabase::class.java,
                        "MovieDatabase_"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}