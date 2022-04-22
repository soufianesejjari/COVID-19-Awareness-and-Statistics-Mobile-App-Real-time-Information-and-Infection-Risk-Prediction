package com.example.myapplication.Statistique

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.Statistique.FragmentGraph.Graph3mois
import com.example.myapplication.Statistique.FragmentGraph.GraphBonus
import com.example.myapplication.Statistique.FragmentGraph.GraphMois
import com.example.myapplication.Statistique.FragmentGraph.GraphSemaine


class GraphAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                GraphSemaine()
            }
            1 -> {
                GraphMois()

            }
            2 -> {
                Graph3mois()

            }
            3 -> {
                GraphBonus()

            }
            else -> {
                Fragment()
            }

        }
    }
}