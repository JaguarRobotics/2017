package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Drives the robot in teleop based on left and right joystick inputs.
 */
public class DriveArcade extends CommandBase {
    public DriveArcade() {
	requires(driveSubsystem);
    }
    /**
     * left motor power to pass to the driveSubsystem.driveTank().
     * 
     * @since 2017
     */
    double left;
    /**
     * right motor power to pass to the driveSubsystem.driveTank().
     * 
     * @since 2017
     */
    double right;
    /**
     * the percentage of the x axis push amount on the joystick
     * 
     * @since 2017
     */
    double stickX;
    /**
     * the percentage of the y axis push amount on the joystick
     * 
     * @since 2017
     */
    double stickY;
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
	stickX = oi.Joystick1.getX();
	stickY = oi.Joystick1.getY();
	left = stickY + ((stickX < 0) ? stickX : 0);
	right = stickY - ((stickX > 0) ? stickX : 0);
	driveSubsystem.driveTank(-left, -right);
    }
    @Override
    protected boolean isFinished() {
	return false;
    }
    @Override
    protected void end() {
    }
    @Override
    protected void interrupted() {
    }
}
