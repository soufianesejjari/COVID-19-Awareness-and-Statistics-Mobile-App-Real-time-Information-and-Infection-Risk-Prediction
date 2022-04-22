package com.example.myapplication.types.ApisTypes

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiTypesCall {


    val vaccin: TypesData by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        Retrofit.Builder()
            .baseUrl("https://pfecovinfo.000webhostapp.com/cvtp.php/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TypesData::class.java)
    }
}
