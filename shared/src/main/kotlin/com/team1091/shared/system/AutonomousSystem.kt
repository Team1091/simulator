package com.team1091.shared.system

import com.team1091.shared.autonomous.commands.Command

class AutonomousSystem {
    private var command: Command? = null

    fun init(command: Command) {
        this.command = command
    }

    fun drive(dt: Double) {

        if (command == null) {

            log("Completed")
            return  // Done with autonomous
        }

        log(command!!.getMessage())
        command = command!!.execute(dt)
    }

    private fun log(message: String) {
        // TODO: need to pass in logger too
//        SmartDashboard.putString("Autonomous", message)
    }
}
