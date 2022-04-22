package com.example.myapplication.types.ApisTypes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import java.io.Serializable

class TypeCovisList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_typeco)

        val test = intent.extras?.get("key") as List<*>


        val recyclerView: RecyclerView = findViewById(R.id.recycle)

        val adapter = TypeAdapter(test as List<TypecovidData>)
        recyclerView.adapter = adapter
        //envoye les donnes avec les nfo de click

        adapter.setOnItemClickListener {
            it

            //log de test seulment
            Log.i("item", it.Nom + " autre info " + it)
            val intent = Intent(
                this,
                TypeCovidDetail::class.java
            )

            //methode de l'envoye de donnes hggggg
            intent.putExtra("key2", it as Serializable)
            startActivity(
                intent
            )

        }
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

    }
}