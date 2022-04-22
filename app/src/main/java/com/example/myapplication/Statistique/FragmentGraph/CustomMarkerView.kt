import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.data.CovidData
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CustomMarkerView(
    context: Context,
    layout: Int,
    private val dataCoved: List<CovidData>,
    private val count: Int
) : MarkerView(context, layout) {

    private var txtViewData: TextView? = null
    private var txtViewDatared: TextView? = null
    private var dateAuj: TextView? = null

    init {
        txtViewData = findViewById(R.id.txtViewData)
        txtViewDatared = findViewById(R.id.txtViewDataRed)
        dateAuj = findViewById(R.id.dateAuj)


    }

    @SuppressLint("SetTextI18n")
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        try {
            val xAxis = e?.x?.toInt() ?: 0
            val xYxis = e?.y?.toInt() ?: 0


            val date = dataCoved[count + xAxis-1].Date // your date
// date is already in Standard ISO format so you don't need custom formatted
            val dateTime: ZonedDateTime =
                OffsetDateTime.parse(date).toZonedDateTime()  // parsed date
// format date object to specific format if needed
            val formatter =
                DateTimeFormatter.ofPattern("EEEE dd MMM , yyyy").withLocale(Locale.CANADA_FRENCH)




            dateAuj?.text = "le " + dateTime.format(formatter).toString()
            txtViewData?.text = " " + dataCoved[count + xAxis-1].newDay.toString()
            txtViewDatared?.text = " " +dataCoved[count + xAxis-1].newDeaths.toString()

        } catch (e: IndexOutOfBoundsException) {
        }

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-(width / 2f), -height.toFloat())
    }
    private val uiScreenWidth = resources.displayMetrics.widthPixels

    override fun draw(canvas: Canvas?, posX: Float, posY: Float) {
        var newPosX = posX
        if (uiScreenWidth - posX < width) {
            newPosX -= width
        }
        super.draw(canvas, newPosX, posY)
    }
}