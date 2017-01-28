//package edu.jaguarbots.steamworks.commands.climber;
//
//import edu.jaguarbots.steamworks.commands.CommandBase;
//
///**
// *
// */
//public class Down extends CommandBase {
//
//    public Down() {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(climberSubsystem);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	climberSubsystem.solDown();
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return true;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
