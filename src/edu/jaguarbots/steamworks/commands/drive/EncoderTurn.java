package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns the robot using the values on the encoders
 * 
 * @author Zach Deibert
 * @since 2017
 * @version 2017
 */
public class EncoderTurn extends CommandBase
{
    /**
     * The angle to turn in radians (positive is to the left, negative is to the right)
     * 
     * @since 2017
     */
    private final double angle;
    /**
     * The speed at which to turn
     * 
     * @since 2017
     */
    private final double speed;

    @Override
    protected void initialize()
    {
        driveSubsystem.resetEncoders(true, true);
    	driveSubsystem.startEncoders();
    }

    @Override
    protected void execute()
    {
    	SmartDashboard.putNumber("EncoderLeft", CommandBase.driveSubsystem.getEncoderLeft());
        SmartDashboard.putNumber("EncoderRight", CommandBase.driveSubsystem.getEncoderRight());
        boolean correctIt = true;
        double[] powers = driveSubsystem.getMotorPowers();
        if (correctIt)
            driveSubsystem.driveTank(-speed * powers[0], speed * powers[1]);
        else
            driveSubsystem.driveTank(-speed, speed);
//    	if (angle > 0)
//        {
//            driveSubsystem.driveTank(-speed, speed);
//        }
//        else if (angle < 0)
//        {
//            driveSubsystem.driveTank(-speed, speed);
//        }
    }

    @Override
    protected boolean isFinished()
    {
        return Math.max(driveSubsystem.getDistanceInInches(Math.abs(driveSubsystem.getEncoderLeft())),
        		driveSubsystem.getDistanceInInches(Math.abs(driveSubsystem.getEncoderRight()))) > angle * ROBOT_WIDTH / 2;
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

    /**
     * Default constructor
     * 
     * @param angle
     *            The angle to turn in radians (positive is to the left, negative is to the right)
     * @param speed
     *            The speed at which to turn
     * @since 2017
     */
    public EncoderTurn(double angle, double speed)
    {
        requires(driveSubsystem);
        this.angle = angle;
        this.speed = speed;
    }

    /**
     * Turns the robot at a speed of 0.7
     * 
     * @param angle
     *            The angle to turn in radians (positive is to the left, negative is to the right)
     * @since 2017
     */
    public EncoderTurn(double angle)
    {
        this(angle, 0.7);
    }
}
