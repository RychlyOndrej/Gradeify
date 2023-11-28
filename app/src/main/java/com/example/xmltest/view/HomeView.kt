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
    private lateinit var graphView: GraphView
    private lateinit var barGraphSeries: BarGraphSeries<DataPoint>
    private var currentLayoutResId: Int = 0

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
                updateTableOfNumbers()
            }
        }

        rootView.findViewById<CardView>(R.id.markOneBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(1)
            updateTableOfNumbers()
        }
        rootView.findViewById<CardView>(R.id.markTwoBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(2)
            updateTableOfNumbers()
        }
        rootView.findViewById<CardView>(R.id.markThreeBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(3)
            updateTableOfNumbers()
        }
        rootView.findViewById<CardView>(R.id.markFourBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(4)
            updateTableOfNumbers()
        }
        rootView.findViewById<CardView>(R.id.markFiveBtn)?.setOnClickListener {
            presenterHomeController.handleMarkButtonClick(5)
            updateTableOfNumbers()
        }
        updateTableOfNumbers()
        return rootView
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? Communication)?.onOptionSelected(1)
        deleteLastMarkBtn.setOnClickListener {
            presenterHomeController.removeLastMark()
            updateTableOfNumbers()
        }
    }

    override fun updateCardViewContent(option: Int) {
        if (isAdded) {
            //na update tabulky protože jinde se neprovede až po načtení db
            updateTableOfNumbers()
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