package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives the robot in teleop based on left and right joystick inputs.
 */
public class DriveTank extends CommandBase {
    /**
     * Below is an image of the idea we are using for joystick vs motor power. <html><img src="https://puu.sh/tEhvx/a211c4f7a1.png"></img></html>
     */
    public DriveTank() {
	requires(driveSubsystem);
    }
    double left;
    double right;
    @Override
    protected void initialize() {
    }
    /**
     * Below is an image of the idea we are using for joystick vs motor power. <html><img src="https://puu.sh/tEhvx/a211c4f7a1.png"></img></html>
     */
    @Override
    @SuppressWarnings("deprecation")
    protected void execute() {
	SmartDashboard.putNumber("EncoderLeft", CommandBase.driveSubsystem.getEncoderLeft());
	SmartDashboard.putNumber("EncoderRight", CommandBase.driveSubsystem.getEncoderRight());
	double powNum = 2;
	double pointNum = SmartDashboard.getNumber("Joystick Tolerance");
	double j0 = oi.Joystick0.getY() * pointNum;
	double j1 = oi.Joystick1.getY() * pointNum;
	double aj0 = Math.abs(j0);
	double aj1 = Math.abs(j1);
	double pj0 = Math.pow(aj0, powNum);
	double pj1 = Math.pow(aj1, powNum);
	if (Math.abs(pj0) > aj0)
	    pj0 = aj0;
	if (Math.abs(pj1) > aj1)
	    pj1 = aj1;
	left = (pj0 * (aj0 / j0)) / pointNum;
	right = (pj1 * (aj1 / j1)) / pointNum;
	System.out.print("Joystick: " + j0 + ", " + j1);
	System.out.print("CodeNumber: " + left + ", " + right + "\n");
	driveSubsystem.driveTank(-left, -right);
    }
    @Override
    protected boolean isFinished() {
	return false;
    }
    @Override
    protected void end() {
    }
    @Override
    protected void interrupted() {
    }
}
