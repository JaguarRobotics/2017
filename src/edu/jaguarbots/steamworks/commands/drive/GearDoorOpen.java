package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.Robot.Gate;
import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Opens the gear security door
 *
 * @author Brian Parks, Nathan Gawith, Kyle, K
 * @since 2017
 */
public class GearDoorOpen extends CommandBase {
    /**
     * Opens the gear security door
     */
    public GearDoorOpen() {
	requires(driveSubsystem);
    }
    @Override
    protected void initialize() {
	DriveSubsystem.gearDoorClose();
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
