package com.example.xmltest

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xmltest.ui.theme.ButtonModel

/*val textView = rootView.findViewById<TextView>(R.id.textView4)

        // Nyní máte referenci na TextView a můžete jej upravit
        textView.text = "Aktuální škálecák"*/

class Window1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_home, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        val textView = rootView.findViewById<TextView>(R.id.textView4)

        // Nyní máte referenci na TextView a můžete jej upravit
        textView.text = "Aktuální škálecák"

        val buttonData = mutableListOf<ButtonModel>()

        val buttonCount = 5 // Počet tlačítek
        for (i in 1 until buttonCount+1) {
            buttonData.add(ButtonModel("$i", { (1..100).random() }, { (1..100).random() }))
        }


        val adapter = ButtonAdapter(buttonData)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter


        return rootView
    }

    private fun generateRandomNumber(): Int {
        return (1..100).random() // Generuje náhodná čísla od 1 do 100
    }
}





