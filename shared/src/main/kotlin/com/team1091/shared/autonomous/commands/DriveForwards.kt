package com.team1091.shared.autonomous.commands

import com.team1091.shared.control.RobotComponents

class DriveForwards(val components: RobotComponents) : Command {


    override fun execute(dt: Double): Command {
        components.drive.arcadeDrive(1.0, 0.0)
        return this
    }

    override fun getMessage(): String =
            "Driving Forwards"


}