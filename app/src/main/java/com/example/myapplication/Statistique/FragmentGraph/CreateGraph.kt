package com.example.myapplication.Statistique.FragmentGraph

import CustomMarkerView
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.data.CovidData
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData


object CreateGraph {
    private lateinit var dataS2: ArrayList<BarEntry>
    private lateinit var barchartdata: BarChart
    private lateinit var chart: CombinedChart

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createChart2(
        chart: CombinedChart,
        count: Int,
        dataCoved: List<CovidData>,
        context: Context
    ): MutableMap<String, Int> {


        dataS2 = ArrayList()
//date test


        //RoomviewModel.dataCovid?.covidDataDao()!!.covidDataSelect()

        val cas = dataCoved[dataCoved.size - 1].Confirmed - dataCoved[count].Confirmed
        val casBefore15 = dataCoved[dataCoved.size - 20].Confirmed - dataCoved[count].Confirmed - dataCoved[count].Deaths
        val mort = dataCoved[dataCoved.size - 1].Deaths - dataCoved[count].Deaths
        val recovre = cas - casBefore15
        val moyenCas = cas / (dataCoved.size - 1 - count)
        val moyenMort = mort / (dataCoved.size - 1 - count)
        val recovredCas=cas-mort
        val recovredMoyen:Int=recovredCas/ (dataCoved.size - 1 - count)
        var pluGrandNomber:Int =0





        var c = 1
        for (i in count until dataCoved.size) {

            val aujourduit = dataCoved[i].Confirmed
            val hier = dataCoved[i - 1].Confirmed
            val casAujourduit = aujourduit - hier
            dataCoved[i].newDay = casAujourduit
            //les morts Aujourduit
            val aujourduitMort = dataCoved[i].Deaths
            val hierMort = dataCoved[i - 1].Deaths
            val casAujourduitMort = aujourduitMort - hierMort
            dataCoved[i].newDeaths = casAujourduitMort


            dataS2.add(BarEntry(c.toFloat(), dataCoved[i].newDay!!.toFloat()))

            Log.i("sssssssssssssssssssss", (" " + c.toString()))
            if(casAujourduit>pluGrandNomber){
                pluGrandNomber=casAujourduit
            }
            c = c + 1
        }
        val donneList = mutableMapOf(
            "cas" to cas,
            "mort" to mort,
            "moyenCas" to moyenCas,
            "plusGrand" to pluGrandNomber,
            "moyenMort" to moyenMort,
            "recovredCas" to recovredCas,
            "recovredMoyen" to recovredMoyen

        )

//        Log.i("sssssssssssssssssssss", (" "+dataCoved[250].Deaths.toString()))
        val mplinechart: LineChart
        val barChart: BarChart
        var morts: TextView
        var active: TextView
        var recovred: TextView


        var barDataSet1: BarDataSet

        var barDataSet2: BarDataSet
//barchardata1
        barDataSet1 = BarDataSet(dataS2, "")
        barDataSet1.setColors(Color.rgb(247, 152, 10))
        //drawable
        /*  barDataSet1.setDrawFilled(true)
          barDataSet1.fillColor = Color.rgb(247, 152, 10) */
        //(ContextCompat.getColor(context,
        //            R.color.purple_200)
        barDataSet1.valueTextSize = 0f



        barDataSet1.label = "morts"


//bardataset2



        val barData: BarData = BarData(barDataSet1)




//        linedata = LineData(lineDataSet)


        //CombinedChart
        chart.description.isEnabled = false
        //  chart.setBackgroundColor(Color.WHITE)
        chart.setDrawGridBackground(false)
        chart.setDrawBarShadow(false)
        chart.isHighlightFullBarEnabled = false

        // draw bars behind lines

        // draw bars behind lines
        chart.drawOrder = arrayOf(
            CombinedChart.DrawOrder.BAR,
            CombinedChart.DrawOrder.BUBBLE,
            CombinedChart.DrawOrder.CANDLE,
            CombinedChart.DrawOrder.LINE,
            CombinedChart.DrawOrder.SCATTER
        )

        //style chart
        chart.setDrawBarShadow(false)
        chart.setDrawValueAboveBar(true)
        chart.description.isEnabled = false
        chart.axisRight.isEnabled = false


        chart.setDrawGridBackground(false)

        chart.isHighlightFullBarEnabled = false

        chart.xAxis.setDrawGridLines(false)
        chart.axisLeft.setDrawGridLines(false)
        chart.axisRight.setDrawGridLines(false)
        chart.axisRight.setDrawLimitLinesBehindData(false)
        chart.axisRight.setDrawLabels(false)
        chart.xAxis.setDrawLimitLinesBehindData(false)
        chart.legend.isEnabled = false

        //    chart.xAxis.setDrawLabels(false) // hide bottom label

        //remove top border
        chart.xAxis.setDrawAxisLine(false)

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)


        val l: Legend = chart.legend
        l.isWordWrapEnabled = true
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        val rightAxis: YAxis = chart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)


        val leftAxis: YAxis = chart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)


        val xAxis: XAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTH_SIDED
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f
        xAxis.labelCount = 4


        val data = CombinedData()


        data.setData(barData)


        xAxis.axisMaximum = data.xMax + 0.25f

        chart.data = data
//date form
        // Formatter to adjust epoch time to readable date
        // Formatter to adjust epoch time to readable date
        chart.xAxis.valueFormatter = ChartXAxisValueFormatter(count, dataCoved)
        //marKerView
        val mv = CustomMarkerView(context, R.layout.custom_marker_view, dataCoved, count)
        chart.marker = mv
        return donneList
    }


    fun dateString(count: Int) {
        /*  val date = count// your date
      // date is already in Standard ISO format so you don't need custom formatted
          val dateTime : ZonedDateTime = OffsetDateTime.parse(date).toZonedDateTime()  // parsed date
      // format date object to specific format if needed
          val formatter = DateTimeFormatter.ofPattern("EEEE MMM , yyyy").withLocale( Locale.CANADA_FRENCH );
          Log.e("Date 1", dateTime.format(formatter)) // output : Dec 16, 2021 16:42





          Log.i("date: " , date.toString())





          Log.i("doneeeeeeees",date.toString())

         */
    }
}


