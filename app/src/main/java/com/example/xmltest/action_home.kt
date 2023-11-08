package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xmltest.ui.theme.ButtonModel
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint

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

        /*fujky*/
        // Get a reference to your GraphView from the XML layout
        val graphView = rootView.findViewById<GraphView>(R.id.graph)

        val series = BarGraphSeries(arrayOf(
            DataPoint(1.0, 10.0),
            DataPoint(2.0, 20.0),
            DataPoint(3.0, 30.0),
            DataPoint(4.0, 20.0),
            DataPoint(5.0, 10.0),
        ))

// Odstranění statických popisků osy X
        graphView.gridLabelRenderer.isHorizontalLabelsVisible = false

// Nastavení spacing pro mezi sloupci
        series.spacing = 20 // Zde můžete upravit hodnotu podle svých potřeb

// Nastavení rozsahu osy X tak, aby nebyly uříznuté krajní sloupce
        graphView.viewport.isXAxisBoundsManual = true
        graphView.viewport.setMinX(0.5)
        graphView.viewport.setMaxX(5.5)

// Upravte formátování popisků osy X
        val customLabelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX) {
                    // Zde upravte formát popisků osy X podle hodnot datové řady
                    val xValues = arrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
                    val index = xValues.indexOf(value)
                    if (index != -1) {
                        return (index + 1).toString()
                    }
                }
                return super.formatLabel(value, isValueX)
            }
        }

        graphView.gridLabelRenderer.labelFormatter = customLabelFormatter

        graphView.addSeries(series)




        val resetBtn: Button = rootView.findViewById(R.id.Reset_stats_btn)
        resetBtn.setOnClickListener {
            // Clear the data in the graph
            series.resetData(arrayOf())
        }

        /*endoffujky*/


        return rootView
    }

    private fun generateRandomNumber(): Int {
        return (1..100).random() // Generuje náhodná čísla od 1 do 100
    }
}





