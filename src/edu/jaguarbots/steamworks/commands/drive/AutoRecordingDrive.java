package edu.jaguarbots.steamworks.commands.drive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import edu.jaguarbots.steamworks.Robot;
import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives the robot in teleop based on left and right joystick inputs.
 */
public class AutoRecordingDrive extends CommandBase {
	public AutoRecordingDrive() {
		requires(driveSubsystem);
	}

	double left;
	double right;
	private ArrayList<String> commands = new ArrayList<String>();
	private ArrayList<Double> encoderTicks = new ArrayList<Double>();
	private boolean isTurning = false;

	@Override
	protected void initialize() {
		driveSubsystem.resetEncoders(true, true);
		driveSubsystem.startEncoders();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute() {
		SmartDashboard.putNumber("EncoderLeft", CommandBase.driveSubsystem.getEncoderLeft());
		SmartDashboard.putNumber("EncoderRight", CommandBase.driveSubsystem.getEncoderRight());
		double powNum = 2;
		double pointNum = SmartDashboard.getNumber("Joystick Tolerance");
		double j0 = oi.Joystick0.getY() * pointNum;
		double j1 = oi.Joystick1.getY() * pointNum;
		double ju1 = (Math.abs(j0) > Math.abs(j1)) ? j0 : j1;
		j0 = oi.Joystick0.getX() * pointNum;
		j1 = oi.Joystick1.getX() * pointNum;
		double ju2 = (Math.abs(j0) > Math.abs(j1)) ? j0 : j1;
		boolean isTurn = (Math.abs(ju1) > Math.abs(ju2)) ? false : true;
		double ju = isTurn ? ju2 : ju1;
		ju = (Math.abs(j0) > Math.abs(j1)) ? j0 : j1;
		double aju = Math.abs(ju);
		double pju = Math.pow(aju, powNum);
		if (Math.abs(pju) > aju)
			pju = aju;
		double power = (pju * (aju / ju)) / pointNum;
		if (isTurn)
			driveSubsystem.driveTank(-power, -power);
		else {
			if (power > 0)
				driveSubsystem.driveTank(power, -power);
			else
				driveSubsystem.driveTank(-power, power);
		}
		left = driveSubsystem.getEncoderLeft();
		right = driveSubsystem.getEncoderRight();
		if (isTurning == isTurn) {
			if (isTurn)
				commands.add("turn");
			else
				commands.add("straight");
			double jrsgn = driveSubsystem.getEncoderRight() / Math.abs(driveSubsystem.getEncoderRight());
			double average = (Math.abs(driveSubsystem.getEncoderLeft()) + Math.abs(driveSubsystem.getEncoderRight())) / 2;
			encoderTicks.add(jrsgn * average);
			driveSubsystem.resetEncoders(true, true);
			driveSubsystem.startEncoders();
		}
		isTurning = isTurn;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		String fileName = "";
		try {
			if (Robot.positionChooser.getSelected() == Robot.Position.Middle) {
				fileName = "/home/lvuser/" + Robot.positionChooser.getSelected().toString() + Robot.middlePositionChooser.getSelected().toString() + Robot.allianceChooser.getSelected().toString();
			} else {
				fileName = "/home/lvuser/" + Robot.positionChooser.getSelected().toString() + Robot.allianceChooser.getSelected().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File(fileName + ".txt");
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		OutputStreamWriter ow = new OutputStreamWriter(fs);
		BufferedWriter writer = new BufferedWriter(ow);
		for (int i = 0; i < commands.size(); i++) {
			try {
				if (commands.get(i) == "straight")
					writer.write("addSequential(new EncoderDrive(" + encoderTicks.get(i) + ", straightSpeed));");
				else if (commands.get(i) == "straight")
					writer.write("addSequential(new EncoderTurn(" + encoderTicks.get(i) + "));");
			} catch (IOException e) {
			}
		}
		try {
			writer.flush();
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}