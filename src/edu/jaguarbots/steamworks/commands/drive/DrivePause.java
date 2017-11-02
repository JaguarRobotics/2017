package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Pauses the specified number of milliseconds (only used in Auto)
 * 
 * @author Brian Parks
 * @since 2017
 */
public class DrivePause extends CommandBase {
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
    public DrivePause(long waitTime) {
	requires(driveSubsystem);
	this.waitTime = waitTime;
    }
    @Override
    protected void initialize() {
	startTime = System.currentTimeMillis();
    }
    @Override
    protected void execute() {
    }
    @Override
    protected boolean isFinished() {
	long now = System.currentTimeMillis();
	return (now - startTime) > waitTime;
    }
    @Override
    protected void end() {
    }
    @Override
    protected void interrupted() {
    }
}
