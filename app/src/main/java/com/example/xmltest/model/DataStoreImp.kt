package com.example.xmltest

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.createDataStore

// Implementace DataStore pro ukládání vybrané možnosti.
class DataStoreImp (context: Context): SetDataStore{
    override val selectedOption: DataStore<Int> = context.createDataStore(
        name = "settings_pref"
    )
}