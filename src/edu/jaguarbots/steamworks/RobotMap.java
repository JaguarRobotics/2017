package edu.jaguarbots.steamworks;

import static edu.jaguarbots.steamworks.MotorID.Talon;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides
 * flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @since 2017
 */
public interface RobotMap
{
    // PWMs
    public static final int         LEFT_DRIVE_MOTOR_PORT     = 0;
    public static final MotorID     LEFT_DRIVE_MOTOR_TYPE     = Talon;
    public static final int         RIGHT_DRIVE_MOTOR_PORT    = 1;
    public static final MotorID     RIGHT_DRIVE_MOTOR_TYPE    = Talon;
    public static final int         CLIMBER_MOTOR_PORT        = 2;
    public static final MotorID     CLIMBER_MOTOR_TYPE        = Talon;
    // Digital I/Os
    public static final int         LEFT_ENCODER_CHANNEL_A    = 0;
    public static final int         LEFT_ENCODER_CHANNEL_B    = 1;
    public static final int         RIGHT_ENCODER_CHANNEL_A   = 2;
    public static final int         RIGHT_ENCODER_CHANNEL_B   = 3;
    // Joysticks
    public static final int         LEFT_JOYSTICK_PORT        = 1;
    public static final int         RIGHT_JOYSTICK_PORT       = 2;
    public static final int         MANIPULATOR_JOYSTICK_PORT = 3;
    // Robot Design
    /**
     * The width of the robot (between the center of masses of both wheels) in inches
     * 
     * @since 2017
     */
    public static final double      ROBOT_WIDTH               = 32;
    // Temporary?
    public static final int         SOLENOID_CLIMBER_PORT     = 500;
    public static final int         CLIMBER_LIMIT_SWITCH_PORT = 5001;
    public static final AnalogInput GYRO_PORT                 = null;
    //Gear Shifting Solenoid is on "pcm" port 0.
    public static final int         SOLENOID_GEAR_SHIFT_PORT  = 0;
}
