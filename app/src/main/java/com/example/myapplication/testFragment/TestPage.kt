package com.example.myapplication.testFragment


import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.R
import com.example.myapplication.testFragment.ModelMachineLearning.Machinelearning
import kotlinx.coroutines.runBlocking


class TestPage : AppCompatActivity() {
    val machinelearning: Machinelearning = Machinelearning()
    var inFoTest: HashMap<String, Any> = hashMapOf(

    )
   lateinit  var buttonValidation: Button
   lateinit var testLL: LinearLayout
   lateinit var resultLL: LinearLayout
    private lateinit var loading1: Animation
    private lateinit var loading2: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagetest)

         buttonValidation = findViewById(R.id.validation)
         testLL= findViewById(R.id.testLL)
         resultLL= findViewById(R.id.resultLL)

        loading1 = AnimationUtils.loadAnimation(this, R.anim.load_animation)
        loading2 = AnimationUtils.loadAnimation(this, R.anim.load2_animation)

        buttonValidation.setOnClickListener {

            //variable
            val feverRadeo = findViewById<View>(R.id.fever) as RadioGroup
            var fever = (findViewById<View>(feverRadeo.checkedRadioButtonId) as RadioButton)
                .text.toString()
            //variable
            val coughRadeo = findViewById<View>(R.id.cough) as RadioGroup
            val cough = (findViewById<View>(coughRadeo.checkedRadioButtonId) as RadioButton)
                .text.toString()
            //variable
            val soreRadeo = findViewById<View>(R.id.sore) as RadioGroup
            val sore = (findViewById<View>(soreRadeo.checkedRadioButtonId) as RadioButton)
                .text.toString()
            //variable
            val breathRadeo = findViewById<View>(R.id.breath) as RadioGroup
            val breath = (findViewById<View>(breathRadeo.checkedRadioButtonId) as RadioButton)
                .text.toString()
            //variable
            val headRadeo = findViewById<View>(R.id.head) as RadioGroup
            val head = (findViewById<View>(headRadeo.checkedRadioButtonId) as RadioButton)
                .text.toString()
            //variable
            val ageRadeo = findViewById<EditText>(R.id.age)
            val age = ageRadeo.text
            //variable
            val genderRadeo = findViewById<View>(R.id.gender) as RadioGroup
            val gender = (findViewById<View>(genderRadeo.checkedRadioButtonId) as RadioButton)
                .text.toString()  //variable
            val indicationRadeo = findViewById<View>(R.id.indication) as RadioGroup
            val indication =
                (findViewById<View>(indicationRadeo.checkedRadioButtonId) as RadioButton)
                    .text.toString()


            //condition variable
            if (fever == "Oui") {
                inFoTest["fever"] = 1

            } else {
                inFoTest["fever"] = 0
            }
            //condition variable
            if (cough == "Oui") {
                inFoTest["cough"] = 1

            } else {
                inFoTest["cough"] = 0
            }
            //condition variable
            if (sore == "Oui") {
                inFoTest["sore"] = 1

            } else {
                inFoTest["sore"] = 0
            }
            //condition variable
            if (breath == "Oui") {
                inFoTest["breath"] = 1

            } else {
                inFoTest["breath"] = 0
            }
            //condition variable
            if (head == "Oui") {
                inFoTest["head"] = 1

            } else {
                inFoTest["head"] = 0
            }
            //condition variable
            if (fever == "Oui") {
                inFoTest["age_60_and_above"] = "Yes"

            } else {
                inFoTest["age_60_and_above"] = "None"
            }

            //condition variable
            if (gender == "femme") {
                inFoTest["gender"] = "female"

            } else {
                inFoTest["gender"] = "male"
            }

            //condition variable
            if (indication == "je ne sais pas") {
                inFoTest["test_indication"] = "Abroad"

            } else if (indication == "Non") {
                inFoTest["test_indication"] = "Other"
            } else {
                inFoTest["test_indication"] = "Contact with confirmed"

            }
            runBlocking {
                val result: MutableMap<String, Any>? = getResult()
           //       Log.i("taaaaaaag", result.toString())


                val resultCov: TextView = findViewById(R.id.result)

                val positif: TextView = findViewById(R.id.positif)

                val negatif: TextView = findViewById(R.id.negatif)

                resultCov.text = result?.get("predicted_corona_result")?.toString() ?: "pas de result"
                positif.text = result?.get("probability_positive")?.toString()?.subSequence(0, 4).toString()
                negatif.text = result?.get("probability_negative")?.toString()?.subSequence(0, 4).toString()
changeVisibility()
            }


        }


    }

    private fun changeVisibility() {
        testLL.isVisible = false
        resultLL.isVisible = true
        resultLL.animation=loading2

    }

    private suspend fun getResult(): MutableMap<String, Any>? {
        val result = machinelearning.getResults(inFoTest)
        return result
    }

}