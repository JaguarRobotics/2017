package edu.jaguarbots.steamworks.commands;

import edu.jaguarbots.steamworks.Robot;
import edu.jaguarbots.steamworks.commands.drive.DrivePause;
import edu.jaguarbots.steamworks.commands.drive.EncoderDrive;
import edu.jaguarbots.steamworks.commands.drive.EncoderTurn;
import edu.jaguarbots.steamworks.commands.drive.GearShiftLow;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The Code to run during the autonomous section of a match.
 *	@author Brian, Nathan, Cody
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
//			Put anything you want to test in here and it will run.
		}
	}

	/**
	 * Selects autonomous route based on defense to cross, position in, and what
	 * goal to shoot in. See a picture below
	 * <html><img src="https://puu.sh/tWete/63e013aebf.png"></img></html>
	 * 
	 * @param position
	 *            enum: Left, Middle, or Right
	 * @param middlePosition
	 *            enum: Left, Right
	 * @param doughnuts
	 *            enum: Yes, No
	 */
	@SuppressWarnings("incomplete-switch")
	public Autonomous(final Robot.Position position, final Robot.MiddlePosition middlePosition,
			final Robot.Doughnuts doughnuts) {
		new GearShiftLow();
		double straightSpeed = 0.7;
		double turnSpeed = 0.7;
		DriveSubsystem ds = CommandBase.driveSubsystem;
		switch (position) {
//		  Run this autonomous if we place the robot on the left side of the robot
		case Left:
            addSequential(new EncoderDrive(ds.getAdjustedLength(73), straightSpeed));//110.25 from tall to turn
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-75)));
			addSequential(new EncoderDrive(ds.getAdjustedLength(23.22), straightSpeed));//31 from turn to airship
			break;
//			 Run this auto if we place the robot on the middle of the airship. Also Position right means it will go to the right side while running through auto
		case Middle:
			boolean takeRightPath = middlePosition == Robot.MiddlePosition.Right ? true : false;
			addSequential(new EncoderDrive(ds.getAdjustedLength(65.638),straightSpeed)); // from wall to airship 111 1/2 inches
			addSequential(new DrivePause(2000));
			addSequential(new EncoderDrive(ds.getAdjustedLength(-34.95), straightSpeed));
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(takeRightPath?-115 : 115),turnSpeed));//90 degrees
			addSequential(new EncoderDrive(ds.getAdjustedLength(47.817),straightSpeed));
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(takeRightPath?115:-115), turnSpeed));//90 degrees
			addSequential(new EncoderDrive(ds.getAdjustedLength(102.63),straightSpeed));
			break;
//			 Run this autonomous if we place the robot on the left side of the robot
		case Right:
            addSequential(new EncoderDrive(ds.getAdjustedLength(74.776), straightSpeed));//110.25 from wall to turn
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(66)));
			addSequential(new EncoderDrive(ds.getAdjustedLength(23.22), straightSpeed));//31 from turn to airship
			break;
		}
//		Go and do doughnuts during autonomous
		if (doughnuts == Robot.Doughnuts.Yes)
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(314159), 1));
		addSequential(new GearShiftLow());
	}
}
