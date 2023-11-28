package com.example.xmltest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.xmltest.controller.Communication
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

interface HomeView : Communication {
    fun updateCardViewContent(option: Int)
}

class HomeViewImp : Fragment(), HomeView {
    private lateinit var presenterScaleModel: ScaleModel
    private lateinit var cardViewToFillHome: CardView
    private lateinit var presenterHomeController: HomeController
    private lateinit var rootView: View
    private lateinit var averageTextView: TextView
    private lateinit var stdDevTextView: TextView
    private lateinit var medianTextView: TextView
    private lateinit var deleteLastMarkBtn: Button
    private var currentLayoutResId: Int = 0

    private lateinit var barChart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.activity_home, container, false)
        averageTextView = rootView.findViewById(R.id.Avarage_num)
        deleteLastMarkBtn = rootView.findViewById(R.id.deleteLastMarkBtn)
        stdDevTextView = rootView.findViewById(R.id.Std_num)
        medianTextView = rootView.findViewById(R.id.Median_num)
        barChart = rootView.findViewById(R.id.chart)


        presenterScaleModel = ScaleModelImp(requireContext())
        presenterHomeController =
            HomeControllerImp(presenterScaleModel, MarkModel(MarkDatabase.getInstance(requireContext()).markDao()))


        currentLayoutResId = R.layout.activity_home_marks_one_five
        // Initialize cardViewToFillHome before calling setCardViewContent
        cardViewToFillHome = rootView.findViewById(R.id.cardViewToFillHomeId)
        setCardViewContent(currentLayoutResId)


        // Correctly initialize resetBtn
        val resetBtn: Button = rootView.findViewById(R.id.resetStatsBtn)
        resetBtn.setOnClickListener {
            if (isAdded) {
                presenterScaleModel.onResetBtnClick()
                presenterHomeController.clearMarksList()
                updateStatistics()
            }
        }

        rootView.findViewById<CardView>(R.id.markOneBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(1)
            updateStatistics()
        }
        rootView.findViewById<CardView>(R.id.markTwoBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(2)
            updateStatistics()
        }
        rootView.findViewById<CardView>(R.id.markThreeBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(3)
            updateStatistics()
        }
        rootView.findViewById<CardView>(R.id.markFourBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(4)
            updateStatistics()
        }
        rootView.findViewById<CardView>(R.id.markFiveBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(5)
            updateStatistics()
        }
        updateStatistics()
        return rootView
    }

    private fun updateStatistics(){
        updateTableOfNumbers()
        updateBarChart()
    }

    private fun updateTableOfNumbers() {
        val average = presenterHomeController.getAverageMark()
        val StandardDeviation = presenterHomeController.getStandardDeviation()
        val median = presenterHomeController.getMedian()
        val decimalFormat = DecimalFormat("#.##")

        averageTextView.text = decimalFormat.format(average)
        stdDevTextView.text = decimalFormat.format(StandardDeviation)
        medianTextView.text = decimalFormat.format(median)
    }

    private fun updateBarChart() {
        val marksFrequency = presenterHomeController.getMarksList().groupingBy { it.toInt() }.eachCount()

        val entries = mutableListOf<BarEntry>()

        // Přidání hodnot do datové sady a nastavení etiket pro osu X
        for (i in 1..5) {
            val frequency = marksFrequency[i] ?: 0
            entries.add(BarEntry(i.toFloat(), frequency.toFloat()))
        }

        val dataSet = BarDataSet(entries, null) // Nastavení popisku na null

        // Definice barev pro jednotlivé sloupce
        val colors = intArrayOf(
            R.color.color_mark_1,
            R.color.color_mark_2,
            R.color.color_mark_3,
            R.color.color_mark_4,
            R.color.color_mark_5
        )

        // Nastavení barev pro jednotlivé sloupce
        dataSet.colors = colors.map { requireContext().getColor(it) }

        val data = BarData(dataSet)

        val barChart = rootView.findViewById<BarChart>(R.id.chart)
        barChart.data = data

        // Vypnutí automatického generování etiket pro osu X
        barChart.xAxis.setDrawLabels(false)
        barChart.xAxis.setDrawAxisLine(false)

        val xAxis: XAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        // Nastavení vlastních etiket pro osu X
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return value.toInt().toString()
            }
        }

        // Nastavení formátu pro osu Y na celočíselné hodnoty
        val yAxis = barChart.axisLeft
        yAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return value.toInt().toString()
            }
        }

        // Skrytí popisku pro tyto barvy
        for (i in 0 until dataSet.entryCount) {
            data.getDataSetByIndex(0).setDrawValues(false)
        }

        barChart.invalidate()
    }

    private class MarkAxisValueFormatter : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            // Omezení hodnot na 1 až 5
            return if (value in 1.0f..5.0f) {
                value.toInt().toString()
            } else {
                "" // Prázdný řetězec pro neplatné hodnoty
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? Communication)?.onOptionSelected(1)
        deleteLastMarkBtn.setOnClickListener {
            presenterHomeController.removeLastMark()
            updateStatistics()
        }
    }

    override fun updateCardViewContent(option: Int) {
        if (isAdded) {
            //na update tabulky protože jinde se neprovede až po načtení db
            updateStatistics()
            when (option) {
                1 -> {
                    currentLayoutResId = R.layout.activity_home_marks_one_five
                    setCardViewContent(currentLayoutResId)
                }
                2 -> {
                    currentLayoutResId = R.layout.activity_home_marks_a_f
                    setCardViewContent(currentLayoutResId)
                }
                3 -> {
                    currentLayoutResId = R.layout.activity_home_marks_one_four
                    setCardViewContent(currentLayoutResId)
                }
                else -> {
                    currentLayoutResId = R.layout.activity_home_marks_a_f
                    setCardViewContent(currentLayoutResId)
                }
            }
        }
    }

    private fun setCardViewContent(layoutResId: Int) {
        val inflater = LayoutInflater.from(requireContext())
        val contentView = inflater.inflate(layoutResId, cardViewToFillHome, false)
        cardViewToFillHome.removeAllViews()
        cardViewToFillHome.addView(contentView)
        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        cardViewToFillHome.findViewById<CardView>(R.id.markOneBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(1)
            updateStatistics()
        }
        cardViewToFillHome.findViewById<CardView>(R.id.markTwoBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(2)
            updateStatistics()
        }
        cardViewToFillHome.findViewById<CardView>(R.id.markThreeBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(3)
            updateStatistics()
        }
        cardViewToFillHome.findViewById<CardView>(R.id.markFourBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(4)
            updateStatistics()
        }
        cardViewToFillHome.findViewById<CardView>(R.id.markFiveBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(5)
            updateStatistics()
        }
    }

    override fun onOptionSelected(option: Int) {
        Log.d("HomeViewImp", "RadioButton clicked with option: $option")
        if (isAdded) {
            updateCardViewContent(option)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: Disconnect the view from the presenter to prevent memory leaks
    }
}