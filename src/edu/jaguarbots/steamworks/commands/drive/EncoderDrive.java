package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives the robot based on a distance gotten from encoders.
 * 
 * @author Jack, Zach Deibert
 * @since 2016
 * @version 2017
 */
public class EncoderDrive extends CommandBase {
    private static final double CUTOFF_VALUE = 0.4;
    /**
     * The distance to travel
     * 
     * @since 2016
     */
    private double distance;
    /**
     * The speed to travel at (between 0 and 1)
     * 
     * @since 2016
     */
    private double speed;
    /**
     * Drives a certain distance at a speed of .7
     * 
     * @param distance
     *            to drive in inches.
     * @since 2016
     */
    public EncoderDrive(double distance) {
	this(distance, (distance < 0) ? -0.7 : 0.7);
    }
    /**
     * Drives a certain distance at a certain speed.
     * 
     * @param distance
     *            to travel in inches.
     * @param speed
     *            to run motors at.
     * @since 2016
     */
    public EncoderDrive(double distance, double speed) {
	requires(driveSubsystem);
	this.distance = distance;
	this.speed = (distance < 0) ? -1 * Math.abs(speed) : Math.abs(speed);
    }
    private double distanceTraveled() {
	return (Math.abs(driveSubsystem.getEncoderLeft()) + Math.abs(driveSubsystem.getEncoderRight())) / 2;
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
	double adjSpeed = Math.min(((distance - distanceTraveled()) / distance) * (1 - CUTOFF_VALUE) + CUTOFF_VALUE, speed);
	if (correctIt) {
	    driveSubsystem.driveTank(adjSpeed * powers[0], adjSpeed * powers[1]);
	} else {
	    driveSubsystem.driveTank(speed, speed);
	    SmartDashboard.putNumber("EncoderLeft", CommandBase.driveSubsystem.getEncoderLeft());
	    SmartDashboard.putNumber("EncoderRight", CommandBase.driveSubsystem.getEncoderRight());
	}
    }
    @Override
    protected boolean isFinished() {
	boolean isFinished = false;
	if (speed > 0) {
	    isFinished = distanceTraveled() >= distance;
	} else {
	    isFinished = distanceTraveled() <= distance;
	}
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
