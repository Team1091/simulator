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
    private val world: World
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
                    SimDrive(20.0, 5.0),
                    SimEncoder(20.0),
                    SimEncoder(-20.0),
                    SimAccelerometer()
            )

            SimRobot(xPos, yPos, 0.0,
                    rotation, 0.0,
                    25.0, 30.0,
                    TeamRobotImpl(rc),
                    if (right) red else blue,
                    rc // These are needed to simulate its position.
            )
        }

        world = World(
                robots = robots
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

        world.stepSimulation(delta)
        render()
    }


    private fun render() {
        background(200f)
        pushMatrix()
        // shift from camera
        //translate(width.toFloat()/2f,height.toFloat()/2f)
        translate(20f, 20f)
//        translate((world.fieldXSize / 2.0).toFloat(), (world.fieldYSize / 2.0).toFloat())
        scale(1.75f)

        // draw everything
        fill(100f)
        rect(0f, 0f, world.fieldXSize.toFloat(), world.fieldYSize.toFloat())


        for (robot in world.robots) {
            pushMatrix() // Unfortunately, no one can be told what the matrix is.  You have to see it for yourself.
            translate(robot.x.toFloat(), robot.y.toFloat())
            rotate(robot.r.toFloat())
            translate(-robot.xSize.toFloat() / 2f, -robot.ySize.toFloat() / 2f)

            fill(robot.alliance.color)
            rect(0f, 0f, robot.xSize.toFloat(), robot.ySize.toFloat())
            popMatrix()
        }
        popMatrix()

        fill(0f)
        text("${world.currentGameState.name} - ${world.elapsedSec.toLong()}", 10f, 10f)
    }

}




