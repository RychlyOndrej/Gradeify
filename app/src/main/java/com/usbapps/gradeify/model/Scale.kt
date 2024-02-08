package com.usbapps.gradeify

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entita reprezentující škálu v databázi pomocí Room
@Entity
data class Scale(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // Primární klíč s možností automatického generování hodnot
    val name: String, // Název škály
    val procentOne: Int, // Procentuální hodnota pro první známku
    val procentTwo: Int, // Procentuální hodnota pro druhou známku
    val procentThree: Int, // Procentuální hodnota pro třetí známku
    val procentFour: Int, // Procentuální hodnota pro čtvrtou známku
    val procentFive: Int // Procentuální hodnota pro pátou známku
) {
    // Alternativní konstruktor pro vytvoření instance bez nutnosti zadávat primární klíč
    constructor(
        name: String,
        procentOne: Int,
        procentTwo: Int,
        procentThree: Int,
        procentFour: Int,
        procentFive: Int
    ) : this(
        0,
        name,
        procentOne,
        procentTwo,
        procentThree,
        procentFour,
        procentFive
    )
}
