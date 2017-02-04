package edu.jaguarbots.steamworks;

import edu.jaguarbots.steamworks.test.DebuggingMotorProxy;
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
     *            The PWM channel that the motor is attached to. 0-9 are on-board, 10-19 are on the MXP port
     * @param motor
     *            The type of motor to create
     * @param name
     *            The name of the motor (for the log)
     * @return The motor object
     * @since 2017
     */
    public static SpeedController motor(int channel, MotorID motor, String name)
    {
        final SpeedController impl;
        switch (motor)
        {
            case CANTJaguar:
                impl = new Jaguar(channel);
                break;
            case SD540:
                impl = new SD540(channel);
                break;
            case Spark:
                impl = new Spark(channel);
                break;
            case Talon:
                impl = new Talon(channel);
                break;
            case Victor:
                impl = new Victor(channel);
                break;
            case VictorSP:
                impl = new VictorSP(channel);
                break;
            default:
                throw new UnsupportedOperationException("Invalid motor type");
        }
        return new DebuggingMotorProxy(impl, name);
    }
}
