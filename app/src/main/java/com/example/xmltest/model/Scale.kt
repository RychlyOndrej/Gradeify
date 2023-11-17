package com.example.xmltest

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Scale(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
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
