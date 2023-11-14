package com.example.xmltest

// Define your controller interface
interface EditController {
    fun getAllScales(): List<Scale>
}

// Implement the controller
class EditControllerImp(private val scaleModelImp: ScaleModelImp) : EditController {
    override fun getAllScales(): List<Scale> {
        return scaleModelImp.getAllScales()
    }
}
