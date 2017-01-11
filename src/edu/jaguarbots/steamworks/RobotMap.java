package edu.jaguarbots.steamworks;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

  //pwms
    public static final int leftDrive              = 0;
    public static final int rightDrive             = 1;

    //relays
    public static final int relayClimberMotor        = 1;
    
    //sol

    // digital ios
    public static final int leftEncoderAChannel    = 0;
    public static final int leftEncoderBChannel    = 1;
    public static final int rightEncoderAChannel   = 2;
    public static final int rightEncoderBChannel   = 3;
//    public static final int climberLimitSwitch = 7;

    //spi
//    public static final int gyro = 0;

    // joysticks
    public static final int leftStick              = 0;
    public static final int rightStick             = 1;
    public static final int manipulator            = 2;
}
