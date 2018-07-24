package com.team1091.sim

import com.team1091.shared.control.RobotComponents
import com.team1091.shared.control.TeamRobot
import org.jbox2d.dynamics.Body

class SimRobot(
        var body: Body?, // physics

        // Starting pos
        var x: Double,
        var y: Double, // position
        var r: Double,  // rotation, angular displacement

        val xSize: Double,
        val ySize: Double,

        val teamRobot: TeamRobot,

        // val mass:Double,
        val alliance: Alliance,
        // we need to read the drive instructions out to simulate them.
        val rc: RobotComponents
)