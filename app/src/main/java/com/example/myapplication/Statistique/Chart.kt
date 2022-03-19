package com.example.myapplication.Statistique

class Chart {
  /*  suspend fun  createChart2(){
        delay(3000)
        val conteext: Context =requireContext()
        val userDao = CovidDatadb.invoke(conteext).covidDataDao()

        GlobalScope.launch(Dispatchers.IO){
            // delay function (a suspend function) must called within coroutine
            // or another suspend function
            dataCoved= userDao.covidDataSelect()
        }



        Log.i("doneeeeeeees", dataCoved.size.toString())
        //RoomviewModel.dataCovid?.covidDataDao()!!.covidDataSelect()

        for (i in 1 until dataCoved.size){
            val aujourduit = dataCoved[i].Confirmed
            val hier = dataCoved[i-1].Confirmed
            val casAujourduit=aujourduit-hier
            dataCoved[i].newDay= casAujourduit
            dataS.add(Entry(i.toFloat(),dataCoved[i].Deaths.toFloat()) )

        }

        Log.i("sssssssssssssssssssss", (dataCoved+" "+dataCoved[6].newDay.toString()).toString())
        val mplinechart : LineChart
        var morts: TextView
        var active: TextView
        var recovred: TextView

        mplinechart= requireView().findViewById(R.id.cas_chart)
        lineDataSet= LineDataSet(dataS,"test")
        linedata= LineData(lineDataSet)

        mplinechart.data=linedata
        lineDataSet.color= Color.BLACK
        lineDataSet.valueTextColor= Color.BLACK
        lineDataSet.valueTextSize= 20f
        val xaxix: XAxis =mplinechart.getXAxis()
        val l1= StatistiqueFragment.MyYAxisValueFormatter()
        xaxix.valueFormatter=(StatistiqueFragment.MyYAxisValueFormatter())
        morts = requireView().findViewById(R.id.morts)
        active = requireView().findViewById(R.id.active)
        recovred = requireView().findViewById(R.id.recovred)

        active.setText(dataCoved[dataCoved.size-1].Active.toString())
        morts.setText(dataCoved[dataCoved.size-1].Deaths.toString())
        recovred.setText(dataCoved[dataCoved.size-1].Recovered.toString())



    } */
}