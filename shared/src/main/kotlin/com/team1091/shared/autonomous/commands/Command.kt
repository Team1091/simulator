package com.team1091.shared.autonomous.commands

interface Command {
    fun execute(dt: Double): Command?
    fun getMessage(): String

    // firstRun() //gets called before first run
    // cleanup() // gets called after last
    // getElapsedTime()
}