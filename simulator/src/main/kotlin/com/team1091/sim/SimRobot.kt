package com.team1091.sim

import com.team1091.sim.components.SimEncoder


class SimRobot(
        var x: kotlin.Double, var y: kotlin.Double, // position
        var v: kotlin.Double, // linear velocity

        var r: kotlin.Double,  // rotation, angular displacement
        var rv: kotlin.Double, // angular velocity

        val xSize: kotlin.Double,
        val ySize: kotlin.Double,
//        val mass:Double,

        val teamRobot: com.team1091.shared.control.TeamRobot,
        val drive: com.team1091.sim.components.SimDrive,   // we need to read the drive instructions out to replicate them, val lEncod: com.team1091.sim.components.SimEncoder, val rEncod: com.team1091.sim.components.SimEncoder){}, lEncod: com.team1091.sim.components.SimEncoder, rEncod: com.team1091.sim.components.SimEncoder){}
        val lEncode: SimEncoder,
        val rEncode: SimEncoder
)