package com.example.xmltest

// Define your controller interface
interface EditController {
    suspend fun getAllScales(context: Context): List<Scale>

}

// Implement the controller
class EditControllerImp(private val scaleModelImp: ScaleModelImp) : EditController {
    override suspend fun getAllScales(context: Context): List<Scale> {
        return scaleModelImp.getAllScales()
    }
}
