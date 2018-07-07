package com.team1091.sim.components

import com.team1091.shared.components.IEncoder

class SimEncoder(val rotDist: Double) : IEncoder {

    var distance: Double = 0.0

    override fun reset() {
        distance = 0.0
    }

    override fun get(): Int {
        return distance.toInt()
    }

}
