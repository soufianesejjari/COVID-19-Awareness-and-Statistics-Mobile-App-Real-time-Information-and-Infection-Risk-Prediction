package com.example.myapplication.Statistique

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.ViewModel.CovInfoViewModel
import com.example.myapplication.ViewModel.ViewModelFactory
import com.example.myapplication.data.CovidData
import com.example.myapplication.ressources.Resource
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


lateinit var dataCoved: List<CovidData>
lateinit var dataS: ArrayList<Entry>
lateinit var dataS2: ArrayList<BarEntry>
lateinit var dataS3: ArrayList<BarEntry>

@SuppressLint("StaticFieldLeak")
//private lateinit var statistiqueViewModel: StatistiqueViewModel


class StatistiqueFragment : Fragment() {

    lateinit var viewModel: CovInfoViewModel
    var viewModelFactory: ViewModelFactory = ViewModelFactory()
    var statuConnectionMessage = "Vous etes a jour"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.statistique_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //tablayout Model
        val context = requireContext()
        //   viewModel = (activity as Laoding).covInfoViewModel
        viewModel = ViewModelProvider(this, viewModelFactory).get(CovInfoViewModel::class.java)

        val tabLayout = requireView().findViewById<TabLayout>(R.id.tablayout)
        val viewPager2 = requireView().findViewById<ViewPager2>(R.id.viewpager2)

        val adapter = GraphAdapter(childFragmentManager, lifecycle)

        viewPager2.adapter = adapter
        viewPager2.isSaveEnabled = false

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "semaine"
                }
                1 -> {
                    tab.text = "mois"
                }
                2 -> {
                    tab.text = "3mois"
                }
                3 -> {
                    tab.text = "tout"
                }

            }
        }.attach()


//apeeeeeel dyal function bah t 2ktiva
        //    statistiqueViewModel.getData()


        observeViewModel()

        dataS = ArrayList()
        dataS2 = ArrayList()
        dataS3 = ArrayList()

    }

    private fun observeViewModel() {
        viewModel.observData().observe(
            viewLifecycleOwner,
        ) { response ->
            when (response) {
                is Resource.Success -> {

                    response.data?.let { newsResponse ->
                        Log.i("Bien fait", "entrain de faire")
                        if (newsResponse == null) {

                        } else {
                            var statuColor: ImageView = requireView().findViewById(R.id.statuColor)

                            statuColor.setColorFilter(Color.GREEN)

                            dataCoved = newsResponse
                            createChart2()
                        }


                    }
                }
                is Resource.BaseDonne -> {

                    response.data?.let { newsResponse ->
                        Log.i("Bien fait", "entrain de faire")

                        dataCoved = newsResponse
                        statuConnectionMessage =
                            "Dernier mis a jour:\n" + dataCoved[dataCoved.size - 1].Date.toString()
                                .subSequence(0, 10)

                        createChart2()

                    }
                }


            }
        }
    }


    fun createChart2() {


        var morts: TextView
        var active: TextView
        var recovred: TextView
        var confirme: TextView
        var statuConnection: TextView


//implimentation
        morts = requireView().findViewById(R.id.decide)
        active = requireView().findViewById(R.id.active)
        confirme = requireView().findViewById(R.id.confirme)
        statuConnection = requireView().findViewById(R.id.statuConnection)

        recovred = requireView().findViewById(R.id.recovred)

        //remplissage
        statuConnection.text = statuConnectionMessage
        confirme.text = "40"

        active.text = "532"
        morts.text = "0"
        recovred.text = "108"


    }


}


