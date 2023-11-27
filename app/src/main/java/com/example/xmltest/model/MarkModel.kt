package com.example.xmltest

import android.util.Log

class MarkModel(private val markDao: MarkDao) {

    suspend fun insertMark(markEntity: MarkEntity) {
        Log.d("Ulož", "Uložení do modelu: $markEntity")
        markDao.insertMark(markEntity)
    }

    suspend fun getAllMarks(): List<MarkEntity> {
        return markDao.getAllMarks()
    }

    suspend fun deleteLastData() {
        markDao.deleteLastData()
    }

    suspend fun deleteAllMarks() {
        markDao.deleteAllMarks()
    }
}