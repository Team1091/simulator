package com.team1091.sim.components

import com.team1091.shared.components.IEncoder
import com.team1091.shared.math.Vec2d

class SimEncoder(offset: Vec2d) : IEncoder {

    private var ticks = 0;

    override fun reset() {
        ticks = 0
    }
    // need placement of wheel, then we can calculate rotation?

    override fun get(): Int {
        return ticks
    }

}
