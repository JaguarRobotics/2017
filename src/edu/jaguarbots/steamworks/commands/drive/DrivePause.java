package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Pauses the specified number of milliseconds (only used in Auto)
 * 
 * @author Brian Parks
 * @since 2017
 */
public class DrivePause extends CommandBase
{
    /**
     * Time to wait in milliseconds
     * 
     * @since 2017
     */
    private long waitTime;
    /**
     * Time in milliseconds that this command was initialized
     * 
     * @since 2017
     */
    private long startTime;

    /**
     * Pauses the specified number of milliseconds (only used in Auto)
     * 
     * @since 2017
     */
    public DrivePause(long waitTime)
    {
        requires(driveSubsystem);
        this.waitTime = waitTime;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute()
    {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished()
    {
        long now = System.currentTimeMillis();
        return (now - startTime) > waitTime;
    }

    // Called once after isFinished returns true
    @Override
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted()
    {
    }
}
