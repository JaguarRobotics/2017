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
			// addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-180),
			// turnSpeed));
			// addSequential(new EncoderDrive(, straightSpeed));
			// addSequential(new
			// EncoderTurn(ds.getEncoderTicksFromRadians(ds.getRadiansFromDegrees(-360)),
			// turnSpeed));
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
			/* Code goes here */
			/*
			 * addSequential(new EncoderTurn(-Math.PI / 3, turnSpeed));
			 * addSequential(new EncoderDrive(20.35 * Math.pow(3, 1/2),
			 * straightSpeed));
			 */
			// addSequential(new GearShiftLow());

			addSequential(new EncoderDrive(68, straightSpeed)); // 87
			addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(-205), turnSpeed)); // 75
			addSequential(new EncoderDrive(18, straightSpeed)); // 33
			addSequential(new DrivePause(5000));
			// addSequential(new EncoderDrive(-39, straightSpeed));
			// addSequential(new EncoderTurn(ds.getRadiansFromDegrees(120),
			// turnSpeed));
			// addSequential(new EncoderDrive(140, straightSpeed));
			/* Code goes here */
			break;
		case Middle:
			boolean takeRightPath = middlePosition == Robot.MiddlePosition.Right ? true : false;
			/* Code goes here */
			// addSequential(new GearShiftLow());
			addSequential(new EncoderDrive(takeRightPath ? 93.3 - 31 + 15.25 : 93.3 - 31 + 12, straightSpeed));
			addSequential(new DrivePause(2000));
			addSequential(new EncoderDrive(-36, straightSpeed));
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(takeRightPath ? -97 : 97), turnSpeed));
			addSequential(new EncoderDrive(72, straightSpeed));
			addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(takeRightPath ? 160 : -160), turnSpeed));
			addSequential(new EncoderDrive(takeRightPath ? 36 : 12, straightSpeed));
			addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(takeRightPath ? 150 : -150), turnSpeed));
			addSequential(new EncoderDrive(takeRightPath ? 60 : 120, straightSpeed));
			/* Code goes here */
			break;
		case Right:
			addSequential(new EncoderDrive(125, straightSpeed));
			
//			addSequential(new EncoderDrive(70, straightSpeed)); // 87
//			addSequential(new EncoderArcTurn(ds.getRadiansFromDegrees(200), turnSpeed)); // 75
//			addSequential(new EncoderDrive(20, straightSpeed)); // 33
//			addSequential(new DrivePause(5000));
			
			
			// addSequential(new EncoderDrive(-39, straightSpeed));
			// addSequential(new EncoderTurn(ds.getRadiansFromDegrees(-130),
			// turnSpeed));
			// addSequential(new EncoderDrive(120, straightSpeed));
			/* Code goes here */
			break;
		}
		if (doughnuts == Robot.Doughnuts.Yes)
			addSequential(new EncoderTurn(ds.getRadiansFromDegrees(314159), 1)); // Delicousness
		addSequential(new GearShiftLow());
	}
}
