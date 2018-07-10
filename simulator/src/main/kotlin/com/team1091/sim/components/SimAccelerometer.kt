package com.team1091.sim.components

import com.team1091.shared.components.IAccelerometer


class SimAccelerometer(
        private var x: Double,
        private var y: Double,
        private var z: Double
) : IAccelerometer {

    override fun getX(): Double = x
    override fun getY(): Double = y
    override fun getZ(): Double = z

}