package com.team1091.shared.control

import com.team1091.shared.game.StartingPos

interface TeamRobot {
    fun robotInit(startingPos: StartingPos)

    fun autonomousInit()

    fun autonomousPeriodic()

    fun teleopInit()

    fun teleopPeriodic()

    fun disabledInit()

    fun disabledPeriodic()

    fun testInit()

    fun testPeriodic()
}