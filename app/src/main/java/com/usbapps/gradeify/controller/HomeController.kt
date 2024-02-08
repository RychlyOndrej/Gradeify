package com.usbapps.gradeify

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

// Rozhraní pro definici metod, které řídí logiku pro interakci s uživatelským rozhraním
interface HomeController {
    fun handleMarkButtonClick(markValue: Int)
    fun getAverageMark(): Double
    fun removeLastMark()
    suspend fun getAllScales(): List<Scale>
    fun getMedian(): Double
    fun getStandardDeviation(): Double
    fun clearMarksList()
    fun getLastMark(): Double?
    fun getMarksList(): List<Double>
    fun removeAllFives()
}

// Implementace rozhraní HomeController
class HomeControllerImp(
    private val model: ScaleModel,
    private val markModel: MarkModel,
) : ComponentActivity(), HomeController {

    // Seznam hodnocení uživatele
    private var marksList: MutableList<Double> = mutableListOf()

    // Inicializace při vytvoření instance
    init {
        // Načtení hodnocení z databáze při startu aplikace
        lifecycleScope.launch {
            marksList = markModel.getAllMarks().map { it.markValue.toDouble() }.toMutableList()
        }
    }

    // Metoda pro obsluhu kliknutí na tlačítko s hodnocením
    override fun handleMarkButtonClick(markValue: Int) {
        // Přidání hodnocení do seznamu a aktualizace databáze
        marksList.add(markValue.toDouble())
        lifecycleScope.launch {
            markModel.insertMark(MarkEntity(markValue = markValue))
        }
    }

    // Metoda pro získání seznamu všech hodnocení
    override fun getMarksList(): List<Double> {
        return marksList.toList()
    }

    // Metoda pro výpočet průměru hodnocení
    override fun getAverageMark(): Double {
        return marksList.average()
    }

    // Metoda pro výpočet mediánu hodnocení
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

    // Metoda pro výpočet směrodatné odchylky hodnocení
    override fun getStandardDeviation(): Double {
        val sumOfSquares = marksList.map { (it - getAverageMark()).pow(2) }.sum()

        val variance = sumOfSquares / marksList.size

        return sqrt(variance)
    }

    // Metoda pro odstranění posledního hodnocení
    override fun removeLastMark() {
        if (marksList.isNotEmpty()) {
            marksList.removeAt(marksList.size - 1)
            lifecycleScope.launch {
                markModel.deleteLastData()
            }
        }
    }

    // Metoda pro vymazání všech hodnocení
    override fun clearMarksList() {
        marksList.clear()
        lifecycleScope.launch {
            markModel.deleteAllMarks()
        }
    }

    // Metoda pro získání všech škál (scales)
    override suspend fun getAllScales(): List<Scale> {
        return model.getAllScales()
    }

    override fun getLastMark(): Double?{
        if (marksList.isNotEmpty()) {
            val lastMark = marksList.get(marksList.size - 1)
            Log.d("LastMark", "Poslední známka: $lastMark")
            return lastMark
        }
        else{
            return null
        }
    }

    override fun removeAllFives() {
        marksList.removeAll { it == 5.0 }
    }
}
