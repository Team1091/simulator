package com.team1091.shared.game

import com.team1091.shared.math.Vec2d

enum class StartingPos(
        val alliance: Alliance,
        val pos: Vec2d,
        val rotation: Double
) {

    RED_1(Alliance.RED, Vec2d(15.0, 75.0), 0.0),
    RED_2(Alliance.RED, Vec2d(15.0, 175.0), 0.0),
    RED_3(Alliance.RED, Vec2d(15.0, 275.0), 0.0),

    BLUE_1(Alliance.BLUE, Vec2d(635.0, 75.0), Math.PI),
    BLUE_2(Alliance.BLUE, Vec2d(635.0, 175.0), Math.PI),
    BLUE_3(Alliance.BLUE, Vec2d(635.0, 275.0), Math.PI)

}

