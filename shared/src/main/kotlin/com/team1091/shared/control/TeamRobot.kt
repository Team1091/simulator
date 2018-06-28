package com.team1091.shared.control

interface TeamRobot {
    fun robotInit()

    fun autonomousInit()

    fun autonomousPeriodic()

    fun teleopInit()

    fun teleopPeriodic()

    fun disabledInit()

    fun disabledPeriodic()

    fun testInit()

    fun testPeriodic()
}