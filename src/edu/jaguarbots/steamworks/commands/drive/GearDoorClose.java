package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;

/**
 *	Closes the crappy idea of a door panel thing that Jacob made.
 *
 *	@author Brian Parks, Nathan Gawith, Kyle, K
 *	@since 2017
 */
public class GearDoorClose extends CommandBase {
	/**
	 *	Closes the crappy idea of a door panel thing that Jacob made.
	 */
	public GearDoorClose() {
		requires(driveSubsystem);
	}
	@Override
	protected void initialize() {
		DriveSubsystem.gearDoorClose();
		System.out.println("I AM CLOSED!");
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
