package com.team1091.shared.components

interface Drive {
    fun arcadeDrive(linear: Double, rotation: Double)
}

interface GameController {

    fun getLeftX(): Double
    fun getLeftY(): Double

}