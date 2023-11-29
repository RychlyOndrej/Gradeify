package com.example.xmltest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xmltest.controller.Communication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface EditView : Communication {
    fun updateCardViewContent(option: Int)
}

class EditViewImp : Fragment(), EditView {
    private lateinit var controller: HomeController
    private lateinit var cardViewToFillEdit: CardView
    private lateinit var markDatabase: MarkDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_edit, container, false)
        cardViewToFillEdit = rootView.findViewById(R.id.cardViewToFillEditId)
        val scaleRepository: ScaleModel = ScaleModelImp(requireContext())
        markDatabase = MarkDatabase.getInstance(requireContext())
        controller = HomeControllerImp(scaleRepository, MarkModel(markDatabase.markDao()))
        showAllScales(rootView)
        return rootView
    }

    private fun showAllScales(rootView: View) {
        val scaleList = rootView.findViewById<RecyclerView>(R.id.scaleList)
        scaleList.layoutManager = LinearLayoutManager(requireContext())
        GlobalScope.launch {
            val scales = controller.getAllScales()
            scaleList.adapter = ScaleAdapter(scales)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? Communication)?.onOptionSelected(1)
    }

    override fun updateCardViewContent(option: Int) {
        when (option) {
            1 -> setCardViewContent(R.layout.marks_set_one_five)
            2 -> setCardViewContent(R.layout.marks_set_a_f)
            3 -> setCardViewContent(R.layout.marks_set_one_four)
            else -> setCardViewContent(R.layout.marks_set_one_five)
        }
    }

    override fun onOptionSelected(option: Int) {
        Log.d("Edit", "RadioButton clicked with option: $option")
        updateCardViewContent(option)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: Disconnect the view from the presenter to prevent memory leaks
        // presenter.detachView()
    }

    private fun setCardViewContent(layoutResId: Int) {
        val inflater = LayoutInflater.from(requireContext())
        val contentView = inflater.inflate(layoutResId, cardViewToFillEdit, false)
        cardViewToFillEdit.removeAllViews()
        cardViewToFillEdit.addView(contentView)

        val marks = listOf(
            R.id.markTwoProcentage to R.id.markOneBottomProcentage,
            R.id.markThreeProcentage to R.id.markTwoBottomProcentage,
            R.id.markFourProcentage to R.id.markThreeBottomProcentage,
            R.id.markFiveProcentage to R.id.markFourBottomProcentage
        )

        for (i in marks.indices) {
            val (editTextId, textViewId) = marks[i]
            val editText = contentView.findViewById<EditText>(editTextId)
            val textView = contentView.findViewById<TextView>(textViewId)

            setPercentageWatcher(editText, textView)
        }
    }

    private fun setPercentageWatcher(editText: EditText, bottomProcentTextView: TextView) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Not needed in this case
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed in this case
            }

            override fun afterTextChanged(s: Editable?) {
                val percentage = s?.toString()?.toDoubleOrNull() ?: 0.0
                bottomProcentTextView.text = String.format("%.0f%%", Math.max(0.0, percentage + 1.0))
            }
        })
    }
}