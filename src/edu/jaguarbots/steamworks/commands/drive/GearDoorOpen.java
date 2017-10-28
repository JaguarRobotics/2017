package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.Robot.Gate;
import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	Opens the crappy idea of a door panel thing that Jacob made as high as Snoop Dogg.
 *
 *	@author Brian Parks, Nathan Gawith, Kyle, K
 *	@since 2017
 */
public class GearDoorOpen extends CommandBase {
	/**
	 *	Lifts the crappy idea of a door panel thing that Jacob made.
	 */
	public GearDoorOpen() {
		requires(driveSubsystem);
	}
	@Override
	protected void initialize() {
		DriveSubsystem.gearDoorOpen();
		System.out.println("I AM OPEN!");
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
        SmartDashboard.putString("Gate", Gate.Open.toString());
	}

	@Override
	protected void interrupted() {
	}
}
