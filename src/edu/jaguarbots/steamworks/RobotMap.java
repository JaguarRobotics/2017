package edu.jaguarbots.steamworks;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides
 * flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @since 2017
 */
public class RobotMap
{
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    // pwms
    public static final int     leftDrive            = 0;
    public static final MotorID leftDriveType        = MotorID.Talon;
    public static final int     rightDrive           = 1;
    public static final MotorID rightDriveType       = MotorID.Talon;
    public static final int     climberMotor         = 2;
    public static final MotorID climberMotorType     = MotorID.Talon;
    // relays
    // sol
    // digital ios
    public static final int     leftEncoderAChannel  = 1;
    public static final int     leftEncoderBChannel  = 2;
    public static final int     rightEncoderAChannel = 3;
    public static final int     rightEncoderBChannel = 4;
    // public static final int climberLimitSwitch = 7;
    // spi
    // public static final int gyro = 0;
    // joysticks
    public static final int     leftStick            = 0;
    public static final int     rightStick           = 1;
    public static final int     manipulator          = 2;
    /* These below may be temporary */
    public static int           solClimber;
    public static int           climberLimitSwitch;
    public static AnalogInput   gyro;
    public static int           solGearShift;
}
