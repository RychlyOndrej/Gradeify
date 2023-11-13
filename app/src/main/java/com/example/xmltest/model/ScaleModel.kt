package com.example.xmltest

import com.jjoe64.graphview.series.DataPoint

class ScaleModel {
    private val scales = ArrayList<Scale>()

    init {
        scales.add(Scale("jmeno1", 100, 85,60,40,20))
        scales.add(Scale("jmeno1sad", 100, 85,60,40,20))
        scales.add(Scale("jmeno1asdasd", 100, 85,60,40,20))
        scales.add(Scale("jmenasdsadsadsadsadsado1", 100, 85,60,40,20))
    }

    fun onViewCreated() {



        // Initialize data and set up the view
        val dataPoints = arrayOf(
            DataPoint(1.0, 10.0),
            DataPoint(2.0, 20.0),
            DataPoint(3.0, 30.0),
            DataPoint(4.0, 40.0),
            DataPoint(5.0, 15.0)
        )

        /*
        view?.showDataOnGraph(dataPoints)
        view?.showTextViewContent("Aktuální škálecák")

         */
    }

    fun onResetBtnClick() {
        // Clear the data in the graph
        //view?.showDataOnGraph(arrayOf())
    }

    fun attachView() {
        //this.view = view
    }

    fun detachView() {
        //this.view = null
    }

    fun getAllScales(): List<Scale> {
        return scales.toList()
    }
}
