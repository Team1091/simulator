package com.team1091.shared.autonomous.commands

import com.team1091.shared.control.RobotComponents
import com.team1091.shared.math.Rotation
import com.team1091.shared.system.PositionSystem
import kotlin.math.abs

class TurnToAngle(val components: RobotComponents, val positionSystem: PositionSystem, val targetAngle: Rotation) : Command {

//    private val requiredTurnDistance: Double = Math.abs(turnDegrees.toDegrees() / 360.0) * (25 * Math.PI)
//    private val isTurnRight: Boolean = turnDegrees.toRadians() > 0

    override fun firstRun() {
        println("Turn Starting")
    }

    // https://robotic-controls.com/learn/programming/pd-feedback-control-introduction

//    val angle = 5.0

    override fun execute(dt: Double): Command? {

        // TODO:
        val toRotate = targetAngle - positionSystem.gyroscope.get()

//        if(abs(toRotate.toDegrees())<angle ){
            // your done
//            components.drive.arcadeDrive(0.0, 0.0)
//            return null
//        }else {
            components.drive.arcadeDrive(0.0, Math.signum(toRotate.toRadians()))
//        }

//        if(toRotate)
//
//        val ltix = components.leftEncoder.getDistance()
//        val rtix = components.rightEncoder.getDistance()
//
//        val difference = Math.abs(rtix - ltix) / 2.0 // ticks per degree
//
//        if (difference > requiredTurnDistance) {
//            // We have turned far enough, we are done
//            components.drive.arcadeDrive(0.0, 0.0)
//            return null
//
//        } else {
//            components.drive.arcadeDrive(0.0, if (isTurnRight) 1.0 else -1.0)
            return this
//        }

    }

    override fun cleanUp() {
        println("Turn Done")
        components.drive.arcadeDrive(0.0, 0.0)
    }

//    override fun getMessage(): String =
//            "Driving Forwards"


}