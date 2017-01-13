package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Drives the robot based on a distance gotten from encoders.
 * @author Jack
 *
 */
public class EncoderDrive extends CommandBase
{
    private double distance;
    private double speed = .7;
    private boolean end;
    private double powers[] = new double[2];
    
    /**
     * Drives a certain distance at a speed of .7
     * @param distance to drive in inches.
     */
    public EncoderDrive(double distance)
    {
        requires(driveSubsystem);
        this.distance=distance;
        end = false;
    }
    /**
     * Drives a certain distance at a certain speed.
     * @param distance to travel in inches.
     * @param speed to run motors at.
     */
    public EncoderDrive(double distance, double speed)
    {
        requires(driveSubsystem);
        this.distance=distance;
        this.speed = speed;
        end = false;
    }
    protected void initialize()
    {
        driveSubsystem.startEncoders();
        driveSubsystem.resetEncoders(true, true);
        //driveSubsystem.driveTank(speed, speed);
        
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        powers = driveSubsystem.getMotorPowers();
        driveSubsystem.driveTank(speed*powers[0], speed*powers[1]);
        
        if (driveSubsystem.getEncoderLeft() >= distance || driveSubsystem.getEncoderRight() >= distance)
        { 
            end=true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return end;
    }

    // Called once after isFinished returns true
    protected void end()
    {
        driveSubsystem.driveTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}
