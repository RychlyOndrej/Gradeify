package com.example.xmltest

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

interface HomeController {
    fun handleMarkButtonClick(markValue: Int)
    fun getAverageMark(): Double
    fun removeLastMark()
    suspend fun getAllScales(): List<Scale>
    fun getMedian(): Double
    fun getStandardDeviation(): Double
    fun clearMarksList()
    fun getMarksList(): List<Double>
}

/*Todo zprovoznit dtabázi, room. a recylclerview add and delete
*  Floating btn, - přidat škálu na první místo a dát ji jako vybranou
*   vyřešit editaci jmen škál - u jména mít ikonu tušky(editace) - jméno by mělo jít editovat
*  pluskem do módu editace - přidá tlačítko, uživatel pak přes edit zadává jméno.
*/
class HomeControllerImp(
    private val model: ScaleModel,
    private val markModel: MarkModel
) : ComponentActivity(), HomeController {

    private var marksList: MutableList<Double> = mutableListOf()

    init {
        lifecycleScope.launch {
            marksList = markModel.getAllMarks().map { it.markValue.toDouble() }.toMutableList()
        }
    }

    override fun handleMarkButtonClick(markValue: Int) {
        marksList.add(markValue.toDouble())
        lifecycleScope.launch {
            markModel.insertMark(MarkEntity(markValue = markValue))
        }
    }

    override fun getMarksList(): List<Double> {
        return marksList.toList()
    }

    override fun getAverageMark(): Double {
        return marksList.average()
    }

    override fun getMedian(): Double {
        val sortedMarks = marksList.sorted()

        return if (sortedMarks.isEmpty()) {
            0.0
        } else if (sortedMarks.size % 2 == 0) {
            val middle1 = sortedMarks[(sortedMarks.size / 2) - 1].toDouble()
            val middle2 = sortedMarks[sortedMarks.size / 2].toDouble()
            (middle1 + middle2) / 2.0
        } else {
            sortedMarks[sortedMarks.size / 2].toDouble()
        }
    }

    override fun getStandardDeviation(): Double {
        val sumOfSquares = marksList.map { (it - getAverageMark()).pow(2) }.sum()

        val variance = sumOfSquares / marksList.size

        return sqrt(variance)
    }

    override fun removeLastMark() {
        if (marksList.isNotEmpty()) {
            marksList.removeAt(marksList.size - 1)
            lifecycleScope.launch {
                markModel.deleteLastData()
            }
        }
    }

    override fun clearMarksList() {
        marksList.clear()
        lifecycleScope.launch {
            markModel.deleteAllMarks()
        }
    }

    override suspend fun getAllScales(): List<Scale> {
        return model.getAllScales()
    }
}

