package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Turns robot a specified angle left or right. Positive angles turn counter-clockwise.
 * 
 * @author Cody Moose
 * @version 2016
 * @since 2016
 */
public class DriveTurn extends CommandBase
{
    private double turnAmount;
    private double angle;

    /**
     * Turns robot a specified angle left or right.
     * 
     * @param turnAmount
     *            Angle to turn. Negative values cause a clockwise turn.
     */
    public DriveTurn(double turnAmount)
    {
        requires(driveSubsystem);
        this.turnAmount = turnAmount;
    }

    @Override
    protected void initialize()
    {
        angle = turnAmount /*+ driveSubsystem.getGyro()*/;
    }

    @Override
    protected void execute()
    {
        if(turnAmount>0)
            driveSubsystem.robotTurn(.7);
        if(turnAmount<0)
            driveSubsystem.robotTurn(-.7);
    }

    @Override
    protected boolean isFinished()
    {
        return (turnAmount >= 0 /*&& driveSubsystem.getGyro() >= angle*/) || (turnAmount < 0 /*&& driveSubsystem.getGyro() <= angle*/);
    }

    @Override
    protected void end()
    {
    }

    @Override
    protected void interrupted()
    {
    }
}
