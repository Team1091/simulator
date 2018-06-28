package com.team1091.shared.autonomous.commands

import com.team1091.shared.components.Drive

class DriveForwards(val drive: Drive) : Command {


    override fun execute(dt: Double): Command {
        drive.arcadeDrive(1.0, 0.0)
        return this
    }

    override fun getMessage(): String =
            "Driving Forwards"


}