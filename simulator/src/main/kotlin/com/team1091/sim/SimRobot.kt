package com.team1091.sim

import com.team1091.shared.control.TeamRobot
import com.team1091.sim.components.SimDrive


class SimRobot(
        var x: Double, var y: Double, // position
        var v: Double, // linear velocity

        var r: Double,  // rotation, angular displacement
        var rv: Double, // angular velocity

        val xSize: Double,
        val ySize: Double,
//        val mass:Double,

        val teamRobot: TeamRobot,
        val drive: SimDrive   // we need to read the drive instructions out to replicate them
)