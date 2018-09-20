package com.team1091.shared.control

import com.team1091.shared.autonomous.commands.CommandList
import com.team1091.shared.autonomous.commands.DriveForwards
import com.team1091.shared.autonomous.commands.Turn
import com.team1091.shared.game.StartingPos
import com.team1091.shared.system.AutonomousSystem
import com.team1091.shared.system.PositionSystem


// This controls our robot in both the sim and real life
class TeamRobotImpl(
        val components: RobotComponents
) : TeamRobot {


    private val autonomousSystem = AutonomousSystem()

    public lateinit var positionSystem: PositionSystem

    override fun robotInit(startingPos: StartingPos) {
        positionSystem = PositionSystem(
                components.accelerometer,
                components.gyroscope,
                startingPos.pos.x,
                startingPos.pos.y,
                0.0,
                0.0,
                startingPos.rotation
        )
    }

    override fun autonomousInit() {
        autonomousSystem.init(
                CommandList(
                        DriveForwards(components, 20.0),
                        Turn(components, 90.0),
                        DriveForwards(components, 20.0),
                        Turn(components, -90.0)
                )
        )

    }

    override fun autonomousPeriodic() {
        val dt = getTime()
        autonomousSystem.drive(dt)
        positionSystem.integrate(dt)
    }

    override fun teleopInit() {

    }


    override fun teleopPeriodic() {
        components.drive.arcadeDrive(
                components.gameController.getLeftY(),
                components.gameController.getLeftX()
        )
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