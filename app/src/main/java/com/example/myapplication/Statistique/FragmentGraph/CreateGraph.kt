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
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


object CreateGraph {

    private lateinit var dataS :ArrayList<Entry>
    private lateinit var dataS2 :ArrayList<BarEntry>
    private lateinit var dataS3 :ArrayList<BarEntry>
    private lateinit var lineDataSet: LineDataSet
    private lateinit var linedata : LineData
    private lateinit var barchartdata : BarChart
    private lateinit var chart: CombinedChart
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun   createChart2(chart: CombinedChart, count:Int, dataCoved: List<CovidData>,context:Context){


        dataS = ArrayList()
        dataS2 = ArrayList()
        dataS3 = ArrayList()
//date test
        val date =  dataCoved[count].Date // your date
// date is already in Standard ISO format so you don't need custom formatted
        val dateTime : ZonedDateTime = OffsetDateTime.parse(date).toZonedDateTime()  // parsed date
// format date object to specific format if needed
        val formatter = DateTimeFormatter.ofPattern("EEEE MMM , yyyy").withLocale( Locale.CANADA_FRENCH );
        Log.e("Date 1", dateTime.format(formatter)) // output : Dec 16, 2021 16:42





        Log.i("date: " , date.toString())





        Log.i("doneeeeeeees",date.toString())

        //RoomviewModel.dataCovid?.covidDataDao()!!.covidDataSelect()


        var c=1
        for (i in count until dataCoved.size){

            val aujourduit =dataCoved[i].Confirmed
            val hier = dataCoved[i-1].Confirmed
            val casAujourduit=aujourduit-hier
            dataCoved[i].newDay= casAujourduit
            //les morts Aujourduit
            val aujourduitMort = dataCoved[i].Deaths
            val hierMort = dataCoved[i-1].Deaths
            val casAujourduitMort=aujourduitMort-hierMort
            dataCoved[i].newDeaths= casAujourduitMort

            dataS.add(Entry(c.toFloat(),dataCoved[i].newDeaths!!.toFloat()) )
            dataS2.add(BarEntry(c.toFloat(),dataCoved[i].newDay!!.toFloat()) )
            dataS3.add(BarEntry(c.toFloat(),dataCoved[i].newDeaths!!.toFloat()) )
            Log.i("sssssssssssssssssssss", (" "+c.toString()))

            c=c+1
        }

//        Log.i("sssssssssssssssssssss", (" "+dataCoved[250].Deaths.toString()))
        val mplinechart : LineChart
        val barChart : BarChart
        var morts: TextView
        var active: TextView
        var recovred: TextView


        var barDataSet1: BarDataSet
        var barDataSet2: BarDataSet
//barchardata1
        barDataSet1 = BarDataSet(dataS2, "")
        barDataSet1.setColors(Color.BLUE)
        barDataSet1.label = "morts"

//bardataset2
        barDataSet2 = BarDataSet(dataS3, "")

        barDataSet2.label = "novelle"
        barDataSet2.setColors(Color.YELLOW)


        val barData: BarData = BarData(barDataSet1,barDataSet2)


        lineDataSet = LineDataSet(dataS,"test")


        linedata = LineData(lineDataSet)









        //CombinedChart
        chart.getDescription().setEnabled(false)
        chart.setBackgroundColor(Color.WHITE)
        chart.setDrawGridBackground(false)
        chart.setDrawBarShadow(false)
        chart.setHighlightFullBarEnabled(false)

        // draw bars behind lines

        // draw bars behind lines
        chart.setDrawOrder(
            arrayOf(
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
            )
        )

        val l: Legend =chart.getLegend()
        l.isWordWrapEnabled = true
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        val rightAxis: YAxis =chart.getAxisRight()
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)


        val leftAxis: YAxis =chart.getAxisLeft()
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)


        val xAxis: XAxis =chart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTH_SIDED
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f
        xAxis.setLabelCount(4)


        val data = CombinedData()


        data.setData(linedata);
        data.setData(barData);


        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        chart.setData(data);
//date form
        // Formatter to adjust epoch time to readable date
        // Formatter to adjust epoch time to readable date
        chart.xAxis.setValueFormatter(ChartXAxisValueFormatter(count,dataCoved))
        //marKerView
        val mv = CustomMarkerView(context, R.layout.custom_marker_view,dataCoved,count)
        chart.marker = mv

    }
}

class ChartXAxisValueFormatter( private val count: Int,
                                private val dataCoved: List<CovidData>
                                ) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String? {

        // Convert float value to date string
        // Convert from seconds back to milliseconds to format time  to show to the user
        val dateInt = value.toInt()+count-1
        val date =  dataCoved[dateInt].Date // your date
// date is already in Standard ISO format so you don't need custom formatted
        val dateTime : ZonedDateTime = OffsetDateTime.parse(date).toZonedDateTime()  // parsed date
// format date object to specific format if needed
        val formatter = DateTimeFormatter.ofPattern("MMM dd").withLocale( Locale.CANADA_FRENCH );








        return dateTime.format(formatter).toString()
    }

}
