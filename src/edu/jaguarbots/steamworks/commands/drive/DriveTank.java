package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Drives the robot in teleop based on left and right joystick inputs.
 */
public class DriveTank extends CommandBase
{
    /**
     * below is an image of the idea we are using for joystick vs motor power.
     * <html><img src="https://puu.sh/tEhvx/a211c4f7a1.png"></img></html>
     */
    public DriveTank()
    {
        requires(driveSubsystem);
    }

    double left;
    double right;

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * below is an image of the idea we are using for joystick vs motor power.
     * <html><img src="https://puu.sh/tEhvx/a211c4f7a1.png"></img></html>
     */
    protected void execute()
    {
    	double powNum = 2;
    	double j0 = oi.Joystick0.getY();
    	double j1 = oi.Joystick1.getY();
    	double aj0 = Math.abs(j0);
    	double aj1 = Math.abs(j1);
    	double pj0 = Math.pow(aj0, powNum);
    	double pj1 = Math.pow(aj1, powNum);
        if(Math.abs(pj0) > aj0)
        	pj0 = aj0;
        if(Math.abs(pj1) > aj1)
        	pj1 = aj1;
        left = pj0 * (aj0 / j0);
        right = pj1 * (aj1 / j1);
        driveSubsystem.driveTank(-left, -right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
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
