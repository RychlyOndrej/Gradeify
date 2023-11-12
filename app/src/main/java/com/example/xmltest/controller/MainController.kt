package com.example.xmltest.controller


interface MainController{
    fun maxScore() :Int
    fun procentArray(pole: Array<Int>)
    fun getAllScales(): List<Scale>
    fun createNewScale()
    fun getMarks(marks: Array<Int>)
    fun getActiveScale()
}


class MainController(){
}