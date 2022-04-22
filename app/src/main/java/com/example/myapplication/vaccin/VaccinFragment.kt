package com.example.myapplication.vaccin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ViewModel.VaccinViewModel
import java.io.Serializable

//
class VaccinFragment : Fragment() {
    lateinit var boutonType: Button
    var dispoDonne: Boolean = false
    lateinit var donnesVaccin: List<TypeVaccinData>


    private lateinit var vaccinViewModel: VaccinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vaccinViewModel = ViewModelProvider(this).get(VaccinViewModel::class.java)

        val context = context
        vaccinViewModel.getType()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vaccin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*      requireView().findViewById<Button>(R.id.type).setOnClickListener{


                  val intent = Intent (getActivity(), typeco::class.java)
                  startActivity(intent)

              } */
        changementData()


    }

    private fun changementData() {
        vaccinViewModel.getVaccinData().observe(
            viewLifecycleOwner,
        ) { t ->
            if (t == null) {
                dispoDonne = false
            } else {
                dispoDonne = true
                donnesVaccin = t
                getRecycle()
            }

        }


    }

    fun getRecycle() {


        if (dispoDonne == true) {
            Toast.makeText(context, "hhHHHHHHHHHhhh", Toast.LENGTH_LONG)
            Log.i("message009", "hhHHHHHHHHHhhh" + donnesVaccin)

            val recyclerView: RecyclerView = requireView().findViewById(R.id.recycleVaccin)

            val adapter = VaccinAdapter(donnesVaccin)
            recyclerView.adapter = adapter
            //envoye les donnes avec les nfo de click

            adapter.setOnItemClickListener {
                it
                //log de test seulment
                Log.i("item", it.Nom_V + " autre info " + it)
                val intent = Intent(
                    requireContext(),
                    VaccinDetail::class.java
                )
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    requireActivity(),
                    (recyclerView as View?)!!, "Vaccin"
                )
                //methode de l'envoye de donnes hggggg
                intent.putExtra("Vaccin", it as Serializable)
                startActivity(
                    intent,options.toBundle()
                )

            }
            recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


        } else {
            Toast.makeText(context, "laaaaaaaaaaaaaaaaaa", Toast.LENGTH_LONG).show()
            Log.i("message009", "aaaaaaaaaaaaaaaaaaaaaaaaah")
            vaccinViewModel.getType()


            changementData()
        }
    }
}

