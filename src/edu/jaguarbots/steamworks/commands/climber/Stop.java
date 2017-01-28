//package edu.jaguarbots.steamworks.commands.climber;
//
//import edu.jaguarbots.steamworks.commands.CommandBase;
//
///**
// * Makes the climber stop 
// * @author Max
// * @since 2017
// */
//public class Stop extends CommandBase
//{
//    public Stop()
//    {
//        requires(climberSubsystem);
//    }
//
//    // Called before this command runs for the first time
//    protected void initalize()
//    {
//    }
//
//    // Called continuously while this command is being run
//    protected void execute()
//    {
//        climberSubsystem.stopMotor();
//    }
//
//    // Is told to return true when the command no longer needs to run
//    protected boolean isFinished()
//    {
//        return false;
//    }
//
//    // Called after isFinished becomes true
//    protected void end()
//    {
//    }
//
//    // Called when the command is interrupted by a different command
//    protected void interrupted()
//    {
//    }
//
//	@Override
//	protected void initialize() {
//		// TODO Auto-generated method stub
//		
//	}
//}
