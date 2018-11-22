package com.team1091.shared.system

import com.team1091.shared.components.IAccelerometer
import com.team1091.shared.components.IGyroscope
import kotlin.math.cos
import kotlin.math.sin

class PositionSystem(
        val accelerometer: IAccelerometer,
        val gyroscope: IGyroscope,

        private var xP: Double,
        private var yP: Double,

        private var xV: Double,
        private var yV: Double,

        private var angle: Double
) {

    fun integrate(dt: Double) {

        // gyroscope already handles integration
        angle = gyroscope.get()

        val aX = accelerometer.getX()
        val aY = accelerometer.getY()

        // rotate instantaneous accelerations
        val aXr = (cos(angle) * aX) - (sin(angle) * aY)
        val aYr = (sin(angle) * aX) + (cos(angle) * aY)

        // integrate velocity
        xV += (aXr * dt)
        yV += (aYr * dt)

        // integrate position
        xP += (xV * dt)
        yP += (yV * dt)

    }

    fun getPos(): Position = Position(xP, yP, angle)

}

class Position(val x: Double, val y: Double, val r: Double)