package com.example.xmltest

// Define your controller interface
interface EditController {
    fun getAllScales(): List<Scale>
}

// Implement the controller
class EditControllerImp(private val scaleModel: ScaleModel) : EditController {
    override fun getAllScales(): List<Scale> {
        return scaleModel.getAllScales()
    }
}
