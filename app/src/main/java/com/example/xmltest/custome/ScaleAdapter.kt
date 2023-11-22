package com.example.xmltest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ScaleAdapter(private val scales: List<Scale>) : RecyclerView.Adapter<ScaleAdapter.ScaleViewHolder>() {

    class ScaleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scaleName: TextView = view.findViewById(R.id.scaleNameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScaleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scale, parent, false)
        return ScaleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScaleViewHolder, position: Int) {
        val scale = scales[position]
        holder.scaleName.text = scale.name

        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        holder.itemView.layoutParams = layoutParams
    }

    override fun getItemCount() = scales.size
}