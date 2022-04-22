package com.example.myapplication.testFragment.ModelMachineLearning

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.pmml4s.model.Model
import java.io.InputStream
import java.net.URL


class Machinelearning : AppCompatActivity() {
    lateinit var model: Model


    suspend fun getResults(bzkMap: HashMap<String, Any>): MutableMap<String, Any>? {

        val job1 = GlobalScope.launch(Dispatchers.IO) {
            // delay function (a suspend function) must called within coroutine
            // or another suspend function deleteAll

            var url: URL =
                URL("https://pfecovinfo.000webhostapp.com/Arbre.xml")
            var `is`: InputStream = url.openStream()
            model = Model.fromInputStream(`is`)


        }
        job1.join()


        var result: MutableMap<String, Any>? = model.predict(bzkMap)
        return result
    }


}