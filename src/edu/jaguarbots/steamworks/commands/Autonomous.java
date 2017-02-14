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
	
	private void safePause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     * Default, does nothing as of yet
     */
    public Autonomous()
    {
    	double straightSpeed = 0.55;
    	double turnSpeed = 0.63;
        DriveSubsystem ds = CommandBase.driveSubsystem;
    	boolean testing = false;
    	if(testing) {
//            addSequential(new GearShiftLow());
//            safePause(250);
//            addSequential(new EncoderDrive(93, straightSpeed));
//            safePause(500);
//            addSequential(new EncoderTurn(ds.getRadiansFromDegrees(60), turnSpeed));
//            safePause(500);
//            addSequential(new EncoderDrive(30, straightSpeed));
//			Start of switch to arc turns
            addSequential(new GearShiftLow());
            safePause(250);
            addSequential(new EncoderDrive(93, straightSpeed));
            safePause(500);
            addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(60), turnSpeed));
            safePause(500);
            addSequential(new EncoderDrive(30, straightSpeed));
//            End of Switch to arc turns
//    		Already Commented out
//            addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-180), turnSpeed));
//            addSequential(new EncoderDrive(, straightSpeed));
//            addSequential(new EncoderTurn(ds.getEncoderTicksFromRadians(ds.getRadiansFromDegrees(-360)), turnSpeed));
    	}
    	else {
    	    new Autonomous(Robot.Position.Middle, Robot.Gears.One, Robot.CrossLine.Yes);
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
    	double straightSpeed = 0.7;
    	double turnSpeed = 0.7;
        DriveSubsystem ds = CommandBase.driveSubsystem;
//        addSequential(new GearShiftLow());
//        addSequential(new EncoderDrive(93.3 - 31 + 15.25, straightSpeed));
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
                            	/*
                                addSequential(new EncoderTurn(-Math.PI / 3, turnSpeed));
                                addSequential(new EncoderDrive(20.35 * Math.pow(3, 1/2), straightSpeed));
                                */
//                                addSequential(new GearShiftLow());
//                            	Original Code
//                                addSequential(new EncoderDrive(93.3, straightSpeed));
//                                addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-65), turnSpeed));
//                                addSequential(new EncoderDrive(27, straightSpeed));
//                                addSequential(new DrivePause(5000));
//                                addSequential(new EncoderDrive(-39, straightSpeed));
//                                addSequential(new EncoderTurn(ds.getRadiansFromDegrees(120), turnSpeed));
//                                addSequential(new EncoderDrive(108, straightSpeed));
//                                Start of Adjusted Code
                                addSequential(new EncoderDrive(108.55, straightSpeed));
                                addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(60), turnSpeed));
                                addSequential(new EncoderDrive(35.25, straightSpeed));
                                addSequential(new DrivePause(5000));
                                addSequential(new EncoderDrive(-35.25, straightSpeed));
                                addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(-60), turnSpeed));
                                addSequential(new EncoderDrive(76.75, straightSpeed));
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
                            	boolean takeRightPath = false;
                                /*Code goes here*/
//                                addSequential(new GearShiftLow());
//                            	Original Code
//                                addSequential(new EncoderDrive(takeRightPath ? 93.3 - 31 + 15.25 : 93.3 - 31 + 12, straightSpeed));
//                                addSequential(new DrivePause(2000));
//                                addSequential(new EncoderDrive(-36, straightSpeed));
//                                addSequential(new EncoderTurn(ds.getRadiansFromDegrees(takeRightPath ? -97 : 130), turnSpeed));
//                                addSequential(new EncoderDrive(72, straightSpeed));
//                                addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(takeRightPath ? 160 : -280), turnSpeed));
//                                addSequential(new EncoderDrive(takeRightPath ? 36 : 12, straightSpeed));
//                                addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(takeRightPath ? 150 : -180), turnSpeed));
//                                addSequential(new EncoderDrive(takeRightPath ? 60 : 120, straightSpeed));
//                              Start of Adjusted Code
                                addSequential(new EncoderDrive(93.3, straightSpeed));
                                addSequential(new DrivePause(2000));
                                addSequential(new EncoderDrive(-36, straightSpeed));
                                addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(takeRightPath ? -90 : 90), turnSpeed));
                                addSequential(new EncoderDrive(72, straightSpeed));
                                addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(takeRightPath ? 45 : -45), turnSpeed));
                                addSequential(new EncoderDrive(36, straightSpeed));
                                addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(takeRightPath ? 45 : -45), turnSpeed));
                                addSequential(new EncoderDrive(takeRightPath ? 60 : 120, straightSpeed));
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
                            	/*
                                addSequential(new EncoderTurn(Math.PI / 3, turnSpeed));
                                addSequential(new EncoderDrive(20.35 * Math.pow(3, 1/2), straightSpeed));
                            	*/
//                                addSequential(new GearShiftLow());
//                            	Start of Original Code
//                                addSequential(new EncoderDrive(87, straightSpeed));
//                                addSequential(new EncoderTurn(ds.getRadiansFromDegrees(80), turnSpeed));
//                                addSequential(new EncoderDrive(33, straightSpeed));
//                                addSequential(new DrivePause(5000));
//                                addSequential(new EncoderDrive(-39, straightSpeed));
//                                addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-80), turnSpeed));
//                                addSequential(new EncoderDrive(108, straightSpeed));
//                                Start of Adjusted Code
                                addSequential(new EncoderDrive(108.55, straightSpeed));
                                addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(-60), turnSpeed));
                                addSequential(new EncoderDrive(35.25, straightSpeed));
                                addSequential(new DrivePause(5000));
                                addSequential(new EncoderDrive(-35.25, straightSpeed));
                                addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(60), turnSpeed));
                                addSequential(new EncoderDrive(76.75, straightSpeed));
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
