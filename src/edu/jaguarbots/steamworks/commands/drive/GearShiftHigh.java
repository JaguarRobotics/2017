package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;

/**
 *
 */
public class GearShiftHigh extends CommandBase {

	public GearShiftHigh() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(driveSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		DriveSubsystem.gearShiftHigh();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
