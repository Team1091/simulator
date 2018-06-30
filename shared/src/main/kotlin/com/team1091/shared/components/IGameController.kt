package com.team1091.shared.components

interface IGameController {

    fun getLeftX(): Double
    fun getLeftY(): Double

    fun getRightX(): Double
    fun getRightY(): Double

    fun pressedA(): Boolean
    fun pressedB(): Boolean
    fun pressedX(): Boolean
    fun pressedY(): Boolean

    // left trigger, right trigger
    // left bumper, right bumper
    // left stick, right stick
    // start
    // select

}