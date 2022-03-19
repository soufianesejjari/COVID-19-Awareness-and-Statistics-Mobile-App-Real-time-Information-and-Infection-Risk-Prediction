package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "covidData")
 class CovidData(
   @PrimaryKey var ID: String,
   val Active: Int,
   val Recovered: Int,
   val Deaths: Int,
   val Date: String?,
   val Confirmed: Int,
   val Country: String,
   var newDay: Int?,
   var newDeaths: Int?,



   ){





 }


