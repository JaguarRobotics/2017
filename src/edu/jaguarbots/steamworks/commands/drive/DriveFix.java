package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Takes robot angle before doing something, and realigns robot afterwards
 * @author Cody Moose
 * @version 2016
 * @since 2016
 *
 */
public class DriveFix extends CommandBase
{
    private double startAngle;
    private double speed = .7;
    public DriveFix(double startAngle)
    {
        requires(driveSubsystem);

        this.startAngle = startAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        driveSubsystem.gyroTurnToAngle(startAngle, speed);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return (driveSubsystem.getGyro() >= startAngle);
    }

    // Called once after isFinished returns true
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}
