package com.team1091.sim

import com.team1091.shared.control.RobotComponents
import com.team1091.shared.control.TeamRobotImpl
import com.team1091.shared.game.Alliance
import com.team1091.sim.components.DummyController
import com.team1091.sim.components.SimAccelerometer
import com.team1091.sim.components.SimDrive
import com.team1091.sim.components.SimEncoder
import com.team1091.sim.phys.SimRobot
import org.junit.Test

class SimTest {

    @Test
    fun testThatWorldWorks() {

        val reverse = Math.PI.toFloat()
        val robots = Array(6) { id ->

            val right = id >= 3
            val xPos = if (right) 1100f else 100f
            val yPos = 100f + 200f * (id % 3)
            val rotation = if (right) reverse else 0f

            val lEncoder = SimEncoder(20.0)
            val rEncoder = SimEncoder(-20.0)
            val drive = SimDrive(20.0, 5.0)

            val rc = RobotComponents(
                    DummyController(),
                    drive,
                    lEncoder,
                    rEncoder,
                    SimAccelerometer()
            )

            SimRobot(xPos, yPos, rotation,
                    25f, 30f,
                    TeamRobotImpl(rc),
                    Alliance("Test", 0),
                    // These are needed to simulate its position.  We may just want to read them from the rc though
                    rc
            )
        }

        val world = SimWorld(
                robots = robots,
                gamePieces = arrayOf(),
                obstacles = arrayOf()
        )

        val stepsPerSec = 10
        for (t in 0 until Period.AUTONOMOUS.seconds * stepsPerSec) {
            world.stepSimulation(1.0 / stepsPerSec)
        }


        // TODO: render final positions? render path?  Could take the pos every frame and draw to png
    }

}