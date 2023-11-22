package com.example.xmltest

import androidx.datastore.core.DataStore



// Rozhraní definující DataStore pro ukládání vybrané možnosti.
interface SetDataStore {
    val selectedOption: DataStore<Int>
}