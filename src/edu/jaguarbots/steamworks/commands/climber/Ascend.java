package edu.jaguarbots.steamworks.commands.climber;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Retracts the climbing winch
 * 
 * @author Cody Moose
 * @since 2016
 */
public class Ascend extends CommandBase {
	// TODO Add code for limit switch
	public Ascend() {
		requires(climberSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		climberSubsystem.motorForward();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		climberSubsystem.stopMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		climberSubsystem.stopMotor();
	}
}
