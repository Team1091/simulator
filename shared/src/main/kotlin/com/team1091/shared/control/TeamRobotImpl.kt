package com.team1091.shared.control

import com.team1091.shared.autonomous.commands.DriveForwards
import com.team1091.shared.components.Drive
import com.team1091.shared.components.GameController
import com.team1091.shared.system.AutonomousSystem

// This controls our robot in both the sim and real life
class TeamRobotImpl(
        val gameController: GameController,
        val drive: Drive

) : TeamRobot {

    private val autonomousSystem: AutonomousSystem = AutonomousSystem()

    override fun robotInit() {

    }

    override fun autonomousInit() {
        autonomousSystem.init(DriveForwards(drive))

    }

    override fun autonomousPeriodic() {
        autonomousSystem.drive(getTime())
    }

    override fun teleopInit() {

    }


    override fun teleopPeriodic() {
        drive.arcadeDrive(gameController.getLeftY(), gameController.getLeftX())
    }

    override fun disabledInit() {

    }

    override fun disabledPeriodic() {

    }

    override fun testInit() {

    }

    override fun testPeriodic() {

    }


    private var lastFrameTime = System.nanoTime()

    private fun getTime(): Double {
        val currentTime = System.nanoTime()
        val dt = (currentTime.toDouble() - lastFrameTime.toDouble()) / 1000000000.0
        lastFrameTime = currentTime
        return dt
    }


}