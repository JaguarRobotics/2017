package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        this(distance, (distance < 0) ? -0.7 : 0.7);
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
        this.speed = (distance < 0) ? -1 * Math.abs(speed) : Math.abs(speed);
    }

    @Override
    protected void initialize()
    {
        driveSubsystem.resetEncoders(true, true);
        driveSubsystem.startEncoders();
    }

    @Override
    protected void execute()
    {
        boolean correctIt = true;
        double[] powers = driveSubsystem.getMotorPowers();
        if (correctIt)
        {
//            if( driveSubsystem.getEncoderLeft() >= Math.abs(distance)*.8 || (-1 * driveSubsystem.getEncoderRight()) >= Math.abs(distance)*.8){
//                driveSubsystem.driveTank(adjustedSpeed * powers[0], adjustedSpeed * powers[1]);
//            }
//            else
//            {
                driveSubsystem.driveTank(speed * powers[0], speed * powers[1]);
//            }
        }
        else
        {
            driveSubsystem.driveTank(speed, speed);
            SmartDashboard.putNumber("EncoderLeft",
                            CommandBase.driveSubsystem.getEncoderLeft());
            SmartDashboard.putNumber("EncoderRight",
                            CommandBase.driveSubsystem.getEncoderRight());
        }
//        if (counter % 5 == 0) System.out.print(
//                        "Left " + CommandBase.driveSubsystem.getEncoderLeft());
//        if (counter % 5 == 0) System.out.println("	Right "
//                        + CommandBase.driveSubsystem.getEncoderRight());
        counter++;
    }

    int counter = 0;

    @Override
    protected boolean isFinished()
    {
        boolean isFinished = false;
        if (speed > 0)
        {
            isFinished = driveSubsystem.getEncoderLeft() >= distance || (-1
                            * driveSubsystem.getEncoderRight()) >= distance;
        }
        else
        {
            isFinished = driveSubsystem.getEncoderLeft() <= distance || (-1
                            * driveSubsystem.getEncoderRight()) <= distance;
        }
        return isFinished;
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
