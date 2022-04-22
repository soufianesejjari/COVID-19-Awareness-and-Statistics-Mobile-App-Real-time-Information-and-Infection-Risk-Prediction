package com.example.myapplication.types.ApisTypes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


class TypeAdapter(
    var typesCovid: List<TypecovidData>


) : RecyclerView.Adapter<TypeAdapter.Typeviewholder>() {
    inner class Typeviewholder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Typeviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item2, parent, false)
        return Typeviewholder(view)

    }

    private var onItemClickListener: ((TypecovidData) -> Unit)? = null


    override fun onBindViewHolder(holder: Typeviewholder, position: Int) {

        holder.itemView.apply {
            val textview: TextView = findViewById(R.id.textViewItem)
            textview.text = typesCovid[position].Nom

            setOnClickListener {
                onItemClickListener?.let { it(typesCovid[position]) }
            }

        }
    }

    override fun getItemCount(): Int {
        return typesCovid.size
    }

    fun setOnItemClickListener(listener: (TypecovidData) -> Unit) {
        onItemClickListener = listener
    }
}