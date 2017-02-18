package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Drives the robot in teleop based on left and right joystick inputs.
 */
public class DrivePause extends CommandBase {
	private long waitTime;
	private long startTime;

	public DrivePause(long waitTime) {
		requires(driveSubsystem);
		this.waitTime = waitTime;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		long now = System.currentTimeMillis();
		// if (now - startTime > waitTime) {
		// return true;
		// } else {
		// return false;
		// }
		return (now - startTime) > waitTime;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
