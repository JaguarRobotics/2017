package edu.jaguarbots.steamworks.commands.climber;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Retracts the climbing winch
 * 
 * @author Cody Moose
 * @since 2016
 */
public class Ascend extends CommandBase {
    public Ascend() {
	requires(climberSubsystem);
    }
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
	climberSubsystem.motorForward();
    }
    @Override
    protected boolean isFinished() {
	return false;
    }
    @Override
    protected void end() {
	climberSubsystem.stopMotor();
    }
    @Override
    protected void interrupted() {
	climberSubsystem.stopMotor();
    }
}
