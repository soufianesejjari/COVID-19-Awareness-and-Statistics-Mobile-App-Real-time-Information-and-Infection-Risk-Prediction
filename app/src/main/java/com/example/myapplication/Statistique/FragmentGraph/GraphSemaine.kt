package com.example.myapplication.Statistique.FragmentGraph

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.ViewModel.CovInfoViewModel
import com.example.myapplication.ViewModel.StatistiqueViewModel
import com.example.myapplication.ViewModel.ViewModelFactory
import com.example.myapplication.data.CovidData
import com.github.mikephil.charting.charts.CombinedChart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@SuppressLint("StaticFieldLeak")
private lateinit var statistiqueViewModel: StatistiqueViewModel

lateinit var viewModel: CovInfoViewModel
var viewModelFactory: ViewModelFactory = ViewModelFactory()

class GraphSemaine : Fragment() {
    private lateinit var dataCoved: List<CovidData>

    private lateinit var chart: CombinedChart
    private lateinit var chart2: CombinedChart

    private lateinit var cas: TextView
    private lateinit var morts: TextView
    private lateinit var active: TextView
    private lateinit var recovred: TextView

    private lateinit var cas2: TextView
    private lateinit var morts2: TextView
    private lateinit var active2: TextView
    private lateinit var recovred2: TextView
    //progress

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
        return inflater.inflate(R.layout.fragment_graph_semaine, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //      viewModel.getData()
        observeViewModel()


    }

    @RequiresApi(Build.VERSION_CODES.O)
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
                    //    chart2=requireView().findViewById(R.id.barChart2)
                    statistiqueViewModel.getSemaineGraphe(chart)
                    val listDonne: MutableMap<String, Int> =
                        statistiqueViewModel.getSemaineGraphe(chart)

                    cas = requireView().findViewById(R.id.cas)
                    morts = requireView().findViewById(R.id.morts)
                    active = requireView().findViewById(R.id.active)
                    recovred = requireView().findViewById(R.id.recored)

                    //progress

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



                }

            }


        }
    }


}


