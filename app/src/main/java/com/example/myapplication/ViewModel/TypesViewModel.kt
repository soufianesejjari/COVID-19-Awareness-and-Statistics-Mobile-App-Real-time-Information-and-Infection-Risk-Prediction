package com.example.myapplication.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.types.ApisTypes.ApiTypesCall
import com.example.myapplication.types.ApisTypes.TypecovidData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypesViewModel(application: Application) : AndroidViewModel(application) {

    private var typeData = MutableLiveData<List<TypecovidData>?>()


    fun getType() {


        ApiTypesCall.vaccin.getdataApp().enqueue(object : Callback<List<TypecovidData>> {

            override fun onResponse(
                call: Call<List<TypecovidData>>,
                response: Response<List<TypecovidData>>


            ) {
                val donnes = response.body()
                val test = donnes?.get(1)?.Nom
                Log.i("ssssssssssss", "bien   " + donnes)
                typeData.value = donnes!!

            }

            override fun onFailure(call: Call<List<TypecovidData>>, t: Throwable) {
                val donnes = null
                Log.i("ssssssssssss", t.toString())

                typeData.value = null

            }

        })

    }


    fun getTypeData(): MutableLiveData<List<TypecovidData>?> {
        return typeData
    }

}