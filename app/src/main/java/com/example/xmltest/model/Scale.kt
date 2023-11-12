package com.example.xmltest.model

data class Scale(
    val name: String,
    val procentOne: Int,
    val procentTwo: Int,
    val procentThree: Int,
    val procentFour: Int,
    val procentFive: Int,

){
    constructor(name: String,procentOne: Int,procentTwo: Int,procentThree: Int,procentFour: Int,procentFive: Int) : this(name, procentOne, procentTwo, procentThree, procentFour, procentFive)
}