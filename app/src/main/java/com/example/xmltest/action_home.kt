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
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint



class Window1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_home, container, false)

        val textView = rootView.findViewById<TextView>(R.id.textView4)

        // Nyní máte referenci na TextView a můžete jej upravit
        textView.text = "Aktuální škálecák"

        val buttonData = mutableListOf<ButtonModel>()

        val buttonCount = 5 // Počet tlačítek
        for (i in 1 until buttonCount+1) {
            buttonData.add(ButtonModel("$i", { (1..100).random() }, { (1..100).random() }))
        }



        /*Obsluha grafu*/
        // Get a reference to your GraphView from the XML layout
        val graphView = rootView.findViewById<GraphView>(R.id.graph)

        val series = BarGraphSeries(arrayOf(
            DataPoint(1.0, 10.0),
            DataPoint(2.0, 20.0),
            DataPoint(3.0, 30.0),
            DataPoint(4.0, 40.0),
            DataPoint(5.0, 15.0),
        ))

        graphView.addSeries(series)

        graphView.viewport.isXAxisBoundsManual = true
        graphView.viewport.setMinX(0.5)
        graphView.viewport.setMaxX(6.5)

        graphView.viewport.isYAxisBoundsManual = true
        graphView.viewport.setMinY(0.0)
        graphView.viewport.setMaxY(50.5)

        graphView.gridLabelRenderer.numHorizontalLabels = 5
        graphView.gridLabelRenderer.numVerticalLabels = 5

        //graphView.gridLabelRenderer.horizontalAxisTitle = "Body"
        // Nastavení výšky pro popisky na ose X
        graphView.gridLabelRenderer.labelHorizontalHeight = 40

        val staticLabelsFormatter = StaticLabelsFormatter(graphView)
        val xLabels = arrayOf(
            "1","2", "3", "4", "5"
        )
        staticLabelsFormatter.setHorizontalLabels(xLabels)
        graphView.gridLabelRenderer.labelFormatter = staticLabelsFormatter

        graphView.addSeries(series)

        val resetBtn: Button = rootView.findViewById(R.id.Reset_stats_btn)
        resetBtn.setOnClickListener {
            // Clear the data in the graph
            series.resetData(arrayOf())
        }

        /*KOnec grafu + reset butonu*/



        return rootView
    }


    private fun generateRandomNumber(): Int {
        return (1..100).random() // Generuje náhodná čísla od 1 do 100
    }
}

