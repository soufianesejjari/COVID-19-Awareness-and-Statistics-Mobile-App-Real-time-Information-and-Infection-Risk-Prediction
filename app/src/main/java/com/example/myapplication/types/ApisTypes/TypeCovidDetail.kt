package com.example.myapplication.types.ApisTypes

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class TypeCovidDetail : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_typev)
//recupiration des donnes de intent
        val test = intent.extras?.get("key2") as TypecovidData
       val  nomtype     :TextView     = findViewById(R.id.nomtype)
       val  originetype :TextView        = findViewById(R.id.originetype)
       val  datetype    :TextView    = findViewById(R.id.datetype)
        val  infection    :TextView    = findViewById(R.id.textView58)
       val   q1         :TextView    = findViewById(R.id.q1)
       val   q2         :TextView     = findViewById(R.id.q2)
       val   q3         :TextView= findViewById(R.id.q3)
       val   q4         :TextView= findViewById(R.id.q4)
       val   q5         :TextView= findViewById(R.id.q5)
       val   q6         :TextView= findViewById(R.id.q6)
        //test de text textView58

        nomtype.text     ="le nom de type est "+test.Nom
        originetype.text =test.origine
        datetype.text    =test.dateDebut
        infection.text= test.infectation
        q1.text          =test.q1
        q2.text          =test.q2
        q3.text          =test.q3
        q4.text          =test.q4
        q5.text          =test.q5
        q6.text          =test.q6

    }
}