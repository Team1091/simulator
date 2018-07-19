package com.team1091.sim

import com.team1091.shared.control.RobotComponents
import com.team1091.shared.control.TeamRobot

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
        val alliance: Alliance,
        // we need to read the drive instructions out to simulate them.
        val rc: RobotComponents
)