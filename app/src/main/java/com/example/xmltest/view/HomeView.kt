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
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint

interface HomeView: Communication  {
    // TODO: Doplnit potřebné metody pro komunikaci s UI.
    fun updateCardViewContent(option: Int)
}

class HomeViewImp : Fragment(), HomeView {
    // Presenter pro komunikaci s modelem.
    private lateinit var presenter: ScaleModel
    private lateinit var cardViewToFillHome: CardView

    // Inicializace UI prvků a presenteru.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inicializace UI prvků.
        val rootView = inflater.inflate(R.layout.activity_home, container, false)
        val textView = rootView.findViewById<TextView>(R.id.textView4)
        val graphView = rootView.findViewById<GraphView>(R.id.graph)
        val resetBtn: Button = rootView.findViewById(R.id.resetStatsBtn)

        cardViewToFillHome = rootView.findViewById(R.id.cardViewToFillHomeId)
        // Inicializace presenteru.
        presenter = ScaleModelImp(requireContext())
        // Nastavení posluchače události pro tlačítko resetBtn.
        resetBtn.setOnClickListener { presenter.onResetBtnClick() }
        // Volání presenteru pro nastavení počátečního stavu.
        presenter.onViewCreated()
        return rootView
    }

    // Zobrazení dat na grafu.
    fun showDataOnGraph(dataPoints: Array<DataPoint>) {
        val series = BarGraphSeries(dataPoints)
        // TODO: Add logic to update the graph
    }

    fun showTextViewContent(content: String) {
        // TODO: Add logic to update TextView
    }

    //Funkce na vložení v počátku rozdílných XML kódů marks do activity_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? Communication)?.onOptionSelected(1)
    }

    //Funkce na nastavení xml částy karty a její přepsání
    private fun setCardViewContent(layoutResId: Int) {
        val inflater = LayoutInflater.from(requireContext())
        val contentView = inflater.inflate(layoutResId, cardViewToFillHome, false)
        cardViewToFillHome.removeAllViews()
        cardViewToFillHome.addView(contentView)
    }

    //Logika pro nastavení daného xml dle radioBtn
    override fun updateCardViewContent(option: Int) {
        when (option) {
            1 -> setCardViewContent(R.layout.activity_home_marks_one_five)
            2 -> setCardViewContent(R.layout.activity_home_marks_a_f)
            3 -> setCardViewContent(R.layout.activity_home_marks_one_four)
            // Add more cases as needed
            else -> setCardViewContent(R.layout.activity_home_marks_a_f)
        }
    }

    //Pro přenos INT z settingsview
    override fun onOptionSelected(option: Int) {
        Log.d("HomViewImp", "RadioButton clicked with option: $option")
        updateCardViewContent(option)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: Disconnect the view from the presenter to prevent memory leaks
        // presenter.detachView()
    }
}