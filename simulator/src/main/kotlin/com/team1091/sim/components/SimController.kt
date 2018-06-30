package com.team1091.sim.components

import com.studiohartman.jamepad.ControllerManager
import com.team1091.shared.components.IGameController

class SimController(
        private val controllers: ControllerManager,
        private val id: Int
) : IGameController {

    private fun state() = controllers.getState(id)

    override fun getLeftX(): Double = state().leftStickX.toDouble()
    override fun getLeftY(): Double = state().leftStickY.toDouble()

    override fun getRightX(): Double = state().rightStickX.toDouble()
    override fun getRightY(): Double = state().rightStickY.toDouble()

    override fun pressedA(): Boolean = state().a
    override fun pressedB(): Boolean = state().b
    override fun pressedX(): Boolean = state().x
    override fun pressedY(): Boolean = state().y


}