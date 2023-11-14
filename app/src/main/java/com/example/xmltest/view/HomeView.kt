package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint

interface HomeView {
    //udelej si to :)
}

class HomeViewImp : Fragment(), HomeView {
    private val presenter = ScaleModelImp()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_home, container, false)

        // Initialize your views
        val textView = rootView.findViewById<TextView>(R.id.textView4)
        val graphView = rootView.findViewById<GraphView>(R.id.graph)
        val resetBtn: Button = rootView.findViewById(R.id.resetStatsBtn)

        // Attach the view to the presenter

        // Set up button click listener
        resetBtn.setOnClickListener { presenter.onResetBtnClick() }

        // Call the presenter to set up the initial state
        presenter.onViewCreated()

        return rootView
    }

    fun showDataOnGraph(dataPoints: Array<DataPoint>) {
        // Update the graph with the provided data points
        val series = BarGraphSeries(dataPoints)

    }

    fun showTextViewContent(content: String) {
        // Update the TextView with the provided content
        //textView.text = content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Detach the view from the presenter to avoid memory leaks
        presenter.detachView()
    }
}
