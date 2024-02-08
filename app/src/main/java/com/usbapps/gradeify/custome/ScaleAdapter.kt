package com.usbapps.gradeify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptér pro zobrazování škál v RecyclerView
class ScaleAdapter(private val scales: List<Scale>) : RecyclerView.Adapter<ScaleAdapter.ScaleViewHolder>() {

    // ViewHolder pro každý prvek v RecyclerView
    class ScaleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // TextView pro zobrazení názvu škály
        val scaleName: TextView = view.findViewById(R.id.scaleNameText)
    }

    // Metoda pro vytvoření nové instance ViewHolderu
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScaleViewHolder {
        // Inflace layoutu pro každý prvek v RecyclerView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scale, parent, false)
        return ScaleViewHolder(view)
    }

    // Metoda pro naplnění obsahu ViewHolderu daty pro konkrétní pozici
    override fun onBindViewHolder(holder: ScaleViewHolder, position: Int) {
        // Získání škály pro aktuální pozici
        val scale = scales[position]

        // Nastavení názvu škály do odpovídajícího TextView v ViewHolderu
        holder.scaleName.text = scale.name

        // Nastavení layout parametrů pro aktuální prvek
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        holder.itemView.layoutParams = layoutParams
    }

    // Metoda pro získání počtu prvků v seznamu škál
    override fun getItemCount() = scales.size
}
