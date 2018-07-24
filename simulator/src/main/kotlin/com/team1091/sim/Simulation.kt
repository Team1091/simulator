package com.team1091.sim

import com.studiohartman.jamepad.ControllerManager
import com.team1091.shared.control.RobotComponents
import com.team1091.shared.control.TeamRobotImpl
import com.team1091.sim.components.SimAccelerometer
import com.team1091.sim.components.SimController
import com.team1091.sim.components.SimDrive
import com.team1091.sim.components.SimEncoder
import processing.core.PApplet


fun main(args: Array<String>) {
    PApplet.main("com.team1091.sim.Simulator")
}


class Simulator : PApplet() {
    private val simWorld: SimWorld
    private val controllers = ControllerManager()

    init {
        controllers.initSDLGamepad()

        val reverse = Math.PI
        val red = Alliance("red", color(255f, 0f, 0f))
        val blue = Alliance("blue", color(0f, 0f, 255f))

        val robots = Array(6) { id ->

            val right = id >= 3
            val xPos = if (right) (650.0 - 15.0) else 15.0
            val yPos = 50.0 + 100.0 * (id % 3)
            val rotation = if (right) reverse else 0.0

            val rc = RobotComponents(
                    SimController(controllers, id),
                    SimDrive(100000.0, 1000000.0),
                    SimEncoder(20.0),
                    SimEncoder(-20.0),
                    SimAccelerometer()
            )

            SimRobot(null, xPos, yPos,
                    rotation,
                    25.0, 30.0,
                    TeamRobotImpl(rc),
                    if (right) red else blue,
                    rc // These are needed to simulate its position.
            )
        }
650
        320
        simWorld = SimWorld(
                robots = robots,
                obstacles = arrayOf(
                        Obstacle(-25f, 160f, 50f, 320f ), // left
                        Obstacle(675f, 160f, 50f, 320f ), // right
                        Obstacle(325f, -25f, 650f, 50f ), // top
                        Obstacle(325f, 345f, 650f, 50f ) // bottom
                )
        )
    }

    override fun settings() {
        size(1200, 600)
    }

    override fun setup() {
        fill(120f, 50f, 240f)
    }


    var lastTime = 0
    override fun draw() {

        val now = millis()
        val delta = (now - lastTime) / 1000.0
        lastTime = now

        simWorld.stepSimulation(delta)
        render()
    }


    private fun render() {
        background(200f)
        pushMatrix()
        // shift from camera
        translate(20f, 20f)
        scale(1.75f)

        // draw everything
        fill(100f)
        rect(0f, 0f, simWorld.fieldXSize.toFloat(), simWorld.fieldYSize.toFloat())

        for (robot in simWorld.robots) {
            val body = robot.body
            if (body != null) {
                pushMatrix() // Unfortunately, no one can be told what the matrix is.  You have to see it for yourself.
                translate(body.position.x, body.position.y)
                rotate(body.angle)
                translate(-robot.xSize.toFloat() / 2f, -robot.ySize.toFloat() / 2f)

                fill(robot.alliance.color)
                rect(0f, 0f, robot.xSize.toFloat(), robot.ySize.toFloat())
                popMatrix()
            }
        }
        popMatrix()

        fill(0f)
        text("${simWorld.currentGameState.name} - ${simWorld.elapsedSec.toLong()}", 10f, 10f)
    }

}




