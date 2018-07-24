package com.team1091.sim

import com.team1091.sim.components.SimDrive
import com.team1091.sim.components.SimEncoder
import org.jbox2d.collision.shapes.PolygonShape
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.BodyDef
import org.jbox2d.dynamics.BodyType
import org.jbox2d.dynamics.FixtureDef
import org.jbox2d.dynamics.World

//import org.jb

class Obstacle(val x: Float, val y: Float, val w: Float, val h: Float)

class SimWorld(
        val fieldXSize: Double = 650.0,
        val fieldYSize: Double = 320.0,
        val robots: Array<SimRobot>,
        obstacles: Array<Obstacle>
) {

    // What is all this physics?

    // world -> magic box that holds physics bodies

    // body -> things that have physics and move around the screen, empty. Velocity, pos, etc
    // shape -> has geometry, gets attached to body.  Collisions Rectangle/polygon/circle
    // fixture -> Attaches shapes to body.
    // joint - Connects 2 bodies.

    val world: World

    init {
        // create physics shit
        world = World(Vec2()) // in a world with no gravity

        // TODO build walls
        obstacles.forEach {
            val wall = BodyDef()
            wall.type = BodyType.STATIC
            val body = world.createBody(wall)

            body.position.x = it.x
            body.position.y = it.y

            val shape = PolygonShape();
            shape.setAsBox(it.w / 2f, it.h / 2f)

            body.createFixture(shape, 1f)
        }

        robots.map {

            val bodyDef = BodyDef()
            bodyDef.position.x = it.x.toFloat()
            bodyDef.position.y = it.y.toFloat()
            bodyDef.angle = it.r.toFloat()
            bodyDef.type = BodyType.DYNAMIC

            bodyDef.linearDamping = 0.8f
            bodyDef.angularDamping = 0.8f
            bodyDef.bullet = true // fast moving

            val body = world.createBody(bodyDef)

            val shape = PolygonShape()
            shape.setAsBox(it.xSize.toFloat() / 2f, it.ySize.toFloat() / 2f)

            val fixtureDef = FixtureDef()
            fixtureDef.shape = shape
            fixtureDef.friction = 0.3f
            fixtureDef.restitution = 0.5f
            fixtureDef.density = 1.0f // mass is calculated from this * area

            body.createFixture(fixtureDef)

            it.body = body
        }

    }


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


        // BOX2d goes here
        for (robot in robots) {
            when (currentGameState) {
                Period.PREMATCH -> Unit
                Period.AUTONOMOUS -> robot.teamRobot.autonomousPeriodic()
                Period.TELEOP -> robot.teamRobot.teleopPeriodic()
                Period.DISABLED -> robot.teamRobot.disabledPeriodic()
            }
        }

        // TODO: add each robot's drive's acceleration

        for (robot in robots) {
            // velocity - need to add accelerations from the drive to the velocity
            //                v += drive.linearAccel * dt
            //                rv += drive.rotationalAccel * dt

            val body = robot.body
            if (body != null) {
                val drive = (robot.rc.drive as SimDrive)
                drive.applyForce(body)
            }
        }



        world.step(dt.toFloat(), 5, 3)

        // TODO: set encoder values?
        for (robot in robots) {
            with(robot) {

                //
                val lEncode = (rc.leftEncoder as SimEncoder)
                val rEncode = (rc.rightEncoder as SimEncoder)

                val v = body?.linearVelocity?.length() ?: 0f
                val rv = body?.angularVelocity ?: 0f

                lEncode.rotation += (v + rv * lEncode.rotDist) * dt
                rEncode.rotation += (v + rv * rEncode.rotDist) * dt
            }
        }

//
//                // limits velocity in any direction.  More friction in ways against the wheel
//                v = moveToward(v, 0.0, 0.5 * dt)
//                rv = moveToward(rv, 0.0, 0.5 * dt)
//
//                // TODO: drifting?
//

//
//                r += rv * dt
//                y += Math.sin(r) * v * dt
//                x += Math.cos(r) * v * dt
//
//                // TODO: ramming walls
//                if (x < 0) {
//                    x = 0.0
//                    v = 0.0
//                    rv = 0.0
//                } else if (x > fieldXSize) {
//                    x = fieldXSize
//                    v = 0.0
//                    rv = 0.0
//                }
//                if (y < 0) {
//                    y = 0.0
//                    v = 0.0
//                    rv = 0.0
//                } else if (y > fieldYSize) {
//                    y = fieldYSize
//                    v = 0.0
//                    rv = 0.0
//                }

//            }

    }
    //println("r:${robots.first().rEncode.get()} l:${robots.first().lEncode.get()}")

}