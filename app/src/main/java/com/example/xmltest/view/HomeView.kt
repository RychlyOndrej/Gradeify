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
import java.text.DecimalFormat

interface HomeView : Communication {
    // TODO: Doplnit potřebné metody pro komunikaci s UI.
    fun updateCardViewContent(option: Int)
}

class HomeViewImp : Fragment(), HomeView {
    // Presenter pro komunikaci s modelem.
    private lateinit var presenterScaleModel: ScaleModel
    private lateinit var cardViewToFillHome: CardView
    private lateinit var presenterHomeController: HomeController
    private lateinit var rootView: View // Declare rootView at the class level

    private lateinit var averageTextView: TextView
    private lateinit var stdDevTextView: TextView
    private lateinit var medianTextView: TextView
    private lateinit var deleteLastMarkBtn: Button

    private lateinit var graphView: GraphView
    private lateinit var barGraphSeries: BarGraphSeries<DataPoint>

    private var currentLayoutResId: Int = 0

    // Inicializace UI prvků a presenteru.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inicializace UI prvků.
        rootView = inflater.inflate(R.layout.activity_home, container, false)
        val textView = rootView.findViewById<TextView>(R.id.textView4)
        val graphView = rootView.findViewById<GraphView>(R.id.graph)
        val resetBtn: Button = rootView.findViewById(R.id.resetStatsBtn)


        averageTextView = rootView.findViewById(R.id.Avarage_num)
        deleteLastMarkBtn = rootView.findViewById(R.id.deleteLastMarkBtn)
        stdDevTextView = rootView.findViewById(R.id.Std_num)
        medianTextView = rootView.findViewById(R.id.Median_num)

        presenterScaleModel = ScaleModelImp(requireContext())
        presenterHomeController = HomeControllerImp(presenterScaleModel, MarkModel(MarkDatabase.getInstance(requireContext()).markDao()))


        // Inicializujte BarGraphSeries s prázdnými daty
        barGraphSeries = BarGraphSeries(arrayOf(DataPoint(1.0, 0.0), DataPoint(2.0, 0.0),
            DataPoint(3.0, 0.0), DataPoint(4.0, 0.0),
            DataPoint(5.0, 0.0)))

        // Přidejte BarGraphSeries do GraphView
        graphView.addSeries(barGraphSeries)


        cardViewToFillHome = rootView.findViewById(R.id.cardViewToFillHomeId)
        // Inicializace presenteru.
        presenterScaleModel = ScaleModelImp(requireContext())
        // Nastavení posluchače události pro tlačítko resetBtn.
        resetBtn.setOnClickListener {
            if (isAdded) {
                presenterScaleModel.onResetBtnClick()
                presenterHomeController.clearMarksList()
                updateTableOfNumbers()
            }
        }
        // Volání presenteru pro nastavení počátečního stavu.
        presenterScaleModel.onViewCreated()

        presenterHomeController = HomeControllerImp(ScaleModelImp(requireContext()), MarkModel(MarkDatabase.getInstance(requireContext()).markDao()))


        currentLayoutResId = R.layout.activity_home_marks_one_five
        setCardViewContent(currentLayoutResId)



        // Přidání posluchačů kliknutí na tlačítka
        rootView.findViewById<CardView>(R.id.markOneBtn).setOnClickListener {
            presenterHomeController.handleMarkButtonClick(1)
            updateTableOfNumbers()
        }
        rootView.findViewById<CardView>(R.id.markTwoBtn).setOnClickListener {
            presenterHomeController.handleMarkButtonClick(2)
            updateTableOfNumbers()
        }
        rootView.findViewById<CardView>(R.id.markThreeBtn).setOnClickListener {
            presenterHomeController.handleMarkButtonClick(3)
            updateTableOfNumbers()
        }
        rootView.findViewById<CardView>(R.id.markFourBtn).setOnClickListener {
            presenterHomeController.handleMarkButtonClick(4)
            updateTableOfNumbers()
        }
        rootView.findViewById<CardView>(R.id.markFiveBtn).setOnClickListener {
            presenterHomeController.handleMarkButtonClick(5)
            updateTableOfNumbers()
        }
        return rootView
    }

    private fun updateTableOfNumbers() {
        val average = presenterHomeController.getAverageMark()
        val StandardDeviation = presenterHomeController.getStandardDeviation()
        val median = presenterHomeController.getMedian()
        //počet desetinných míst
        val decimalFormat = DecimalFormat("#.##")

        averageTextView.text = decimalFormat.format(average)
        stdDevTextView.text = decimalFormat.format(StandardDeviation)
        medianTextView.text = decimalFormat.format(median)
    }

    //Funkce na vložení v počátku rozdílných XML kódů marks do activity_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? Communication)?.onOptionSelected(1)
        deleteLastMarkBtn.setOnClickListener {
            presenterHomeController.removeLastMark()
            updateTableOfNumbers()
        }
    }

    // Implementation of the missing method from HomeView interface
    override fun updateCardViewContent(option: Int) {
        if (isAdded) { // Kontrola, zda je fragment připojen
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
                // Add more cases as needed
                else -> {
                    currentLayoutResId = R.layout.activity_home_marks_a_f
                    setCardViewContent(currentLayoutResId)
                }
            }
        }
    }

    //Funkce na nastavení xml částy karty a její přepsání
    private fun setCardViewContent(layoutResId: Int) {
        val inflater = LayoutInflater.from(requireContext())
        val contentView = inflater.inflate(layoutResId, cardViewToFillHome, false)
        cardViewToFillHome.removeAllViews()
        cardViewToFillHome.addView(contentView)

        // Add listeners for buttons in the new layout
        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        // Assuming you have buttons in the new layout with these IDs
        cardViewToFillHome.findViewById<CardView>(R.id.markOneBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(1)
            updateTableOfNumbers()
        }
        cardViewToFillHome.findViewById<CardView>(R.id.markTwoBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(2)
            updateTableOfNumbers()
        }
        cardViewToFillHome.findViewById<CardView>(R.id.markThreeBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(3)
            updateTableOfNumbers()
        }
        cardViewToFillHome.findViewById<CardView>(R.id.markFourBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(4)
            updateTableOfNumbers()
        }
        cardViewToFillHome.findViewById<CardView>(R.id.markFiveBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(5)
            updateTableOfNumbers()
        }
        // Add listeners for other buttons as needed
    }



    override fun onOptionSelected(option: Int) {
        Log.d("HomeViewImp", "RadioButton clicked with option: $option")
        if (isAdded) { // Kontrola, zda je fragment připojen
            updateCardViewContent(option)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: Disconnect the view from the presenter to prevent memory leaks
        // presenter.detachView()
    }
}
