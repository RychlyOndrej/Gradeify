package com.example.xmltest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

interface EditView {
    fun getContext(): Context
}
class EditViewImp : Fragment(), EditView , CoroutineScope by MainScope(){
    private lateinit var controller: EditController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_edit, container, false)
        // Initialize the controller before using it
        val scaleModel: ScaleModel = ScaleModelImp(this)
        controller = EditControllerImp(scaleModel)
        showAllScales(rootView)
        return rootView
    }

    override fun getContext(): Context {
        return requireContext()
    }

    private fun showAllScales(rootView: View) {
        val scaleList = rootView.findViewById<RecyclerView>(R.id.scaleList) // Change ID to match your actual layout
        scaleList.layoutManager = LinearLayoutManager(requireContext())
        /*val scales = controller.getAllScales()
        scaleList.adapter = ScaleAdapter(scales)*/
    }
}

