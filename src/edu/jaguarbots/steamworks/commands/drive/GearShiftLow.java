package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;

/**
 *	Shifts the robot into low gear.
 *
 *	@author Max K
 *	@since 2017
 */
public class GearShiftLow extends CommandBase {
	/**
	 *	Shifts the robot into low gear.
	 */
	public GearShiftLow() {
		requires(driveSubsystem);
	}
	@Override
	protected void initialize() {
		DriveSubsystem.gearShiftHigh();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
