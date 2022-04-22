package com.example.myapplication.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Statistique.FragmentGraph.CreateGraph.createChart2
import com.example.myapplication.Statistique.dataCoved
import com.example.myapplication.data.CovidData
import com.example.myapplication.data.db.CovidDatadb
import com.github.mikephil.charting.charts.CombinedChart

class StatistiqueViewModel(application: Application) : AndroidViewModel(application) {


    private var covidDataMutible = MutableLiveData<List<CovidData>?>()

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private var x: Boolean = false
    val userDao = CovidDatadb.invoke(context).covidDataDao()


    /*  fun getData(){
      ApiCall.apiMaroc.getNationalData().enqueue(object : Callback<List<CovidData>> {

          override fun onResponse(
              call: Call<List<CovidData>>,
              response: Response<List<CovidData>>
          ) {
              val donnes =response.body()
              if (donnes != null) {
                  dataCoved =donnes
              }
  runBlocking {
      GlobalScope.launch(Dispatchers.IO){
          // delay function (a suspend function) must called within coroutine
          // or another suspend function deleteAll
          val child1=  userDao.deleteCovidData()

          userDao.insertCovidData(dataCoved)





      }
  }
              x=true

              covidDataMutible.value= dataCoved


          }

          override fun onFailure(call: Call<List<CovidData>>, t: Throwable) {
              x=false
              GlobalScope.launch(Dispatchers.IO){
                  // delay function (a suspend function) must called within coroutine
                  // or another suspend function deleteAll

                  val data:List<CovidData> = userDao.covidDataSelect()

                  GlobalScope.launch(Dispatchers.Main){
                      if(data.isEmpty()){
                          covidDataMutible.value =null
                      }else{
                          covidDataMutible.value =data
                      }

                      Log.i("dataaaaaaa",data.toString())
                  }

              }

           /*   GlobalScope.launch(Dispatchers.Main){
                  // delay function (a suspend function) must called within coroutine
                  // or another suspend function
                  delay(2000)
                  async {createChart2()  }
              } */

          }

      }) }
      fun observData(): MutableLiveData<List<CovidData>?> {
          return covidDataMutible
      }
      fun enligne(): Boolean {
          return x
      }
    */
    @SuppressLint("NewApi")

    suspend fun getSemaineGraphe(chart: CombinedChart): MutableMap<String, Int> {
        val count = dataCoved.size - 7

        return createChart2(chart, count, dataCoved, context)
    }

    @SuppressLint("NewApi")

    suspend fun getMoiGraphe(chart: CombinedChart): MutableMap<String, Int> {
        val count = dataCoved.size - 30
        return createChart2(chart, count, dataCoved, context)
    }

    @SuppressLint("NewApi")
    suspend fun get3moisGraphe(chart: CombinedChart): MutableMap<String, Int> {
        val count = dataCoved.size - 90

        return createChart2(chart, count, dataCoved, context)
    }

    @SuppressLint("NewApi")

    suspend fun getToutGraphe(chart: CombinedChart): MutableMap<String, Int> {
        val count = 1

        return createChart2(chart, count, dataCoved, context)
    }
}