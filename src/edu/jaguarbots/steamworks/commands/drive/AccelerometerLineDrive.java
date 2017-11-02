package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.net.Accelerometer;

/**
 * Drives the robot in a straight line based on the values from the accelerometer
 * 
 * @author Zach Deibert
 * @since 2017
 * @version 2017
 */
public class AccelerometerLineDrive extends CommandBase {
    /**
     * The distance to drive in feet
     * 
     * @since 2017
     */
    private final double distance;
    /**
     * The speed to drive at between [0, 1]
     * 
     * @since 2017
     */
    private final double speed;
    /**
     * The target x coordinate
     * 
     * @since 2017
     */
    private double targetX;
    /**
     * The target y coordinate
     * 
     * @since 2017
     */
    private double targetY;
    /**
     * The angle the robot was at during the last iteration
     * 
     * @since 2017
     */
    private double lastAngle;
    @Override
    protected void initialize() {
	lastAngle = Accelerometer.getRotation();
	targetX = distance * Math.cos(lastAngle) + Accelerometer.getX();
	targetY = distance * Math.sin(lastAngle) + Accelerometer.getY();
    }
    @Override
    protected void execute() {
	double angle = Accelerometer.getRotation();
	double diff = angle - lastAngle;
	lastAngle = angle;
	if (diff < 0) {
	    driveSubsystem.driveTank(speed * Math.abs(Math.cos(2 * diff)), speed);
	} else {
	    driveSubsystem.driveTank(speed, speed * Math.abs(Math.cos(2 * diff)));
	}
    }
    @Override
    protected boolean isFinished() {
	return Math.abs(Math.atan2(targetY - Accelerometer.getY(), targetX - Accelerometer.getX()) - Accelerometer.getRotation()) > Math.PI / 2;
    }
    @Override
    protected void end() {
	driveSubsystem.driveTank(0, 0);
    }
    @Override
    protected void interrupted() {
	end();
    }
    /**
     * Drives a certain distance at a speed of .7
     * 
     * @param distance
     *            The distance to drive in feet.
     * @since 2017
     */
    public AccelerometerLineDrive(double distance) {
	this(distance, 0.7);
    }
    /**
     * Drives a certain distance at a certain speed.
     * 
     * @param distance
     *            The distance to travel in feet.
     * @param speed
     *            The maximum speed to run motors at.
     * @since 2017
     */
    public AccelerometerLineDrive(double distance, double speed) {
	requires(driveSubsystem);
	this.distance = distance;
	this.speed = speed;
    }
}
