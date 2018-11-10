package com.team1091.shared.math

val Number.radians: Rotation
    get() = Rotation(this.toDouble())

val Number.degrees: Rotation
    get() = Rotation(this.toDouble() / (180 / Math.PI))

class Rotation(private val radians: Double) {

    fun toDegrees() = radians * (180 / Math.PI)
    fun toRadians() = radians

    operator fun plus(other: Rotation) = Rotation(this.radians + other.radians)
    operator fun minus(other: Rotation) = Rotation(this.radians - other.radians)
}