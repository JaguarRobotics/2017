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

	// Called just before this Command runs the first time
    @Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
		stickX = oi.Joystick1.getX();
		stickY = oi.Joystick1.getY();
		left = stickY + ((stickX < 0) ? stickX : 0);
		right = stickY - ((stickX > 0) ? stickX : 0);
		driveSubsystem.driveTank(-left, -right);
	}

	// Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
    @Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
    @Override
	protected void interrupted() {
	}
}
