package com.team1091.sim

import com.team1091.shared.math.moveToward

class World(
        val fieldXSize: Double = 1200.0,
        val fieldYSize: Double = 600.0,
        val robots: Array<Robot>,
        val startTime: Long
) {

    var currentGameState: Period = Period.PREMATCH
    var elapsedSec: Long = 0

    // Runs the sim
    fun sim() {
        elapsedSec = (System.currentTimeMillis() - startTime) / 1000


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

                // velocity - need to add accelerations from the drive to the
                v += drive.linearAccel.toFloat()
                rv += drive.rotationalAccel.toFloat()

                // linear friction - limits velocity in any direction.  More friction in ways against the wheel
                v = moveToward(v, 0.0, 0.1)

                // rotational friction
                rv = moveToward(rv, 0.0, 0.01)

                // TODO: drifting?

                r += rv
                y += Math.sin(r) * v
                x += Math.cos(r) * v

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
    }
}