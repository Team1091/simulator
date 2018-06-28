package com.team1091.sim

import com.studiohartman.jamepad.ControllerManager
import com.team1091.shared.components.GameController

class SimController(
        private val controllers: ControllerManager,
        private val id: Int
) : GameController {

    override fun getLeftX(): Double {
        return controllers.getState(id).leftStickX.toDouble()
    }

    override fun getLeftY(): Double {
        return controllers.getState(id).leftStickY.toDouble()
    }

}