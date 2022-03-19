package com.example.myapplication.Statistique

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.ViewModel.StatistiqueViewModel
import com.example.myapplication.data.CovidData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


lateinit var dataCoved: List<CovidData>
lateinit var dataS :ArrayList<Entry>
lateinit var dataS2 :ArrayList<BarEntry>
lateinit var dataS3 :ArrayList<BarEntry>

@SuppressLint("StaticFieldLeak")
private lateinit var statistiqueViewModel: StatistiqueViewModel



class StatistiqueFragment() : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statistiqueViewModel = ViewModelProvider(this).get(StatistiqueViewModel::class.java)




    }



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


        val tabLayout=requireView().findViewById<TabLayout>(R.id.tablayout)
        val viewPager2=requireView().findViewById<ViewPager2>(R.id.viewpager2)

        val adapter=GraphAdapter(getChildFragmentManager(),lifecycle)

        viewPager2.adapter=adapter
        viewPager2.setSaveEnabled(false);

        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="semaine"
                }
                1->{
                    tab.text="mois"
                }
                2->{
                    tab.text="3mois"
                }
                3->{
                    tab.text="tout"
                }

            }
        }.attach()









        statistiqueViewModel.getData()
        observeViewModel()

        dataS = ArrayList()
        dataS2 =ArrayList()
        dataS3 =ArrayList()

    }

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
                    createChart2()
                }

            }


        }
    }




    suspend fun   createChart2(){



        var morts: TextView
        var active: TextView
        var recovred: TextView


        morts = requireView().findViewById(R.id.morts)
        active = requireView().findViewById(R.id.active)
        recovred = requireView().findViewById(R.id.recovred)

        active.setText(dataCoved[dataCoved.size-1].Active.toString())
        morts.setText(dataCoved[dataCoved.size-1].Deaths.toString())
        recovred.setText(dataCoved[dataCoved.size-1].Recovered.toString())



    }


}


