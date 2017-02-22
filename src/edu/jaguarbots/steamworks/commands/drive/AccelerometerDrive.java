package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.net.Accelerometer;

/**
 * Drives the robot based on a distance gotten from encoders.
 * 
 * @author Nathan Gawith
 * @since 2017
 * @version 2017
 */
public class AccelerometerDrive extends CommandBase {
	/**
	 * The initial x position
	 * 
	 * @since 2017
	 */
	private double initialXPosition;
	/**
	 * The initial y position
	 * 
	 * @since 2017
	 */
	private double initialYPosition;
	/**
	 * The goal x position
	 * 
	 * @since 2017
	 */
	private double finalXPosition;
	/**
	 * The goal y position
	 * 
	 * @since 2017
	 */
	private double finalYPosition;
	/**
	 * The speed to travel at (between 0 and 1)
	 * 
	 * @since 2017
	 */
	private double speed;

	/**
	 * Drives a certain distance at a speed of .7
	 * 
	 * @param distance
	 *            to drive in inches.
	 * @since 2017
	 */
	public AccelerometerDrive(double distance) {
		this(distance, (distance < 0) ? -0.7 : 0.7);
	}

	/**
	 * Drives a certain distance at a certain speed.
	 * 
	 * @param distance
	 *            to travel in inches.
	 * @param speed
	 *            to run motors at.
	 * @since 2017
	 */
	public AccelerometerDrive(double distance, double speed) {
		requires(driveSubsystem);
		this.finalXPosition = Accelerometer.getX() + (distance * Math.cos(Accelerometer.getRotation()));
		this.finalYPosition = Accelerometer.getY() + (distance * Math.sin(Accelerometer.getRotation()));
		this.initialXPosition = Accelerometer.getX();
		this.initialYPosition = Accelerometer.getY();
		this.speed = (distance < 0) ? -1 * Math.abs(speed) : Math.abs(speed);
	}

	@Override
	protected void initialize() {
		driveSubsystem.resetEncoders(true, true);
		driveSubsystem.startEncoders();
	}

	@Override
	protected void execute() {
		boolean correctIt = true;
		double[] powers = driveSubsystem.getMotorPowers();
		if (correctIt)
			driveSubsystem.driveTank(speed * powers[0], speed * powers[1]);
		else
			driveSubsystem.driveTank(speed, speed);
		counter++;
	}

	int counter = 0;

	@Override
	protected boolean isFinished() {
		boolean isFinished = false;
		if (finalXPosition < initialXPosition) {
			isFinished = Accelerometer.getX() <= finalXPosition;
		}
		if (finalYPosition < initialYPosition) {
			isFinished = Accelerometer.getY() <= finalYPosition;
		}
		if (finalXPosition > initialXPosition) {
			isFinished = Accelerometer.getX() >= finalXPosition;
		}
		if (finalYPosition > initialYPosition) {
			isFinished = Accelerometer.getY() >= finalYPosition;
		}
		// isFinished = Accelerometer.getX() >= finalXPosition
		// || Accelerometer.getY() >= finalYPosition;
		return isFinished;
	}

	@Override
	protected void end() {
		driveSubsystem.driveTank(0, 0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
