// package edu.jaguarbots.steamworks.commands.climber;
//
// import edu.jaguarbots.steamworks.commands.CommandBase;
// import edu.wpi.first.wpilibj.Timer;
//
/// **
// *
// */
// public class ClimberControl extends CommandBase {
//
// double direction;
// boolean on = false;
// double state;
// Timer timer = new Timer();
//
// public ClimberControl() {
// requires(climberSubsystem);
// }
//
// protected void initialize() {
// }
//
// protected void execute() {
// direction = oi.Manipulator.getPOV(0);
// if(direction < 22.5 || direction >= 337.5){
// new Ascend();
// } else if(direction >= 157.5 && direction < 202.5){
// new Descend();
// }
// }
//
// protected boolean isFinished() {
// return false;
// }
//
// protected void end() {
// }
//
// protected void interrupted() {
// }
// }
