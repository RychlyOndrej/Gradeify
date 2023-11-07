package com.example.xmltest


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xmltest.ui.theme.ButtonModel


class ButtonAdapter(
    private val buttonData: List<ButtonModel>
) : RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.button)
        val countText1: TextView = itemView.findViewById(R.id.countText1)
        val countText2: TextView = itemView.findViewById(R.id.countText2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = buttonData[position]
        holder.button.text = item.buttonText
        holder.countText1.text = "${item.random1()} b."
        holder.countText2.text = "${item.random2()} b."

        holder.button.setOnClickListener {
            // Reakce na kliknutí na tlačítko
            // Můžete použít item.random1() a item.random2() pro další generování náhodných čísel
        }
    }

    override fun getItemCount(): Int {
        return buttonData.size
    }
}


