package com.example.xmltest

data class Scale(
    val name: String,
    val procentOne: Int,
    val procentTwo: Int,
    val procentThree: Int,
    val procentFour: Int,
    val procentFive: Int
) {
    constructor(
        name: String,
        procentOne: Int,
        procentTwo: Int,
        procentThree: Int,
        procentFour: Int,
        procentFive: Int,
        additionalParam: Int
    ) : this(
        name,
        procentOne,
        procentTwo,
        procentThree,
        procentFour,
        procentFive
    ) {
        // Další inicializační kód, pokud je potřeba
    }
}
