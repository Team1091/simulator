package com.team1091.shared.autonomous.commands

import com.team1091.shared.control.RobotComponents

class DriveForwards(val components: RobotComponents, val distance: Double) : Command {


    override fun firstRun() {
        components.encoderL.reset()
    }

    override fun execute(dt: Double): Command? {

        if (components.encoderL.get() < distance) {
            components.drive.arcadeDrive(1.0, 0.0)
            return this
        }
        return null

    }

    override fun cleanUp() {
        println("Cleaning")
        components.drive.arcadeDrive(0.0, 0.0)
    }

//    override fun getMessage(): String =
//            "Driving Forwards"


}