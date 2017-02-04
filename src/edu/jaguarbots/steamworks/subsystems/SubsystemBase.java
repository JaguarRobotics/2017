package edu.jaguarbots.steamworks.subsystems;

import edu.jaguarbots.steamworks.IOFactory;
import edu.jaguarbots.steamworks.MotorID;
import edu.jaguarbots.steamworks.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The base class for all of the subsystems
 * 
 * @author Zach Deibert
 * @since 2017
 * @version 2017
 */
abstract class SubsystemBase extends Subsystem implements RobotMap
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
    protected static SpeedController motor(int channel, MotorID motor, String name)
    {
        return IOFactory.motor(channel, motor, name);
    }

    /**
     * Creates an encoder
     * 
     * @param channelA
     *            The a channel digital input channel.
     * @param channelB
     *            The b channel digital input channel.
     * @param name
     *            The name of the motor (for the log)
     * @since 2017
     */
    protected static Encoder encoder(int channelA, int channelB, String name)
    {
        return IOFactory.encoder(channelA, channelB, name);
    }
}
