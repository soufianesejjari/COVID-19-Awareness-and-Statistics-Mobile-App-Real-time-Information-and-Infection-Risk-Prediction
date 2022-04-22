package com.example.myapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.CovidData

@Dao
interface CovidDataDao {

    //select all data
    @Query("SELECT * FROM covidData")
    fun covidDataSelect(): List<CovidData>

    //ajoute  tous les donnes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCovidData(covidDataList: List<CovidData>)

    //suprime tous les donnes
    @Query("DELETE FROM covidData")
    suspend fun deleteCovidData()

}