package com.team1091.sim

import com.studiohartman.jamepad.ControllerManager
import com.team1091.shared.control.RobotComponents
import com.team1091.shared.control.TeamRobotImpl
import com.team1091.sim.components.SimController
import com.team1091.sim.components.SimDrive
import com.team1091.sim.components.SimEncoder
import processing.core.PApplet


fun main(args: Array<String>) {
    PApplet.main("com.team1091.sim.Simulator")
}


class Simulator : PApplet() {
    private val world: World
    private val controllers = ControllerManager()

    init {
        controllers.initSDLGamepad()

        val reverse = Math.PI

        val robots = Array(6) { id ->

            val right = id >= 3
            val xPos = if (right) 1100.0 else 100.0
            val yPos = 100.0 + 200.0 * (id % 3)
            val rotation = if (right) reverse else 0.0

            val lEncoder = SimEncoder(20.0)
            val rEncoder = SimEncoder(-20.0)
            val drive = SimDrive()

            val rc = RobotComponents(
                    SimController(controllers, id),
                    drive,
                    lEncoder,
                    rEncoder
            )

            SimRobot(xPos, yPos, 0.0,
                    rotation, 0.0,
                    25.0, 30.0,
                    TeamRobotImpl(rc),
                    drive,
                    lEncoder,
                    rEncoder
            )
        }

        world = World(
                robots = robots,
                startTime = System.currentTimeMillis()
        )
    }

    override fun settings() {
        size(1200, 600)
    }

    override fun setup() {
        fill(120f, 50f, 240f)
    }

    override fun keyPressed() {
        when (key) {
            'w' -> {
                world.robots[0].v += 0.5f
            }
            's' -> {
                world.robots[0].v -= 0.5f
            }
            'a' -> {
                world.robots[0].rv -= 0.1f
            }
            'd' -> {
                world.robots[0].rv += 0.1f
            }
        }
    }

    var lastTime = 0
    override fun draw() {
        val now = millis()
        val delta = ( now - lastTime)/1000.0
        lastTime = now

        world.stepSimulation(delta)
        render()
    }


    private fun render() {
        background(200f)
        for (robot in world.robots) {
            pushMatrix() // Unfortunately, no one can be told what the matrix is.  You have to see it for yourself.
            translate(robot.x.toFloat(), robot.y.toFloat())
            rotate(robot.r.toFloat())
            translate(-robot.xSize.toFloat() / 2f, -robot.ySize.toFloat() / 2f)
            rect(0f, 0f, robot.xSize.toFloat(), robot.ySize.toFloat())
            popMatrix()
        }

        text("${world.currentGameState.name} - ${world.elapsedSec.toLong()}", 10f, 10f)
    }

}




