package com.team1091.sim

import com.team1091.shared.math.moveToward

class World(
        val fieldXSize: Double = 650.0,
        val fieldYSize: Double = 320.0,
        val robots: Array<SimRobot>,
        val startTime: Long
) {

    var currentGameState: Period = Period.PREMATCH
    var elapsedSec: Long = 0

    // Runs the sim
    fun stepSimulation() {
        elapsedSec = (System.currentTimeMillis() - startTime) / 1000
        val dt = 0.1

        when (currentGameState) {
            Period.PREMATCH -> {
                currentGameState = Period.AUTONOMOUS
                robots.forEach { it.teamRobot.autonomousInit() }
            }
            Period.AUTONOMOUS -> {
                if (elapsedSec >= Period.AUTONOMOUS.seconds) {
                    currentGameState = Period.TELEOP
                    robots.forEach { it.teamRobot.teleopInit() }
                }
            }
            Period.TELEOP -> {
                if (elapsedSec >= Period.TELEOP.seconds) {
                    currentGameState = Period.DISABLED
                    robots.forEach { it.teamRobot.disabledInit() }
                }
            }
            Period.DISABLED -> return
        }


        for (robot in robots) {
            with(robot) {
                when (currentGameState) {
                    Period.PREMATCH -> Unit
                    Period.AUTONOMOUS -> teamRobot.autonomousPeriodic()
                    Period.TELEOP -> teamRobot.teleopPeriodic()
                    Period.DISABLED -> teamRobot.disabledPeriodic()
                }

                // velocity - need to add accelerations from the drive to the velocity
                v += drive.linearAccel * dt
                rv += drive.rotationalAccel * dt

                // limits velocity in any direction.  More friction in ways against the wheel
                v = moveToward(v, 0.0, 0.5 * dt)
                rv = moveToward(rv, 0.0, 0.5 * dt)

                // TODO: drifting?
                // add to encoders
                lEncode.rotation += (v + rv * lEncode.rotDist) * dt
                rEncode.rotation += (v + rv * rEncode.rotDist) * dt

                r += rv * dt
                y += Math.sin(r) * v * dt
                x += Math.cos(r) * v * dt

                // TODO: ramming walls
                if (x < 0) {
                    x = 0.0
                    v = 0.0
                } else if (x > fieldXSize) {
                    x = fieldXSize
                    v = 0.0
                }
                if (y < 0) {
                    y = 0.0
                } else if (y > fieldYSize) {
                    y = fieldYSize
                    v = 0.0
                }
//                x = clamp(x, 0.0, fieldXSize)
//                y = clamp(y, 0.0, fieldYSize)

            }

        }
        //println("r:${robots.first().rEncode.get()} l:${robots.first().lEncode.get()}")

    }
}