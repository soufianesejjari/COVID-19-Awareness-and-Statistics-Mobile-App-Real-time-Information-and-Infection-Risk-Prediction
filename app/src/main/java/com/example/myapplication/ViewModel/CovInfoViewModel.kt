package com.example.myapplication.ViewModel

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Statistique.ApiCall
import com.example.myapplication.data.CovidData
import com.example.myapplication.data.db.CovidDatadb
import com.example.myapplication.ressources.Resource
import com.example.myapplication.types.ApisTypes.ApiTypesCall
import com.example.myapplication.types.ApisTypes.TypecovidData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CovInfoViewModel : ViewModel() {
    lateinit var dataCove: List<CovidData>
    private lateinit var job: Job
    private var covidDataMutible = MutableLiveData<Resource<List<CovidData>?>>()
    private var completeCheck = MutableLiveData<Resource<Boolean>>()

    @SuppressLint("StaticFieldLeak")
    //  private val context = getApplication<Application>().applicationContext
    private var x: Boolean = false
    private var donneSource: Boolean = false

    private var typeData = MutableLiveData<Resource<List<TypecovidData>?>>()
    /* companion object{
         private lateinit var instance: CovInfoViewModel

         @MainThread
         fun getInstance(userId: String): CovInfoViewModel{
             val application:Application= Application()
             instance = if(::instance.isInitialized) instance else CovInfoViewModel(application)
             return instance
         }
     } */

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: CovInfoViewModel? = null
        fun getInstance() =
            instance ?: synchronized(CovInfoViewModel::class.java) {
                instance ?: CovInfoViewModel().also { instance = it }
            }
    }

    private suspend fun getDonneDb(context: Context): List<CovidData>? {
        val userDao = CovidDatadb.invoke(context).covidDataDao()

        var data: List<CovidData>? = null

        job = GlobalScope.launch(Dispatchers.IO) {
            // delay function (a suspend function) must called within coroutine
            // or another suspend function deleteAll
            data = userDao.covidDataSelect()

        }
        job.join()

        return data


    }

    suspend fun getData(context: Context) {

        val userDao = CovidDatadb.invoke(context).covidDataDao()

        GlobalScope.launch(Dispatchers.Main) {
            covidDataMutible.value = Resource.Loading()

        }

        val data = getDonneDb(context)
        job.join()

        if (connexiionDispo(context)) {
            ApiCall.apiMaroc.getNationalData().enqueue(object : Callback<List<CovidData>> {

                override fun onResponse(
                    call: Call<List<CovidData>>,
                    response: Response<List<CovidData>>
                ) {
                    val donnes = response.body()
                    if (donnes != null) {
                        donneSource = true
                        dataCove = donnes
                    }
                    runBlocking {
                        GlobalScope.launch(Dispatchers.IO) {
                            // delay function (a suspend function) must called within coroutine
                            // or another suspend function deleteAll
                            val child1 = userDao.deleteCovidData()
                            userDao.insertCovidData(dataCove)


                        }
                    }
                    x = true

                    covidDataMutible.value = Resource.Success(dataCove)


                }

                override fun onFailure(call: Call<List<CovidData>>, t: Throwable) {
                    x = false
                    GlobalScope.launch(Dispatchers.IO) {
                        // delay function (a suspend function) must called within coroutine
                        // or another suspend function deleteAll

                        //   val data:List<CovidData> = userDao.covidDataSelect()

                        GlobalScope.launch(Dispatchers.Main) {
                            if (data != null) {
                                if (data.isEmpty()) {
                                    covidDataMutible.value = t.message?.let { Resource.Error(it) }
                                } else {
                                    Log.e("dabs est ", data.toString())
                                    covidDataMutible.value = Resource.BaseDonne(data)
                                    donneSource = false
                                }
                            } else {
                                Log.i("dataaaaaaa", "c nulllll")

                            }

                            Log.i("dataaaaaaa", data.toString())
                        }

                    }

                    /*   GlobalScope.launch(Dispatchers.Main){
                           // delay function (a suspend function) must called within coroutine
                           // or another suspend function
                           delay(2000)
                           async {createChart2()  }
                       } */

                }

            })
        } else {


            GlobalScope.launch(Dispatchers.Main) {
                if (data?.isEmpty() == true) {
                    covidDataMutible.value = Resource.Error("pas de internet")
                } else covidDataMutible.value = Resource.BaseDonne(data)

                Log.i("dataaaaaaa dans offline", data.toString())
            }
        }

    }

    fun getType(context: Context) {

        typeData.value = Resource.Loading()

        if (connexiionDispo(context)) {
            ApiTypesCall.vaccin.getdataApp().enqueue(object : Callback<List<TypecovidData>> {

                override fun onResponse(
                    call: Call<List<TypecovidData>>,
                    response: Response<List<TypecovidData>>


                ) {
                    val donnes = response.body()

                    typeData.value = Resource.Success(donnes)

                }

                override fun onFailure(call: Call<List<TypecovidData>>, t: Throwable) {
                    val donnes = null
                    Log.i("ssssssssssss", t.toString())

                    typeData.value = Resource.Error("problem dans api")

                }

            })

        } else {
            typeData.value = Resource.Error("pas d'internet")

        }
    }

    private fun connexiionDispo(getApplication: Context): Boolean {
        val connectivityManager = getApplication.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ContactsContract.CommonDataKinds.Email.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

    fun observData(): MutableLiveData<Resource<List<CovidData>?>> {

        return covidDataMutible


    }

    fun getTypeData(): MutableLiveData<Resource<List<TypecovidData>?>> {
        return typeData
    }

    //function pour elecharge tous les donnes
    suspend fun laodAllData(context: Context) {
        completeCheck.value = Resource.Loading()
        val traitment1: Job = GlobalScope.launch { getData(context) }
        traitment1.join()
        val traitement2: Job = GlobalScope.launch(Dispatchers.Main) { getType(context) }
        traitement2.join()
        if (donneSource == true) {
            completeCheck.value = Resource.Success(true)


        } else {
            completeCheck.value = Resource.BaseDonne(true)

        }

    }

    fun getCompletCheck(): MutableLiveData<Resource<Boolean>> {
        return completeCheck
    }

}