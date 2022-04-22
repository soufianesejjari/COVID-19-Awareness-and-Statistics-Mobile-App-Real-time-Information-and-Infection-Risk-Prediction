package com.example.myapplication.laoding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.ViewModel.CovInfoViewModel
import com.example.myapplication.ViewModel.ViewModelFactory
import com.example.myapplication.ressources.Resource
import kotlinx.coroutines.runBlocking


class Laoding : AppCompatActivity() {
    var cnx = "notYet"
    val screen = 5000
    private lateinit var loading1: Animation
    private lateinit var loading2: Animation


    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var textView: TextView
    lateinit var covInfoViewModel: CovInfoViewModel
    var viewModelFactory: ViewModelFactory = ViewModelFactory()

    //   val appplication:CovInfoApplication = CovInfoApplication()

    // val test= appplication.applicationContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide status bar
        setContentView(R.layout.laoding_activity)

        covInfoViewModel =
            ViewModelProvider(this, viewModelFactory).get(CovInfoViewModel::class.java)


        //cree deux animations
        loading1 = AnimationUtils.loadAnimation(this, R.anim.load_animation)
        loading2 = AnimationUtils.loadAnimation(this, R.anim.load2_animation)

        imageView = findViewById(R.id.image_view)
        button = findViewById(R.id.start)
        textView = findViewById(R.id.description)

        imageView.animation = loading1
        button.animation = loading2
        textView.animation = loading2

        findViewById<Button>(R.id.start).setOnClickListener {
            val intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(
                intent
            )

        }

        runBlocking { covInfoViewModel.getData(application.applicationContext) }
        runBlocking { covInfoViewModel.getType(application.applicationContext) }


        //   covInfoViewModel.getTypeData()
        getData()

        //   getResult()


    }

    /* private fun getResult() {
         covInfoViewModel.getCompletCheck().observe(this, Observer { response ->
             when(response) {
                 is Resource.Success -> {

                     response.data?.let { newsResponse ->
                         Log.i("Bien fait","entrain de faire")

                         val intent= Intent(
                             this,
                             MainActivity::class.java)
                         intent.putExtra("internet",cnx);

                         startActivity(
                             intent
                         )

                     }
                 }
                 is Resource.BaseDonne -> {

                     response.data?.let { newsResponse ->
                         Log.i("Bien fait","entrain de faire")
                     cnx=false
                         val intent= Intent(
                             this,
                             MainActivity::class.java)
                         intent.putExtra("internet",cnx);

                         startActivity(
                             intent
                         )

                     }
                 }
                 is Resource.Error -> {
                     response.message?.let { newsResponse ->
                         Log.i("problem",newsResponse.toString())
                     }
                     Log.i("problem","cnx")

                 }
                 is Resource.Loading -> {
                     Log.i("laodiiing","en attend")
                 }




             }})} */

    fun getData() {


        covInfoViewModel.observData().observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {

                    response.data?.let { newsResponse ->
                        Log.i("Bien fait", "entrain de faire")
                        cnx = "success"
                        val intent = Intent(
                            this,
                            MainActivity::class.java
                        )
                        intent.putExtra("internet", cnx)

                        startActivity(
                            intent
                        )

                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.i("Error", message)
                        cnx = "Error"
                        val intent = Intent(
                            this,
                            MainActivity::class.java
                        )
                        intent.putExtra("internet", cnx)

                        startActivity(
                            intent
                        )

                    }
                }
                is Resource.Loading -> {
                    Log.i("loading", "entrain de faire")

                }
                is Resource.BaseDonne -> {
                    Log.i("Base Donne", "entrain de faire")
                    response.data?.let { newsResponse ->
                        Log.i("Bien fait", "entrain de faire")
                        cnx = "baseDonne"
                        val intent = Intent(
                            this,
                            MainActivity::class.java
                        )
                        intent.putExtra("internet", cnx)

                        startActivity(
                            intent
                        )

                    }


                }
            }
        })
    }
}