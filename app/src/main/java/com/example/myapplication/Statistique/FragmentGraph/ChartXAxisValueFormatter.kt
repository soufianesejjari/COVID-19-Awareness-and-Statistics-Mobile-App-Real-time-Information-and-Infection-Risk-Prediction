package com.example.myapplication.Statistique.FragmentGraph

import com.example.myapplication.data.CovidData
import com.github.mikephil.charting.formatter.ValueFormatter
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ChartXAxisValueFormatter(
    private val count: Int,
    private val dataCoved: List<CovidData>
) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String? {

        // Convert float value to date string
        // Convert from seconds back to milliseconds to format time  to show to the user
        val dateInt = value.toInt() + count - 1
        val date = dataCoved[dateInt].Date // your date
// date is already in Standard ISO format so you don't need custom formatted
        val dateTime: ZonedDateTime = OffsetDateTime.parse(date).toZonedDateTime()  // parsed date
// format date object to specific format if needed
        val formatter = DateTimeFormatter.ofPattern("MMM dd").withLocale(Locale.CANADA_FRENCH)








        return dateTime.format(formatter).toString()
    }
}