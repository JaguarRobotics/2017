package edu.jaguarbots.steamworks.subsystems;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Subsystem for the mechanism to scale the rope
 * 
 * @author Max K.
 * @since 2017
 * @version 2017
 */
public class ClimberSubsystem extends SubsystemBase {
	/**
	 * Motor for ascending and descending
	 */
	private SpeedController climberMotor = motor(CLIMBER_MOTOR_PORT, CLIMBER_MOTOR_TYPE);

	/**
	 * The subsystem that holds all of the methods needed for the climber
	 */
	public ClimberSubsystem() {
	}

	/**
	 * What the subsystem will do when initialized
	 */
	public void initDefaultCommand() {
	}

	/**
	 * Makes climber motor turn forward
	 */
	public void motorForward() {
		climberMotor.set(1.0);
	}

	/**
	 * Makes motor stop running and stop accepting input
	 */
	public void stopMotor() {
		climberMotor.stopMotor();
	}

	/**
	 * Makes climber motor turn backward
	 */
	public void motorBackward() {
		climberMotor.set(-1.0);
	}

}