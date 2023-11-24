package com.example.xmltest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xmltest.controller.Communication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface EditView: Communication {
    // Remove the getContext method
    fun updateCardViewContent(option: Int)
}

class EditViewImp : Fragment(), EditView {
    private lateinit var controller: HomeController
    private lateinit var cardViewToFillEdit: CardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_edit, container, false)
        cardViewToFillEdit = rootView.findViewById(R.id.cardViewToFillEditId)
        // Inicializace controlleru před použitím
        val scaleRepository: ScaleModel = ScaleModelImp(requireContext())
        controller = HomeControllerImp(scaleRepository)
        showAllScales(rootView)
        return rootView
    }

    // funkce na zobrazení všech škál
    private fun showAllScales(rootView: View) {
        val scaleList = rootView.findViewById<RecyclerView>(R.id.scaleList) // Change ID to match your actual layout
        scaleList.layoutManager = LinearLayoutManager(requireContext())
        GlobalScope.launch {
            val scales = controller.getAllScales()
            scaleList.adapter = ScaleAdapter(scales)
        }
    }

    //Funkce na vložení v počátku rozdílných XML kódů marks do activity_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       (activity as? Communication)?.onOptionSelected(1)
    }

    //Funkce na nastavení xml částy karty a její přepsání
    private fun setCardViewContent(layoutResId: Int) {
        val inflater = LayoutInflater.from(requireContext())
        val contentView = inflater.inflate(layoutResId, cardViewToFillEdit, false)
        cardViewToFillEdit.removeAllViews()
        cardViewToFillEdit.addView(contentView)
    }

    //Logika pro nastavení daného xml dle radioBtn
    override fun updateCardViewContent(option: Int) {
        when (option) {
            1 -> setCardViewContent(R.layout.marks_set_one_five)
            2 -> setCardViewContent(R.layout.marks_set_a_f)
            3 -> setCardViewContent(R.layout.marks_set_one_four)
            // Add more cases as needed
            else -> setCardViewContent(R.layout.marks_set_one_five)
        }
    }

    //Pro přenos INT z settingsview
    override fun onOptionSelected(option: Int) {
        Log.d("Edit", "RadioButton clicked with option: $option")
        updateCardViewContent(option)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: Disconnect the view from the presenter to prevent memory leaks
        // presenter.detachView()
    }
}