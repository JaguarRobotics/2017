package edu.jaguarbots.steamworks;

import edu.wpi.first.wpilibj.Encoder;

public class OversampledEncoder extends Encoder {
    public OversampledEncoder(int channelA, int channelB) {
        super(channelA, channelB, false, EncodingType.k4X);
        setSamplesToAverage(127);
    }
}
