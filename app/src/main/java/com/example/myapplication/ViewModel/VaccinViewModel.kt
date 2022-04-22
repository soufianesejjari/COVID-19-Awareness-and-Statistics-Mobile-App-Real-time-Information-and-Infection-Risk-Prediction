package com.example.myapplication.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.types.ApisTypes.ApiTypesCall
import com.example.myapplication.vaccin.TypeVaccinData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VaccinViewModel(application: Application) : AndroidViewModel(application) {

    private var typeData = MutableLiveData<List<TypeVaccinData>?>()


    fun getType() {


        ApiTypesCall.vaccin.getdataVaccin().enqueue(object : Callback<List<TypeVaccinData>> {

            override fun onResponse(
                call: Call<List<TypeVaccinData>>,
                response: Response<List<TypeVaccinData>>
            ) {
                val donnes = response.body()
                val test = donnes?.get(1)?.Nom_V
                Log.i("ssssssssssss", "bien   " + donnes)
                typeData.value = donnes

            }

            override fun onFailure(call: Call<List<TypeVaccinData>>, t: Throwable) {
                val donnes = null
                Log.i("ssssssssssss", t.toString())

                typeData.value = null

            }

        })

    }


    fun getVaccinData(): MutableLiveData<List<TypeVaccinData>?> {
        return typeData
    }
}

