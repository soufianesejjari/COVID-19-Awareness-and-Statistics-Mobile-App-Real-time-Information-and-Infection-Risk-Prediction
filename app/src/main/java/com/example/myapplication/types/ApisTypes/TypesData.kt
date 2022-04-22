package com.example.myapplication.types.ApisTypes

import com.example.myapplication.vaccin.TypeVaccinData
import retrofit2.Call
import retrofit2.http.GET

interface TypesData {
    @GET("?table=covid_type")
    fun getdataApp(): Call<List<TypecovidData>>

    @GET("?table=vaccin")
    fun getdataVaccin(): Call<List<TypeVaccinData>>
}