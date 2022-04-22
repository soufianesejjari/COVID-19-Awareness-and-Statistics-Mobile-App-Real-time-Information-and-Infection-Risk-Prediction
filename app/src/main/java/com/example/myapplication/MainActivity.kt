package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.Statistique.StatistiqueFragment
import com.example.myapplication.testFragment.Test_Fragment
import com.example.myapplication.types.ApisTypes.TypesFragment
import com.example.myapplication.vaccin.VaccinFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val context: Context = this

    private val statistiqueFragment = StatistiqueFragment()
    private val typesFragment = TypesFragment()
    private val vaccinFragment = VaccinFragment()
    private val testFragment = Test_Fragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val test: String = intent.extras?.get("internet") as String
        if (test == "Error") {
            statuInternet(
                "aucune internet au donnes stocké ",
                " l'application besoin au minimum d'interne dans la premier fois \n s'il vous plait essyé avec un internet ",
                "sortir"
            )
        } else if (test == "baseDonne") {

            statuInternet(
                "aucune internet  ",
                " les donnes de covid n'a pas a jour  \n quelques  functionalite ne sont pas marché correctement ",
                "D'accord"
            )

        }




        setContentView(R.layout.activity_main)
        //supportActionBar?.hide()

        replaceFragment(statistiqueFragment)
        var Navigation_butom = findViewById<BottomNavigationView>(R.id.Navigation_butom)
        Navigation_butom.isSaveEnabled = true
        Navigation_butom.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.typea -> replaceFragment(typesFragment)
                R.id.statistiqua -> replaceFragment(statistiqueFragment)
                R.id.vaccin -> replaceFragment(vaccinFragment)
                R.id.test -> replaceFragment(testFragment)

            }
            true
        }
    }

    private fun statuInternet(titre: String, phrase: String, button: String) {

        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(titre)
            .setMessage(phrase)
            .setPositiveButton(
                button
            ) { dialogInterface, i -> finish() }.show()
    }

    private fun replaceFragment(fragment: Fragment) {

        if (fragment != null) {


            val transforme = supportFragmentManager.beginTransaction()
            transforme.replace(R.id.fragment_container, fragment)
            transforme.commit()
        }
    }
}