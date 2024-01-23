package com.example.loveengine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loveengine.classes.data.NovellData
import com.example.loveengine.classes.data.SaveData
import com.example.loveengine.classes.data.SavedEnvironment
import com.example.loveengine.data.dao.NovellDataDao

@Database(entities = [NovellData::class, SaveData::class, SavedEnvironment::class], version = 11, exportSchema = false)
abstract class NovellDataDatabase : RoomDatabase() {

    abstract fun novellDataDao(): NovellDataDao

    companion object {

        @Volatile
        private var INSTANCE: NovellDataDatabase? = null

        fun getDatabase(context: Context): NovellDataDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NovellDataDatabase::class.java,
                    "db"
                )
                    .createFromAsset("db.db")
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}