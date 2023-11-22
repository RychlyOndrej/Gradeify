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
    // TODO: Doplnit potřebné metody pro komunikaci s UI.
}

class HomeViewImp : Fragment(), HomeView {
    // Presenter pro komunikaci s modelem.
    private lateinit var presenter: ScaleModel

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
        // TODO: Aktualizace grafu s poskytnutými daty.
        val series = BarGraphSeries(dataPoints)
    }

    // Zobrazení obsahu na TextView.
    fun showTextViewContent(content: String) {
        // TODO: Aktualizace TextView s poskytnutým obsahem.
        //textView.text = content
    }

    // Odpojení view od presenteru při zničení fragmentu.
    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: Odpojení view od presenteru pro zabránění úniku paměti.
        //presenter.detachView()
    }
}