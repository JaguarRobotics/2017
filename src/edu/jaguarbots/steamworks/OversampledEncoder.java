package edu.jaguarbots.steamworks;

import edu.wpi.first.wpilibj.Encoder;

public class OversampledEncoder extends Encoder {
    private static final int SAMPLES_TO_AVERAGE = 20;
    private final double[]   values;
    private int              frame;

    @Override
    public double getDistance() {
        double value = super.getDistance();
        values[frame % SAMPLES_TO_AVERAGE] = value;
        double sum = 0;
        int numSamples = Math.min(SAMPLES_TO_AVERAGE, ++frame);
        for (int i = 0; i < numSamples; ++i) {
            sum += values[i];
        }
        return sum / numSamples;
    }

    public OversampledEncoder(int channelA, int channelB) {
        super(channelA, channelB);
        values = new double[SAMPLES_TO_AVERAGE];
        frame = 0;
    }
}
