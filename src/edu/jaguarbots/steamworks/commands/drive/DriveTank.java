package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives the robot in teleop based on left and right joystick inputs.
 */
public class DriveTank extends CommandBase {
	public DriveTank() {
		requires(driveSubsystem);
	}

	double left;
	double right;

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	@SuppressWarnings("deprecation")
	protected void execute() {
		SmartDashboard.putNumber("EncoderLeft", CommandBase.driveSubsystem.getEncoderLeft());
		SmartDashboard.putNumber("EncoderRight", CommandBase.driveSubsystem.getEncoderRight());
		double powNum = 2;
		double pointNum = SmartDashboard.getNumber("Joystick Tolerance");
		double joystick0 = oi.Joystick0.getY() * pointNum;
		double joystick1 = oi.Joystick1.getY() * pointNum;
		double absoluteOfJoystick0 = Math.abs(joystick0);
		double absoluteOfJoystick1 = Math.abs(joystick1);
		double powerOfJoystick0 = Math.pow(absoluteOfJoystick0, powNum);
		double powerOfJoystick1 = Math.pow(absoluteOfJoystick1, powNum);
		if (Math.abs(powerOfJoystick0) > absoluteOfJoystick0)
			powerOfJoystick0 = absoluteOfJoystick0;
		if (Math.abs(powerOfJoystick1) > absoluteOfJoystick1)
			powerOfJoystick1 = absoluteOfJoystick1;
		left = (powerOfJoystick0 * (absoluteOfJoystick0 / joystick0)) / pointNum;
		right = (powerOfJoystick1 * (absoluteOfJoystick1 / joystick1)) / pointNum;
		driveSubsystem.driveTank(-left, -right);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
