package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface EditView {
    // Remove the getContext method
}

class EditViewImp : Fragment(), EditView {
    private lateinit var controller: HomeController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_edit, container, false)
        // Initialize the controller before using it
        val scaleRepository: ScaleModel = ScaleModelImp(requireContext())
        val homeView: HomeView = HomeViewImp()  // Zde vytvořte instanci HomeView, pokud ji ještě nemáte
        val settingsModel: SettingsModel = SettingsModelImp(requireContext())
        controller = HomeControllerImp(scaleRepository, homeView, settingsModel)
        showAllScales(rootView)
        return rootView
    }

    private fun showAllScales(rootView: View) {
        val scaleList = rootView.findViewById<RecyclerView>(R.id.scaleList)
        scaleList.layoutManager = LinearLayoutManager(requireContext())
        viewLifecycleOwner.lifecycleScope.launch {
            val scales = controller.getAllScales(requireContext())
            scaleList.adapter = ScaleAdapter(scales)
        }
    }

}