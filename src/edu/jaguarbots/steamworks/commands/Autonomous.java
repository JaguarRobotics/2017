package edu.jaguarbots.steamworks.commands;

import edu.jaguarbots.steamworks.Robot;
import edu.jaguarbots.steamworks.commands.drive.*;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Autonomous extends CommandGroup
{
    /**
     * Default, does nothing as of yet
     */
    public Autonomous()
    {
    	double straightSpeed = 0.55;
    	double turnSpeed = 0.55;
        DriveSubsystem ds = CommandBase.driveSubsystem;
    	boolean testing = true;
    	if(testing) {
            addSequential(new GearShiftLow());
//            addSequential(new EncoderDrive(88, straightSpeed));
            addSequential(new EncoderTurn(ds.getEncoderTicksFromRadians(ds.getRadiansFromDegrees(1)), turnSpeed));
//            addSequential(new EncoderDrive(, straightSpeed));
//            addSequential(new EncoderTurn(ds.getEncoderTicksFromRadians(ds.getRadiansFromDegrees(-360)), turnSpeed));
    	}
    	else {
    	    new Autonomous(Robot.Position.Left, Robot.Gears.One, Robot.CrossLine.Yes);
    	}
    }
    
    /**
     * Selects autonomous route based on defense to cross, position in, and what
     * goal to shoot in.
     * See a picture below
     * <html><img src="https://puu.sh/tWete/63e013aebf.png"></img></html>
     * @param position
     *            enum: Left, Middle, or Right
     * @param gears
     *            enum: One, Two, or Three
     * @param cross
     *            enum: Yes or No
     */
    public Autonomous(final Robot.Position position,
            final Robot.Gears gears, final Robot.CrossLine cross)
    {
        DriveSubsystem ds = CommandBase.driveSubsystem;
        addSequential(new GearShiftLow());
        addSequential(new EncoderDrive(ds.getEncoderTicksFromInches(93.3 - 31 + 15.25), 0.8));
        switch(position)
        {
            case Left:
                switch(gears)
                {
                    case One:
                        switch(cross)
                        {
                            case Yes:
                                /*Code goes here*/
                                addSequential(new EncoderTurn(ds.getEncoderTicksFromRadians(-Math.PI / 3), 0.55));
                                addSequential(new EncoderDrive(ds.getEncoderTicksFromInches(20.35 * Math.pow(3, 1/2))));
                                /*Code goes here*/
                                break;
                        }
                        break;
                }
                break;
            case Middle:
                switch(gears)
                {
                    case One:
                        switch(cross)
                        {
                            case Yes:
                                /*Code goes here*/
                                /*Code goes here*/
                                break;
                        }
                }
                break;
            case Right:
                switch(gears)
                {
                    case One:
                        switch(cross)
                        {
                            case Yes:
                                /*Code goes here*/
                                addSequential(new EncoderTurn(ds.getEncoderTicksFromRadians(Math.PI / 3), 0.55));
                                addSequential(new EncoderDrive(ds.getEncoderTicksFromInches(20.35 * Math.pow(3, 1/2))));
                                /*Code goes here*/
                                break;
                        }
                }
                break;
        }
    }
    
    /**
     * Selects autonomous route based on defense to cross, position in, and what
     * goal to shoot in.
     * 
     * @param position
     *            enum: Left, Middle, or Right
     * @param gears
     *            enum: One, Two, or Three
     * @param cross
     *            enum: Yes or No
     */
    public Autonomous(final Robot.Position position,
            final Robot.Gears gears, final Robot.CrossLine cross, String extraparamater)
    {
        switch(position)
        {
            case Left:
                switch(gears)
                {
                    case One:
                        switch(cross)
                        {
                            case Yes:
                                break;
                            case No:
                                break;
                        }
                        break;
                    case Two:
                        switch(cross)
                        {
                            case Yes:
                                break;
                            case No:
                                break;
                        }
                        break;
                    case Three:
                        switch(cross)
                        {
                            case Yes:
                                break;
                            case No:
                                break;
                        }
                        break;
                }
                break;
            case Middle:
                switch(gears)
                {
                    case One:
                        switch(cross)
                        {
                            case Yes:
                                break;
                            case No:
                                break;
                        }
                        break;
                    case Two:
                        switch(cross)
                        {
                            case Yes:
                                break;
                            case No:
                                break;
                        }
                        break;
                    case Three:
                        switch(cross)
                        {
                            case Yes:
                                break;
                            case No:
                                break;
                        }
                        break;
                }
                break;
            case Right:
                switch(gears)
                {
                    case One:
                        switch(cross)
                        {
                            case Yes:
                                break;
                            case No:
                                break;
                        }
                        break;
                    case Two:
                        switch(cross)
                        {
                            case Yes:
                                break;
                            case No:
                                break;
                        }
                        break;
                    case Three:
                        switch(cross)
                        {
                            case Yes:
                                break;
                            case No:
                                break;
                        }
                        break;
                }
                break;
        }
    }
}
