package com.team1091.shared.autonomous.commands

class Or : Command {

    private val commands: List<Command>

    constructor(vararg commands: Command) {
        this.commands = commands.toMutableList()
    }

    constructor(commandList: ArrayList<Command>) {
        this.commands = commandList
    }

    override fun execute(dt: Double): Command? {

        if (commands.isEmpty()) {
            return null
        }

        // do them all, until one returns null.  Right now this doesn't clean up after itself
        for (command in commands) {
            command.execute(dt) ?: return null
        }
        return this
    }


    override fun getMessage(): String {
        return commands.first().getMessage()
    }
}
