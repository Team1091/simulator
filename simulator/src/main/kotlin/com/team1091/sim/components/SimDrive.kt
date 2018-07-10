package com.team1091.sim.components

import com.team1091.shared.components.IDrive

class SimDrive(val linearForce: Double, val rotationForce: Double) : IDrive {

    var linearAccel: Double = 0.0
    var rotationalAccel: Double = 0.0

    override fun arcadeDrive(linear: Double, rotation: Double) {
        this.linearAccel = linearForce * linear
        this.rotationalAccel = rotationForce * rotation
    }

}