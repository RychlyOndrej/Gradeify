package com.usbapps.gradeify

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.usbapps.gradeify.controller.Communication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Rozhraní pro komunikaci mezi třídami
interface EditView : Communication {
    fun updateCardViewContent(option: Int)
}

// Implementace fragmentu EditView
class EditViewImp : Fragment(), EditView {
    private lateinit var controller: HomeController
    private lateinit var cardViewToFillEdit: CardView
    private lateinit var markDatabase: MarkDatabase

    // Define textWatcher as a property of the class
    private val textWatcher: TextWatcher by lazy {
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed in this case
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed in this case
            }

            override fun afterTextChanged(s: Editable?) {
                val percentage = s?.toString()?.toDoubleOrNull() ?: 0.0
                val bottomProcentTextView =
                    cardViewToFillEdit.findViewById<TextView>(R.id.markOneBottomProcentage)
                bottomProcentTextView?.text = String.format("%.0f%%", Math.max(0.0, percentage + 1.0))
            }
        }
    }

    // Metoda volaná při vytváření fragmentu
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflace layoutu pro tento fragment
        val rootView = inflater.inflate(R.layout.activity_edit, container, false)
        cardViewToFillEdit = rootView.findViewById(R.id.cardViewToFillEditId)

        // Inicializace modelu a controlleru
        val scaleRepository: ScaleModel = ScaleModelImp(requireContext())
        markDatabase = MarkDatabase.getInstance(requireContext())
        controller = HomeControllerImp(scaleRepository, MarkModel(markDatabase.markDao()))


        // Zobrazení všech skal ve fragmentu
        showAllScales(rootView)
        return rootView
    }

    // Metoda pro zobrazení všech skal
    private fun showAllScales(rootView: View) {
        val scaleList = rootView.findViewById<RecyclerView>(R.id.scaleList)
        scaleList.layoutManager = LinearLayoutManager(requireContext())

        // Spuštění asynchronní operace pro získání všech skal
        GlobalScope.launch {
            val scales = controller.getAllScales()
            scaleList.adapter = ScaleAdapter(scales)
        }
    }

    // Metoda volaná po vytvoření view
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Nastavení vybrané možnosti po vytvoření view
        (activity as? Communication)?.onOptionSelected(1)

        // Nastavení windowSoftInputMode pro zabránění automatického přizpůsobení displeje klávesnice
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    // Metoda pro aktualizaci obsahu CardView podle vybrané možnosti
    override fun updateCardViewContent(option: Int) {
        when (option) {
            1 -> setCardViewContent(R.layout.marks_set_one_five)
            2 -> setCardViewContent(R.layout.marks_set_a_f)
            3 -> setCardViewContent(R.layout.marks_set_one_four)
            else -> setCardViewContent(R.layout.marks_set_one_five)
        }
    }

    // Metoda volaná při výběru možnosti
    override fun onOptionSelected(option: Int) {
        updateCardViewContent(option)
    }

    // Metoda volaná při zničení view
    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: Odpojení view od presenteru k zabránění memory leaks
        // presenter.detachView()
    }

    // Metoda pro nastavení obsahu CardView podle layoutu
    private fun setCardViewContent(layoutResId: Int) {
        if (isAdded) {
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

            for ((editTextId, textViewId) in marks) {
                val editText = contentView.findViewById<EditText>(editTextId)
                val textView = contentView.findViewById<TextView>(textViewId)

                if (editText != null && textView != null) {
                    setPercentageWatcher(editText, textView)
                }
            }
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
