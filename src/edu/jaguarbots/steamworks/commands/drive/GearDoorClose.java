package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.Robot.Gate;
import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Closes the gear security door
 *
 * @author Brian Parks, Nathan Gawith, Kyle, K
 * @since 2017
 */
public class GearDoorClose extends CommandBase {
    /**
     * Closes the gear security door
     */
    public GearDoorClose() {
	requires(driveSubsystem);
    }
    @Override
    protected void initialize() {
	DriveSubsystem.gearDoorOpen();
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
	SmartDashboard.putString("Gate", Gate.Closed.toString());
    }
    @Override
    protected void interrupted() {
    }
}
