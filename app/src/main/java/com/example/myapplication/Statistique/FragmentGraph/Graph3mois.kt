package com.example.myapplication.Statistique.FragmentGraph

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.ViewModel.CovInfoViewModel
import com.example.myapplication.ViewModel.StatistiqueViewModel
import com.example.myapplication.ViewModel.ViewModelFactory
import com.example.myapplication.data.CovidData
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private lateinit var dataCoved: List<CovidData>
private lateinit var dataS: ArrayList<Entry>
private lateinit var dataS2: ArrayList<BarEntry>
private lateinit var dataS3: ArrayList<BarEntry>
private lateinit var lineDataSet: LineDataSet
private lateinit var linedata: LineData
private lateinit var barchartdata: BarChart
private lateinit var chart: CombinedChart

//textView


@SuppressLint("StaticFieldLeak")

private lateinit var statistiqueViewModel: StatistiqueViewModel

class Graph3mois : Fragment() {
    lateinit var viewModel: CovInfoViewModel
    var viewModelFactory: ViewModelFactory = ViewModelFactory()
    private lateinit var cas: TextView
    private lateinit var morts: TextView
    private lateinit var active: TextView
    private lateinit var recovred: TextView

    //progress
    private lateinit var cas2: TextView
    private lateinit var morts2: TextView
    private lateinit var active2: TextView
    private lateinit var recovred2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statistiqueViewModel = ViewModelProvider(this).get(StatistiqueViewModel::class.java)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CovInfoViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph3mois, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //   statistiqueViewModel.getData()
        observeViewModel()


    }

    private fun observeViewModel() {
        viewModel.observData().observe(
            viewLifecycleOwner,
        ) { t ->
            if (t.data == null) {
                Log.i("ssssssssssss", "pas de enternet ou donnes local")
            } else {

                dataCoved = t.data
                GlobalScope.launch(Dispatchers.Main) {
                    chart = requireView().findViewById(R.id.barChart)
                    //textview
                    cas = requireView().findViewById(R.id.cas)
                    morts = requireView().findViewById(R.id.morts)
                    active = requireView().findViewById(R.id.active)
                    recovred = requireView().findViewById(R.id.recored)


                    val listDonne: MutableMap<String, Int> =
                        statistiqueViewModel.get3moisGraphe(chart)

                    cas2 = requireView().findViewById(R.id.cas2)
                    morts2 = requireView().findViewById(R.id.morts2)
                    active2 = requireView().findViewById(R.id.active2)
                    recovred2 = requireView().findViewById(R.id.recored2)

                    cas.text = listDonne["cas"].toString()
                    morts.text = listDonne["mort"].toString()

                    active.text = listDonne["plusGrand"].toString()
                    recovred.text = listDonne["recovredCas"].toString()

                    cas2.text = listDonne["moyenCas"].toString()+" par jour"
                    morts2.text = listDonne["moyenMort"].toString()+" par jour"
                    active2.text = ""
                    recovred2.text = listDonne["recovredMoyen"].toString()+" par jour"


                    Log.i(
                        "pppppppppp",
                        listDonne["prcMoyenCas"].toString() + " mort" + listDonne["prcMort"].toString()
                    )


                }

            }


        }
    }


}


