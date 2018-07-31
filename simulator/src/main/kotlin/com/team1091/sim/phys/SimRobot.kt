package com.team1091.sim.phys

import com.team1091.shared.control.RobotComponents
import com.team1091.shared.control.TeamRobot
import com.team1091.sim.Alliance
import org.jbox2d.dynamics.Body

class SimRobot(
        // Starting pos
        var x: Float,
        var y: Float, // position
        var r: Float,  // rotation, angular displacement

        val xSize: Float,
        val ySize: Float,

        val teamRobot: TeamRobot,

        // val mass:Double,
        val alliance: Alliance,
        // we need to read the drive instructions out to simulate them.
        val rc: RobotComponents
) {
    lateinit var body: Body
}


