package com.team1091.sim

import com.team1091.shared.math.moveToward
import com.team1091.sim.components.SimDrive
import com.team1091.sim.components.SimEncoder

class World(
        val fieldXSize: Double = 650.0,
        val fieldYSize: Double = 320.0,
        val robots: Array<SimRobot>
) {

    // Next step is probably to use an actual physics engine
    // http://www.gamefromscratch.com/post/2014/09/25/LibGDX-LibGDX-Tutorial-13-Physics-with-Box2D-Part-3-Collisions.aspx
    // http://www.iforce2d.net/b2dtut/top-down-car
    // http://thisiswhatiknowabout.blogspot.com/2011/12/jbox2d-tutorial.html

    var currentGameState: Period = Period.PREMATCH
    var elapsedSec: Double = 0.0

    // Runs the sim
    fun stepSimulation(dt: Double = 0.1) {
        elapsedSec += dt

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

                val drive = (rc.drive as SimDrive)
                val lEncode = (rc.leftEncoder as SimEncoder)
                val rEncode = (rc.rightEncoder as SimEncoder)

                // velocity - need to add accelerations from the drive to the velocity
                v += drive.linearAccel * dt
                rv += drive.rotationalAccel * dt

                // limits velocity in any direction.  More friction in ways against the wheel
                v = moveToward(v, 0.0, 0.5 * dt)
                rv = moveToward(rv, 0.0, 0.5 * dt)

                // TODO: drifting?

                lEncode.rotation += (v + rv * lEncode.rotDist) * dt
                rEncode.rotation += (v + rv * rEncode.rotDist) * dt

                r += rv * dt
                y += Math.sin(r) * v * dt
                x += Math.cos(r) * v * dt

                // TODO: ramming walls
                if (x < 0) {
                    x = 0.0
                    v = 0.0
                    rv = 0.0
                } else if (x > fieldXSize) {
                    x = fieldXSize
                    v = 0.0
                    rv = 0.0
                }
                if (y < 0) {
                    y = 0.0
                    v = 0.0
                    rv = 0.0
                } else if (y > fieldYSize) {
                    y = fieldYSize
                    v = 0.0
                    rv = 0.0
                }

            }

        }
        //println("r:${robots.first().rEncode.get()} l:${robots.first().lEncode.get()}")

    }
}