package com.example.xmltest

/*
data class Scale(
    val name: String,
    val procentOne: Int,
    val procentTwo: Int,
    val procentThree: Int,
    val procentFour: Int,
    val procentFive: Int,)
*/

interface ScaleModel{
    fun getAllScales(): List<Scale>
    fun removeScale(scale: Scale)
    fun editScale(scale: Scale)
}

class ScaleModelImp: ScaleModel{
    private val scales = ArrayList<Scale>()

    init {
        scales.add(Scale("Standart",100,85,60,40,20))
        scales.add(Scale("Inferno",100,90,80,70,60))
        scales.add(Scale("Pre-school",100,80,60,30,10))
    }

    override fun getAllScales(): List<Scale> {
        return scales.toList()
    }

    override fun removeScale(scale: Scale) {
        scales.remove(scale)
    }

    override fun editScale(scale: Scale) {
        TODO("Not yet implemented")
    }
}