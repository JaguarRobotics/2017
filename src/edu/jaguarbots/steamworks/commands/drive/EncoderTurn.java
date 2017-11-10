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
public class EncoderTurn extends CommandBase {
    /**
     * The speed at which to turn
     * 
     * @since 2017
     */
    private final double speed;
    /**
     * Target arclength
     * 
     * @since 2017
     */
    private double       arclength;
    private final double WHEELBASE_WIDTH = 18.0;

    @Override
    protected void initialize() {
        driveSubsystem.resetEncoders(true, true);
        driveSubsystem.startEncoders();
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("EncoderLeft", CommandBase.driveSubsystem.getEncoderLeft());
        SmartDashboard.putNumber("EncoderRight", CommandBase.driveSubsystem.getEncoderRight());
        driveSubsystem.driveTank(-speed, speed);
    }

    @Override
    protected boolean isFinished() {
        double left = Math.abs(driveSubsystem.getEncoderLeft());
        double right = Math.abs(driveSubsystem.getEncoderRight());
        return Math.max(left, right) >= arclength;
    }

    @Override
    protected void end() {
        driveSubsystem.driveTank(0, 0);
    }

    @Override
    protected void interrupted() {
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
    public EncoderTurn(double angle, double speed) {
        requires(driveSubsystem);
        this.speed = (angle < 0) ? -1 * Math.abs(speed) : Math.abs(speed);
        this.arclength = Math.abs(angle * WHEELBASE_WIDTH / 2);
    }

    /**
     * Turns the robot at a speed of 0.7
     * 
     * @param angle
     *            The angle to turn in radians (positive is to the left, negative is to the right)
     * @since 2017
     */
    public EncoderTurn(double angle) {
        this(angle, 0.7);
    }
}
