package edu.jaguarbots.steamworks.commands;

import edu.jaguarbots.steamworks.Robot;
import edu.jaguarbots.steamworks.commands.drive.DrivePause;
import edu.jaguarbots.steamworks.commands.drive.EncoderDrive;
import edu.jaguarbots.steamworks.commands.drive.EncoderTurn;
import edu.jaguarbots.steamworks.commands.drive.GearDoorOpen;
import edu.jaguarbots.steamworks.commands.drive.GearShiftLow;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The Code to run during the autonomous section of a match.
 * 
 * @author Brian, Nathan, Cody
 */
public class Autonomous extends CommandGroup {
    /**
     * This is the default autonomous, anything you put in here will end up running as auto in testing
     */
    @SuppressWarnings("unused")
    public Autonomous() {
	double straightSpeed = 0.7;
	double turnSpeed = 0.7;
	DriveSubsystem ds = CommandBase.driveSubsystem;
	boolean testing = false;
	if (testing) {
	}
    }
    /**
     * Selects autonomous route based on defense to cross, position in, and what goal to shoot in. See a picture below <html><img src="https://puu.sh/tWete/63e013aebf.png"></img></html>
     * 
     * @param position
     *            enum: Left, Middle, or Right
     * @param middlePosition
     *            enum: Left, Right
     * @param doughnuts
     *            enum: Yes, No
     */
    @SuppressWarnings("incomplete-switch")
    public Autonomous(final Robot.Position position, final Robot.MiddlePosition middlePosition, final Robot.Doughnuts doughnuts, final Robot.Alliance alliance) {
	addSequential(new GearDoorOpen());
	addSequential(new GearShiftLow());
	double straightSpeed = 0.6;
	DriveSubsystem ds = CommandBase.driveSubsystem;
	switch (position) {
	    case Left:
		switch (alliance) {
		    case Blue:
			addSequential(new EncoderDrive(83, straightSpeed));
			addSequential(new DrivePause(1000));
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(60)));
			addSequential(new DrivePause(1000));
			addSequential(new EncoderDrive(47, straightSpeed));
		    break;
		    case Red:
			addSequential(new EncoderDrive(83, straightSpeed));
			addSequential(new DrivePause(1000));
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-60)));
			addSequential(new DrivePause(1000));
			addSequential(new EncoderDrive(47, straightSpeed));
		    break;
		}
	    break;
	    case Middle:
		addSequential(new EncoderDrive(102, straightSpeed));
	    break;
	    case Right:
		switch (alliance) {
		    case Blue:
			addSequential(new EncoderDrive(83, straightSpeed));
			addSequential(new DrivePause(1000));
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-60)));
			addSequential(new DrivePause(1000));
			addSequential(new EncoderDrive(47, straightSpeed));
		    break;
		    case Red:
			addSequential(new EncoderDrive(83, straightSpeed));
			addSequential(new DrivePause(1000));
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-60)));
			addSequential(new DrivePause(1000));
			addSequential(new EncoderDrive(47, straightSpeed));
		    break;
		}
	    break;
	    case None:
	    break;
	    case Forward:
		addSequential(new EncoderDrive(100, straightSpeed));
	    break;
	}
	addSequential(new GearShiftLow());
	addSequential(new GearDoorOpen());
    }
}
