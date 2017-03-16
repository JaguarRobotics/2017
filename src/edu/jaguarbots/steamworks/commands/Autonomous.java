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
		double straightSpeed = 0.6;
		double turnSpeed = 0.7;
		DriveSubsystem ds = CommandBase.driveSubsystem;
		switch (position) {
//		  Run this autonomous if we place the robot on the left side of the robot
		case Left:
            addSequential(new EncoderDrive(ds.getAdjustedLength(55), straightSpeed));//110.25 from tall to turn //47.817 KC regional for 60 inches (Length minus robot length)
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-100)));//turn 60 degrees for KC regional
			addSequential(new EncoderDrive(ds.getAdjustedLength(53), straightSpeed));//31 from turn to airship //53 KC regional for 64 inches
			System.out.println("Left Ran");
			break;
//			 Run this auto if we place the robot on the middle of the airship. Also Position right means it will go to the right side while running through auto
		case Middle:
			boolean takeRightPath = middlePosition == Robot.MiddlePosition.Right ? true : false;
			addSequential(new EncoderDrive(ds.getAdjustedLength(62),straightSpeed)); // from wall to airship 111 1/2 inches //57.678 KC regional for 72 inches (Length minus robot length)
			addSequential(new DrivePause(2000));
			addSequential(new EncoderDrive(ds.getAdjustedLength(-38.172), straightSpeed)); // 38.172 KC regional for 48 inches
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(takeRightPath? -200 : 200),turnSpeed));//90 degrees
			addSequential(new EncoderDrive(ds.getAdjustedLength(70),straightSpeed));//52.689 KC regional for 66 inches
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(takeRightPath? 125:-125), turnSpeed));//90 degrees
			addSequential(new EncoderDrive(ds.getAdjustedLength(109),straightSpeed));//109 KC regional for 140 inches
			break;
//			 Run this autonomous if we place the robot on the left side of the robot
		case Right:
			addSequential(new EncoderDrive(ds.getAdjustedLength(57), straightSpeed));//110.25 from tall to turn //47.817 KC regional for 60 inches (Length minus robot length)
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(95)));//turn 60 degrees for KC regional
			addSequential(new EncoderDrive(ds.getAdjustedLength(53), straightSpeed));//31 from turn to airship //53 KC regional for 64 inches
			System.out.println("Right Ran");
			break;
		}
//		Go and do doughnuts during autonomous
//		if (doughnuts == Robot.Doughnuts.Yes)
//			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(314159), 1));
		addSequential(new GearShiftLow());
	}
}
