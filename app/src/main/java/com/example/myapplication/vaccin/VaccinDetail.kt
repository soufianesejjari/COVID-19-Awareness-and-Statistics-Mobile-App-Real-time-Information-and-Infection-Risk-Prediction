package com.example.myapplication.vaccin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class VaccinDetail : AppCompatActivity() {

    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView
    private lateinit var text4: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vaccindetail)
//recupiration des donnes de intent
        val test = intent.extras?.get("Vaccin") as TypeVaccinData
    val  Origine_V :TextView = findViewById(R.id.Origine_V)
    val  PourQ     :TextView  =    findViewById(R.id.PourQ)
    val  Nom_V     :TextView = findViewById(R.id.Nom_V)
    val  p1        :TextView = findViewById(R.id.p1)
    val  p2        :TextView  = findViewById(R.id.p2)
        val  p3        :TextView  = findViewById(R.id.p3)


        //test de text
        Origine_V.text = test.Origine_V
        Nom_V.text = test.Nom_V
        PourQ.text = test.PourQ
        p1.text = test.p1
        p2.text = test.p2
        p3.text = test.p3
    }
}