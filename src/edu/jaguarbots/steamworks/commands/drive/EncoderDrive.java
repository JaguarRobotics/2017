package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Drives the robot based on a distance gotten from encoders.
 * 
 * @author Jack, Zach Deibert
 * @since 2016
 * @version 2017
 */
public class EncoderDrive extends CommandBase
{
    /**
     * The distance to travel
     * 
     * @since 2016
     */
    private double distance;
    /**
     * The speed to travel at (between 0 and 1)
     * 
     * @since 2016
     */
    private double speed;

    /**
     * Drives a certain distance at a speed of .7
     * 
     * @param distance
     *            to drive in inches.
     * @since 2016
     */
    public EncoderDrive(double distance)
    {
        this(distance, 0.7);
    }

    /**
     * Drives a certain distance at a certain speed.
     * 
     * @param distance
     *            to travel in inches.
     * @param speed
     *            to run motors at.
     * @since 2016
     */
    public EncoderDrive(double distance, double speed)
    {
        requires(driveSubsystem);
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    protected void initialize()
    {
        driveSubsystem.startEncoders();
        driveSubsystem.resetEncoders(true, true);
    }

    @Override
    protected void execute()
    {
        double[] powers = driveSubsystem.getMotorPowers();
        driveSubsystem.driveTank(speed * powers[0], speed * powers[1]);
    }

    @Override
    protected boolean isFinished()
    {
        return driveSubsystem.getEncoderLeft() >= distance || driveSubsystem.getEncoderRight() >= distance;
    }

    @Override
    protected void end()
    {
        driveSubsystem.driveTank(0, 0);
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}
