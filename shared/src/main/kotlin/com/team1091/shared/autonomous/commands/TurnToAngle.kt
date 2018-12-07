package com.team1091.shared.autonomous.commands

import com.team1091.shared.control.RobotComponents
import com.team1091.shared.math.Rotation
import com.team1091.shared.system.PositionSystem

class TurnToAngle(val components: RobotComponents, val positionSystem: PositionSystem, val targetAngle: Rotation) : Command {

//    private val requiredTurnDistance: Double = Math.abs(turnDegrees.toDegrees() / 360.0) * (25 * Math.PI)
//    private val isTurnRight: Boolean = turnDegrees.toRadians() > 0

    override fun firstRun() {
        println("Turn Starting")
    }


    var lastPosition = 0.0
    var lastTime = 0L

    // https://robotic-controls.com/learn/programming/pd-feedback-control-introduction

    // TODO: we need to adjust these and provide a minimum speed that we can break out
    // Get There Fast - (low rise time)	Smaller Kp
    // Less Overshoot - Smaller Kp, Larger Kd
    // Less Vibration - Larger Kd

    val kP = 0.5
    val kD = 0.8

    override fun execute(dt: Double): Command? {

        val goal = targetAngle.toRadians()

        val position = positionSystem.gyroscope.get().toRadians()
        val now = System.currentTimeMillis() // store the current time in "now"

        // change in position over change in time
        val speed = (position - lastPosition) / (now - lastTime)

        // this measurement is now the previous one
        lastPosition = position;
        lastTime = now;

        val output = kP * (goal - position) - kD * speed

        components.drive.arcadeDrive(0.0, output)

        return this

    }

    override fun cleanUp() {
        println("Turn Done")
        components.drive.arcadeDrive(0.0, 0.0)
    }

}