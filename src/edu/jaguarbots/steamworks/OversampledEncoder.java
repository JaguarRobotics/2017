package edu.jaguarbots.steamworks;

import edu.wpi.first.wpilibj.Encoder;

public class OversampledEncoder extends Encoder {
    @Override
    public double getDistance() {
        // TODO oversampling code
        return super.getDistance();
    }

    public OversampledEncoder(int channelA, int channelB) {
        super(channelA, channelB, false, EncodingType.k4X);
        setSamplesToAverage(127);
    }
}
