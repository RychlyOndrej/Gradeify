package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface EditView {
    //udelej si to :)
}

class EditViewImp : Fragment(), EditView {
    private lateinit var controller: EditController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_edit, container, false)
        // Initialize the controller before using it
        val scaleModel = ScaleModel()
        controller = EditControllerImp(scaleModel)
        showAllScales(rootView)
        return rootView
    }

    private fun showAllScales(rootView: View) {
        val scaleList = rootView.findViewById<RecyclerView>(R.id.scaleList) // Change ID to match your actual layout
        scaleList.layoutManager = LinearLayoutManager(requireContext())
        val scales = controller.getAllScales()
        scaleList.adapter = ScaleAdapter(scales)
    }
}

