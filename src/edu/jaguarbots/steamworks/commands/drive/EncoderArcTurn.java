package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns the robot using the values on the encoders in an Arc shape rather than in place
 * 
 * @author Nathan Gawith
 * @since 2017
 * @version 2017
 */
public class EncoderArcTurn extends CommandBase {
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
	private double arclength;

	/**
	 * Just used for System.outs
	 */
	private int counter = 0;

	@Override
	protected void initialize() {
		driveSubsystem.resetEncoders(true, true);
		driveSubsystem.startEncoders();
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("EncoderLeft", CommandBase.driveSubsystem.getEncoderLeft());
		SmartDashboard.putNumber("EncoderRight", CommandBase.driveSubsystem.getEncoderRight());
		boolean correctIt = false;
		double[] powers = driveSubsystem.getMotorPowers();
		double left, right;
		if (correctIt) {
			left = -speed * powers[0];
			right = speed * powers[1];
		} else {
			left = -speed;
			right = speed;
		}
		driveSubsystem.driveTank(left < 0 ? 0 : left, right < 0 ? 0 : right);
		// if (angle > 0)
		// {
		// driveSubsystem.driveTank(-speed, speed);
		// }
		// else if (angle < 0)
		// {
		// driveSubsystem.driveTank(-speed, speed);
		// }
	}

	@Override
	protected boolean isFinished() {
		double left = Math.abs(driveSubsystem.getEncoderLeft());
		double right = Math.abs(driveSubsystem.getEncoderRight());
		if (counter % 5 == 0)
			System.out.println("left: " + left + "  right: " + right + "  angle: " + arclength);
		return Math.max(left, right) > arclength;
		// return Math.max(driveSubsystem.getDistanceInInches(left),
		// driveSubsystem.getDistanceInInches(Math.abs(right))) > angle *
		// ROBOT_WIDTH / 2;
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
	 *            The angle to turn in radians (positive is to the left,
	 *            negative is to the right)
	 * @param speed
	 *            The speed at which to turn
	 * @since 2017
	 */
	public EncoderArcTurn(double angle, double speed) {
		requires(driveSubsystem);
		// this.speed = speed;
		this.speed = (angle < 0) ? -1 * Math.abs(speed) : Math.abs(speed);
		// this.arclength = Math.abs(angle * ROBOT_WIDTH / 2);
		this.arclength = Math.abs(angle * 13.25);
	}

	/**
	 * Turns the robot at a speed of 0.7
	 * 
	 * @param angle
	 *            The angle to turn in radians (positive is to the left,
	 *            negative is to the right)
	 * @since 2017
	 */
	public EncoderArcTurn(double angle) {
		this(angle, 0.7);
	}
}
