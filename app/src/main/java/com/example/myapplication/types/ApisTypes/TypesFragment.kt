package com.example.myapplication.types.ApisTypes

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
import com.example.myapplication.R
import com.example.myapplication.ViewModel.TypesViewModel
import java.io.Serializable


class TypesFragment : Fragment() {
    lateinit var boutonType: Button
    var dispoDonne: Boolean = false
    lateinit var donnesTypes: List<TypecovidData>


    private lateinit var typesViewModel: TypesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        typesViewModel = ViewModelProvider(this).get(TypesViewModel::class.java)

        val context = context
        typesViewModel.getType()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_types, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*      requireView().findViewById<Button>(R.id.type).setOnClickListener{


                  val intent = Intent (getActivity(), typeco::class.java)
                  startActivity(intent)

              } */
        changementData()

        boutonType = requireView().findViewById(R.id.type)

        boutonType.setOnClickListener {

            if (dispoDonne == true) {
                Toast.makeText(context, "hhHHHHHHHHHhhh", Toast.LENGTH_LONG)
                Log.i("message009", "hhHHHHHHHHHhhh" + donnesTypes)


                val intent = Intent(
                    context,
                    TypeCovisList::class.java
                )
                intent.putExtra("key", donnesTypes as Serializable)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    requireActivity(),
                    (boutonType as View?)!!, "profile"
                )
                startActivity(
                    intent,  options.toBundle()
                )


            } else {
                Toast.makeText(context, "laaaaaaaaaaaaaaaaaa", Toast.LENGTH_LONG).show()
                Log.i("message009", "aaaaaaaaaaaaaaaaaaaaaaaaah")
                typesViewModel.getType()


                changementData()
            }
        }


    }

    private fun changementData() {
        typesViewModel.getTypeData().observe(
            viewLifecycleOwner,
        ) { t ->
            if (t == null) {
                dispoDonne = false
            } else {
                dispoDonne = true
                donnesTypes = t
            }

        }


    }
}

