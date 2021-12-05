package com.team1091.sim.components

import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.PovDirection
import com.team1091.shared.components.IGameController


class SimController(
        private val controller:Controller
) : IGameController {

    override fun getLeftX(): Double = controller.getAxis(AXIS_LEFT_X).toDouble()
    override fun getLeftY(): Double = controller.getAxis(AXIS_LEFT_Y).toDouble()

    override fun getRightX(): Double = controller.getAxis(AXIS_RIGHT_X).toDouble()
    override fun getRightY(): Double = controller.getAxis(AXIS_RIGHT_Y).toDouble()

    override fun pressedA(): Boolean = controller.getButton(BUTTON_A)
    override fun pressedB(): Boolean = controller.getButton(BUTTON_B)
    override fun pressedX(): Boolean = controller.getButton(BUTTON_X)
    override fun pressedY(): Boolean = controller.getButton(BUTTON_Y)

    override fun getLeftTrigger(): Double = controller.getAxis(AXIS_LEFT_TRIGGER).toDouble()
    override fun getRightTrigger(): Double = controller.getAxis(AXIS_RIGHT_TRIGGER).toDouble()

    override fun pressedLeftBumper(): Boolean =controller.getButton(BUTTON_LB)
    override fun pressedRightBumper(): Boolean = controller.getButton(BUTTON_RB)

    override fun getLeftStick(): Boolean = controller.getButton(BUTTON_L3)
    override fun getRightStick(): Boolean = controller.getButton(BUTTON_R3)

    override fun getStart(): Boolean = controller.getButton(BUTTON_START)
    override fun getBack(): Boolean = controller.getButton(BUTTON_BACK)

//  Works in sim, but not on robot right now
//    override fun getDPadUp(): Boolean = state().dpadUp
//    override fun getDPadDown(): Boolean = state().dpadDown
//    override fun getDPadLeft(): Boolean = state().dpadLeft
//    override fun getDPadRight(): Boolean = state().dpadRight


    val BUTTON_X = 2
    val BUTTON_Y = 3
    val BUTTON_A = 0
    val BUTTON_B = 1
    val BUTTON_BACK = 6
    val BUTTON_START = 7
    val BUTTON_DPAD_UP = PovDirection.north
    val BUTTON_DPAD_DOWN = PovDirection.south
    val BUTTON_DPAD_RIGHT = PovDirection.east
    val BUTTON_DPAD_LEFT = PovDirection.west
    val BUTTON_LB = 4
    val BUTTON_L3 = 8
    val BUTTON_RB = 5
    val BUTTON_R3 = 9
    val AXIS_LEFT_X = 1 //-1 is left | +1 is right

    val AXIS_LEFT_Y = 0 //-1 is up | +1 is down

    val AXIS_LEFT_TRIGGER = 4 //value 0 to 1f

    val AXIS_RIGHT_X = 3 //-1 is left | +1 is right

    val AXIS_RIGHT_Y = 2 //-1 is up | +1 is down

    val AXIS_RIGHT_TRIGGER = 4 //value 0 to -1f


}