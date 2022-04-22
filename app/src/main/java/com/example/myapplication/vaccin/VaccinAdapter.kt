package com.example.myapplication.vaccin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


class VaccinAdapter(
    var typesvaccin: List<TypeVaccinData>


) : RecyclerView.Adapter<VaccinAdapter.Typeviewholder>() {

    inner class Typeviewholder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Typeviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.type_item, parent, false)



        return Typeviewholder(view)

    }

    private var onItemClickListener: ((TypeVaccinData) -> Unit)? = null


    override fun onBindViewHolder(holder: Typeviewholder, position: Int) {

        holder.itemView.apply {
            val textview: TextView = findViewById(R.id.textViewItem)
            textview.text = typesvaccin[position].Nom_V

            setOnClickListener {
                onItemClickListener?.let { it(typesvaccin[position]) }
            }

        }
    }

    override fun getItemCount(): Int {
        return typesvaccin.size
    }

    fun setOnItemClickListener(listener: (TypeVaccinData) -> Unit) {
        onItemClickListener = listener
    }


}
