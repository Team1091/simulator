package frc.team1091.robot.components;

import com.team1091.shared.components.IEncoder;
import edu.wpi.first.wpilibj.Encoder;

public class WrappedEncoder implements IEncoder {

    private Encoder encoder;

    public WrappedEncoder(int channelA, int channelB) {
        encoder = new Encoder(channelA, channelB);
    }

    public WrappedEncoder(int channelA, int channelB, boolean reverse) {
        encoder = new Encoder(channelA, channelB, reverse);
    }

    @Override
    public void reset() {
        encoder.reset();
    }

    @Override
    public int get() {
        return encoder.get();
    }
}
