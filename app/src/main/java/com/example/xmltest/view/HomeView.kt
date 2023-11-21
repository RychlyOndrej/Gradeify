package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint

interface HomeView {
    // TODO: Doplnit potřebné metody pro komunikaci s UI.
    // TODO: Aktualizace grafu s poskytnutými daty.
    // TODO: Aktualizace TextView s poskytnutým obsahem.

    fun updateCardView(xmlResource: Int)
}

class HomeViewImp : Fragment(), HomeView {
    private lateinit var scaleModel: ScaleModel
    private lateinit var settingsModel: SettingsModel
    private lateinit var cardViewContainer: CardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_home, container, false)
        val textView = rootView.findViewById<TextView>(R.id.textView4)
        val graphView = rootView.findViewById<GraphView>(R.id.graph)
        val resetBtn: Button = rootView.findViewById(R.id.resetStatsBtn)

        scaleModel = ScaleModelImp(requireContext())
        settingsModel = SettingsModelImp(requireContext())

        cardViewContainer = rootView.findViewById(R.id.cardViewToFill)

        lifecycleScope.launchWhenStarted {
            settingsModel.getValueFromDataStore().collect { option ->
                updateCardView(getXmlResourceForOption(option))
            }
        }

        resetBtn.setOnClickListener { scaleModel.onResetBtnClick() }

        scaleModel.onViewCreated()

        return rootView
    }

    override fun updateCardView(xmlResource: Int) {
        val inflater = LayoutInflater.from(requireContext())
        val xmlLayout = inflater.inflate(xmlResource, cardViewContainer, false)
        cardViewContainer.removeAllViews()
        cardViewContainer.addView(xmlLayout)
    }

    private fun getXmlResourceForOption(option: Int): Int {
        return when (option) {
            1 -> R.layout.activity_home_marks_one_five
            2 -> R.layout.activity_home_marks_a_f
            3 -> R.layout.activity_home_marks_one_four
            else -> R.layout.activity_home_marks_one_five
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
