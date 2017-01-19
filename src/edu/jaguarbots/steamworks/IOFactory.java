package edu.jaguarbots.steamworks;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SD540;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The factory for making motors and sensors
 * 
 * @author Zach Deibert
 * @since 2017
 * @version 2017
 */
public class IOFactory
{
    /**
     * Creates a motor
     * 
     * @param channel
     *            The PWM channel that the Jaguar is attached to. 0-9 are on-board, 10-19 are on the MXP port
     * @param motor
     *            The type of motor to create
     * @return The motor object
     * @since 2017
     */
    public static SpeedController motor(int channel, MotorID motor)
    {
        switch (motor)
        {
            case CANTJaguar:
                return new Jaguar(channel);
            case SD540:
                return new SD540(channel);
            case Spark:
                return new Spark(channel);
            case Talon:
                return new Talon(channel);
            case Victor:
                return new Victor(channel);
            case VictorSP:
                return new VictorSP(channel);
            default:
                throw new UnsupportedOperationException("Invalid motor type");
        }
    }
}
