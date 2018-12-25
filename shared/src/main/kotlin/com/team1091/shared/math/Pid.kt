package com.team1091.shared.math

class Pid(val kp: Double, val kd: Double, val ki: Double) {

    var lastSum = 0.0
    var lastErr = 0.0

    fun run(goalPosition: Double, curPosition: Double): Double {

        /* 2. calculate error */
        val err = goalPosition - curPosition;

        /* 3. calculate the output */
        val proportional = kp * err
        val derivative = kd * (err - lastErr)
        val integral = ki * (lastSum + err)
        val output = proportional + derivative + integral

        /* 4. keep history */
        lastSum += err
        lastErr = err

        /* 5. apply control output */
        return output
    }

}