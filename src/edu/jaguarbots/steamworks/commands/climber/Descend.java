package edu.jaguarbots.steamworks.commands.climber;

import edu.jaguarbots.steamworks.commands.CommandBase;

/**
 * Extends the climbing winch
 * 
 * @author Cody Moose
 * @since 2016
 */
public class Descend extends CommandBase {
    public Descend() {
	requires(climberSubsystem);
    }
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
	climberSubsystem.motorBackward();
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