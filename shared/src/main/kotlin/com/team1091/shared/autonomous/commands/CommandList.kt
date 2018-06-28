package com.team1091.shared.autonomous.commands

import java.util.*

class CommandList : Command {

    private val commands: ArrayList<Command>

    constructor(vararg commands: Command) {
        this.commands = ArrayList(Arrays.asList(*commands))
    }

    constructor(commandList: ArrayList<Command>) {
        this.commands = commandList
    }

    override fun execute(dt: Double): Command? {

        if (commands.isEmpty()) {
            return null
        }

        // do the first one, if it's done remove it
        val first = commands.first()
        val next = first.execute(dt)

        if (next == null) {
            // Current command is done, go to the next
            if (commands.size == 1)
                return null // List done

            commands.removeAt(0)
        } else if (next !== first) {
            // Replace current command
            commands[0] = next
        }
        return this
    }

    override fun getMessage(): String {
        return commands.first().getMessage()
    }
}
