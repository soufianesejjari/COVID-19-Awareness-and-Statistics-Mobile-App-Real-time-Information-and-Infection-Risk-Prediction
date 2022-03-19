package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.Statistique.StatistiqueFragment
import com.example.myapplication.Statistique.TypesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val context: Context =this

    private val statistiqueFragment = StatistiqueFragment()
    private val typesFragment = TypesFragment()


    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(statistiqueFragment)
        var Navigation_butom = findViewById<BottomNavigationView>(R.id.Navigation_butom)
        Navigation_butom.setSaveEnabled(true);
        Navigation_butom.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.typea ->replaceFragment(typesFragment)
                R.id.statistiqua ->replaceFragment(statistiqueFragment)
            }
            true
        }
    }


     private fun replaceFragment(fragment: Fragment){

        if(fragment !=null){
            fragment.setRetainInstance(true); // IMPORTANT

            val transforme=supportFragmentManager.beginTransaction()
            transforme.replace(R.id.fragment_container,fragment)
            transforme.commit()
        }
    }
}