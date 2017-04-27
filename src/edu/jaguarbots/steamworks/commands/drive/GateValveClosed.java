package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;

/**
 *	Shifts the robot into high gear.
 *
 *	@author Max K
 *	@since 2017
 */
public class GateValveClosed extends CommandBase {
	/**
	 *	Shifts the robot into high gear.
	 */
	public GateValveClosed() {
		requires(driveSubsystem);
	}
	@Override
	protected void initialize() {
		DriveSubsystem.gateValveClosed();
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
