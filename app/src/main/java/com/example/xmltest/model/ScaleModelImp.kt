package com.example.xmltest

import com.jjoe64.graphview.series.DataPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Context

interface ScaleModel{
    fun onViewCreated()
    fun onResetBtnClick()
    suspend fun getAllScales(): List<Scale>
    suspend fun removeScale(scale: Scale)
    suspend fun insertScale(scale: Scale)

    suspend fun addNewScale(name: String,
                            procentOne: Int,
                            procentTwo: Int,
                            procentThree: Int,
                            procentFour: Int,
                            procentFive: Int)

}

class ScaleModelImp (private val context: Context): ScaleModel {
    private val scaleDao = ScaleDatabase.getDatabase(context).scaleDao()

    private suspend fun insertInitialScales() {
        /*
                val initialScales = listOf(
                    Scale("Standart", 100, 85, 60, 40, 20),
                    Scale("Inferno", 100, 85, 60, 40, 20),
                    Scale("Preschool", 100, 60, 40, 20, 5)
                )

                initialScales.forEach { insertScale(it) }

                 */
    }

    init {
        GlobalScope.launch(Dispatchers.IO) {
            insertInitialScales()
        }
    }

    override suspend fun getAllScales(): List<Scale> = withContext(Dispatchers.IO) {
        return@withContext scaleDao.getAllScales()
    }

    override suspend fun removeScale(scale: Scale) {
        scaleDao.deleteScale(scale)
    }

    override suspend fun insertScale(scale: Scale) = withContext(Dispatchers.IO) {
        scaleDao.insertScale(scale)
    }

    override suspend fun addNewScale(
        name: String,
        procentOne: Int,
        procentTwo: Int,
        procentThree: Int,
        procentFour: Int,
        procentFive: Int
    ) {
        val newScale = Scale(
            name = name,
            procentOne = procentOne,
            procentTwo = procentTwo,
            procentThree = procentThree,
            procentFour = procentFour,
            procentFive = procentFive
        )
        insertScale(newScale)
    }

    override fun onViewCreated() {
        // Zkouška grafu
        val dataPoints = arrayOf(
            DataPoint(1.0, 10.0),
            DataPoint(2.0, 20.0),
            DataPoint(3.0, 30.0),
            DataPoint(4.0, 40.0),
            DataPoint(5.0, 15.0)
        )
        // You can use 'dataPoints' as needed
    }

    override fun onResetBtnClick() {
        // Clear the data in the graph
        //view?.showDataOnGraph(arrayOf())
    }
}