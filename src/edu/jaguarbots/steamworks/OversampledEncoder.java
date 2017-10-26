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
        if (++frame >= SAMPLES_TO_AVERAGE) {
            double avg = 0;
            for (double val : values) {
                avg += val;
            }
            return avg / SAMPLES_TO_AVERAGE;
        } else {
            return value;
        }
    }

    public OversampledEncoder(int channelA, int channelB) {
        super(channelA, channelB);
        values = new double[SAMPLES_TO_AVERAGE];
        frame = 0;
    }
}
