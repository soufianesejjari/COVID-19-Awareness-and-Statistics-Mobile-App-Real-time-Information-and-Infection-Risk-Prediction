package com.example.myapplication.Statistique

import com.example.myapplication.data.CovidData
import retrofit2.Call
import retrofit2.http.GET

interface CovidService {
    @GET("date/2020-03-21T13:13:30Z")
    fun getNationalData(): Call<List<CovidData>>
    @GET("")
    fun getAujouduitData(): Call<List<CovidData>>

}