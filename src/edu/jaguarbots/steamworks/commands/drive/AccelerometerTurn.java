package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.net.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns the robot using the values on the encoders
 * 
 * @author Nathan Gawith
 * @since 2017
 * @version 2017
 */
public class AccelerometerTurn extends CommandBase
{
    /**
     * <html>The angle to turn <u>to</u> in radians</html> 
     * 
     * @since 2017
     */
    private double angle = 0;
    /**
     * The speed at which to turn
     * 
     * @since 2017
     */
    private double speed = 0;

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
//      if (angle > 0)
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
        return (speed > 0) ? (angle > Accelerometer.getRotation()) : (angle > Accelerometer.getRotation());
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
    public AccelerometerTurn(double angle, double speed)
    {
        requires(driveSubsystem);
        this.angle = Accelerometer.getRotation() + angle;
        this.speed = (angle < 0) ? -1 * Math.abs(speed) : Math.abs(speed);
    }

    /**
     * Turns the robot at a speed of 0.7
     * 
     * @param angle
     *            The angle to turn in radians (positive is to the left, negative is to the right)
     * @since 2017
     */
    public AccelerometerTurn(double angle)
    {
        this(angle, 0.7);
    }
}
