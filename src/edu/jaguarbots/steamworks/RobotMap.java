package edu.jaguarbots.steamworks;

import static edu.jaguarbots.steamworks.MotorID.Talon;
import static edu.jaguarbots.steamworks.MotorID.Spark;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers floating around.
 * 
 * @since 2017
 */
public interface RobotMap {
	// Sets the ports of any motors and there types.
	public static final int LEFT_DRIVE_MOTOR_PORT = 0;
	public static final MotorID LEFT_DRIVE_MOTOR_TYPE = Talon;
	public static final int RIGHT_DRIVE_MOTOR_PORT = 1;
	public static final MotorID RIGHT_DRIVE_MOTOR_TYPE = Talon;
	public static final int CLIMBER_MOTOR_PORT = 2;
	public static final MotorID CLIMBER_MOTOR_TYPE = Spark;
	// Sets the ports of any inputs or outputs for the RIO (IE Encoders).
	public static final int LEFT_ENCODER_CHANNEL_A = 0;
	public static final int LEFT_ENCODER_CHANNEL_B = 1;
	public static final int RIGHT_ENCODER_CHANNEL_A = 2;
	public static final int RIGHT_ENCODER_CHANNEL_B = 3;
	// Sets the ports that the joystics are plugged into.
	public static final int LEFT_JOYSTICK_PORT = 1;
	public static final int RIGHT_JOYSTICK_PORT = 2;
	public static final int MANIPULATOR_JOYSTICK_PORT = 3;
	// The ports that things are in on the robot itself.
	public static final double ROBOT_WIDTH = 16;
	public static final int SOLENOID_CLIMBER_PORT = 500;
	public static final int CLIMBER_LIMIT_SWITCH_PORT = 5001;
	public static final int SOLENOID_GEAR_SHIFT_PORT = 0;
}
