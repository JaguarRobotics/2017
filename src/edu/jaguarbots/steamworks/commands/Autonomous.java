package edu.jaguarbots.steamworks.commands;

import edu.jaguarbots.steamworks.Robot;
import edu.jaguarbots.steamworks.commands.drive.DrivePause;
import edu.jaguarbots.steamworks.commands.drive.EncoderArcTurn;
import edu.jaguarbots.steamworks.commands.drive.EncoderDrive;
import edu.jaguarbots.steamworks.commands.drive.EncoderTurn;
import edu.jaguarbots.steamworks.commands.drive.GearShiftLow;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {
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
	public Autonomous() {
		double straightSpeed = 0.7;
		double turnSpeed = 0.63;
		DriveSubsystem ds = CommandBase.driveSubsystem;
		boolean testing = false;
		if (testing) {
			addSequential(new GearShiftLow());
			safePause(250);
			addSequential(new EncoderDrive(93, straightSpeed));
			safePause(500);
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(60), turnSpeed));
			safePause(500);
			addSequential(new EncoderDrive(30, straightSpeed));
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
	public Autonomous(final Robot.Position position, final Robot.MiddlePosition middlePosition,
			final Robot.Doughnuts doughnuts) {
		double straightSpeed = 0.7;
		double turnSpeed = 0.7;
		DriveSubsystem ds = CommandBase.driveSubsystem;
		// addSequential(new GearShiftLow());
		// addSequential(new EncoderDrive(93.3 - 31 + 15.25, straightSpeed));
		switch (position) {
		case Left:
		    addSequential(new EncoderDrive(106.86, straightSpeed));
            addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-60)));
            addSequential(new EncoderDrive(9.44, straightSpeed));
			break;
		case Middle:
			boolean takeRightPath = middlePosition == Robot.MiddlePosition.Right ? true : false;
			addSequential(new EncoderDrive(71.54, straightSpeed));
			addSequential(new DrivePause(2000));
			addSequential(new EncoderDrive(-22.55, straightSpeed));
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(takeRightPath?90 : -90), turnSpeed));
			addSequential(new EncoderDrive(14.71,straightSpeed));
			addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(takeRightPath?-120:120), turnSpeed));
			addSequential(new EncoderDrive(63.73, straightSpeed));
			
			break;
		case Right:
            addSequential(new EncoderDrive(106.86, straightSpeed));
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(60)));
			addSequential(new EncoderDrive(9.44, straightSpeed));
			break;
		}
		if (doughnuts == Robot.Doughnuts.Yes)
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(314159), 1)); // Delicousness
		addSequential(new GearShiftLow());
	}
}
