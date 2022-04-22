package com.example.myapplication.Statistique

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCall {
    val uel = "https://www.covid-maroc-api.ml/"
    val apiMaroc: CovidService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/live/country/morocco/status/confirmed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CovidService::class.java)
    }
    val apiAujourduit: CovidService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.covid-maroc-api.ml/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CovidService::class.java)
    }
}




