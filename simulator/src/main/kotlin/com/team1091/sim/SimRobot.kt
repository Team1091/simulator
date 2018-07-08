package com.team1091.sim

import com.team1091.shared.control.TeamRobot
import com.team1091.sim.components.SimDrive
import com.team1091.sim.components.SimEncoder

class SimRobot(
        var x: Double,
        var y: Double, // position
        var v: Double, // linear velocity

        var r: Double,  // rotation, angular displacement
        var rv: Double, // angular velocity

        val xSize: Double,
        val ySize: Double,

        val teamRobot: TeamRobot,

        // val mass:Double,

        // we need to read the drive instructions out to simulate them.
        // Would probably be more efficient to get the robotComponents here,
        // but its wrapped in an interface and we need the concrete class
        val drive: SimDrive,
        val lEncode: SimEncoder,
        val rEncode: SimEncoder
)