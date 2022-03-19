package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.CovidData

@Database(entities = [CovidData::class], version =2  )
abstract class CovidDatadb : RoomDatabase() {
    abstract fun covidDataDao(): CovidDataDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var instance: CovidDatadb? = null
        private val LOCK=Any()

       operator fun invoke(context: Context)= instance ?:synchronized(LOCK) {
                 instance ?: buildatabase(context).also { instance =it }

        }
        private fun buildatabase(context: Context)=
                    Room.databaseBuilder(
                     context.applicationContext,
                      CovidDatadb::class.java,
                   "covidData"
                  ).fallbackToDestructiveMigration()
                        .build()

    }
}