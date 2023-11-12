package com.example.xmltest

import android.media.MediaSession2Service.MediaNotification
import androidx.activity.ComponentActivity

interface MainController{
    fun maxScore(maxPoints: Int)
    fun procentArray(pole: Array<Int>)
    fun getAllScales(): List<Scale>
    fun createNewScale()
    fun getMarks(marks: Array<Int>)
    fun getActiveScale()
    fun setActiveScale()
}

class MainControllerImp(private val view: MainView, private val model: ScaleModel): ComponentActivity(), MainController{
    private var activeScaleName = "Standart"
    private var maxScore = 0
    private var procentArray: Array<Int>? = null
    private var createNewScaleName: String? = null


    override fun maxScore(maxPoints: Int){

    }
    override fun procentArray(pole: Array<Int>){

    }
    override fun getAllScales(): List<Scale>{
        return(model.getAllScales())
    }
    override fun createNewScale(){

    }
    override fun getMarks(marks: Array<Int>){

    }
    override fun getActiveScale(){

    }
    override fun setActiveScale(){

    }

}