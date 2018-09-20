package frc.team1091.robot.components;

import com.team1091.shared.components.IGyroscope;
import edu.wpi.first.wpilibj.AnalogGyro;

public class WrappedGyroscope implements IGyroscope {

    private final AnalogGyro analogGyro;

    public WrappedGyroscope(int channel) {
        if (channel != 0 && channel != 1) {
            throw new RuntimeException("Gyro only works on channels 0 + 1");
        }

        this.analogGyro = new AnalogGyro(channel);
    }

    @Override
    public double get() {
        return analogGyro.getAngle();
    }
}
