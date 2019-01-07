package com.team1091.sim

import com.team1091.shared.control.RobotComponents
import com.team1091.shared.control.TeamRobotImpl
import com.team1091.shared.game.StartingPos
import com.team1091.shared.math.degrees
import com.team1091.sim.components.DummyController
import com.team1091.sim.components.SimAccelerometer
import com.team1091.sim.components.SimDrive
import com.team1091.sim.components.SimEncoder
import com.team1091.sim.components.SimGyroscope
import com.team1091.sim.phys.SimRobot
import org.junit.Test
import kotlin.math.abs

class SimTest {

    @Test
    fun testThatWorldWorks() {

        val robots = Array(6) { id ->

            val lEncoder = SimEncoder(20.0)
            val rEncoder = SimEncoder(-20.0)
            val drive = SimDrive(20.0, 5.0)

            val rc = RobotComponents(
                    DummyController(),
                    drive,
                    lEncoder,
                    rEncoder,
                    SimAccelerometer(),
                    SimGyroscope()
            )

            SimRobot(StartingPos.values()[id],
                    25f, 30f,
                    TeamRobotImpl(rc),
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


    @Test
    fun simulateRotation() {

        val test = {
            val lEncoder = SimEncoder(20.0)
            val rEncoder = SimEncoder(-20.0)
            val drive = SimDrive(20.0, 5.0)

            val rc = RobotComponents(
                    DummyController(),
                    drive,
                    lEncoder,
                    rEncoder,
                    SimAccelerometer(),
                    SimGyroscope()
            )
            val robots = arrayOf(
                    SimRobot(StartingPos.BLUE_1,
                            25f, 30f,
                            TeamRobotImpl(rc),
                            rc
                    )
            )

            val world = SimWorld(
                    robots = robots,
                    gamePieces = arrayOf(),
                    obstacles = arrayOf()
            )

            val stepsPerSec = 10
            for (t in 0 until Period.AUTONOMOUS.seconds * stepsPerSec) {
                world.stepSimulation(1.0 / stepsPerSec)
                val robot = robots.first()

                val angleVel = abs(robot.body.angularVelocity)
                val angle = abs(robot.body.angle.toDouble() - 90.degrees.toRadians())

                if (angleVel < 0.1 && angle < 0.1) {
                    println("done")
                }else{
                    println ("${world.currentGameState} ${angleVel} ${angle}")
                }

            }

            Period.AUTONOMOUS.seconds * stepsPerSec
        }

        println(test())


    }
}