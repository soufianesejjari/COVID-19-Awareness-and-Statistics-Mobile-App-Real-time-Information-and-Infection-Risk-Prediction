package com.example.myapplication.Statistique.FragmentGraph

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.ViewModel.StatistiqueViewModel
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
private lateinit var dataS :ArrayList<Entry>
private lateinit var dataS2 :ArrayList<BarEntry>
private lateinit var dataS3 :ArrayList<BarEntry>
private lateinit var lineDataSet: LineDataSet
private lateinit var linedata : LineData
private lateinit var barchartdata : BarChart
private lateinit var chart: CombinedChart
private lateinit var chart2: CombinedChart



@SuppressLint("StaticFieldLeak")
private lateinit var statistiqueViewModel: StatistiqueViewModel

class GraphSemaine : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statistiqueViewModel = ViewModelProvider(this).get(StatistiqueViewModel::class.java)

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

        statistiqueViewModel.getData()
        observeViewModel()



    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeViewModel() {
        statistiqueViewModel.observData().observe(
            viewLifecycleOwner,
        ) { t ->
            if (t==null){
                Log.i("ssssssssssss","pas de enternet ou donnes local")
            }
            else{
                dataCoved =t
                GlobalScope.launch(Dispatchers.Main) {
                    chart = requireView().findViewById(R.id.barChart)
                //    chart2=requireView().findViewById(R.id.barChart2)
                    statistiqueViewModel.getSemaineGraphe(chart)
                  //  statistiqueViewModel.getMoiGraphe(chart2)

                }

            }


        }
    }




}


