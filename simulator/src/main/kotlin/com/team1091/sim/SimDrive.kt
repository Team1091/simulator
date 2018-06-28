package com.team1091.sim

import com.team1091.shared.components.Drive

class SimDrive : Drive {
    var linearAccel: Double = 0.0
    var rotationalAccel: Double = 0.0

    override fun arcadeDrive(linear: Double, rotation: Double) {
        this.linearAccel = linear
        this.rotationalAccel = rotation
    }

}