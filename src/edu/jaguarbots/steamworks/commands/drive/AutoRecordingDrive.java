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
	/**
	 * Below is an image of the idea we are using for joystick vs motor power. <html><img src="https://puu.sh/tEhvx/a211c4f7a1.png"></img></html>
	 */
	public AutoRecordingDrive() {
		requires(driveSubsystem);
	}

	double left;
	double right;
	private ArrayList<String> commands = new ArrayList<String>();
	private ArrayList<Double> encoderTicks = new ArrayList<Double>();
	private boolean isTurning = false;

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		driveSubsystem.resetEncoders(true, true);
		driveSubsystem.startEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
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
		/*
		 * // code for Y axis double j0 = oi.Joystick0.getY() * pointNum; double j1 = oi.Joystick1.getY() * pointNum; double ju = (Math.abs(j0) > Math.abs(j1)) ? j0 : j1; double aju = Math.abs(ju); double pju = Math.pow(aju, powNum); if (Math.abs(pju) > aju) pju = aju; if (Math.abs(pju) > aju) pju = aju; double straight = (pju * (aju / ju)) / pointNum; // code for X axis j0 = oi.Joystick0.getX() * pointNum; j1 = oi.Joystick1.getX() * pointNum; ju = (Math.abs(j0) > Math.abs(j1)) ? j0 : j1; aju = Math.abs(ju); pju = Math.pow(aju, powNum); if (Math.abs(pju) > aju) pju = aju; if (Math.abs(pju) > aju) pju = aju; double turn = (pju * (aju / ju)) / pointNum; if (Math.abs(straight) > Math.abs(turn)) driveSubsystem.driveTank(-straight, -straight); else { if (turn > 0) driveSubsystem.driveTank(turn, -turn); else driveSubsystem.driveTank(-turn, turn); }
		 */
		// rewrite
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
		// Gets what the auto is going to be
		String output = "";
		for (int i = 0; i < commands.size(); i++) {
			output += commands.get(i) + " " + encoderTicks.get(i);
		}
		output += "done";

		// Figures out what to call the File
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
		// Creates the File and the output Stream

		File file = new File(fileName + ".txt");
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		OutputStreamWriter ow = new OutputStreamWriter(fs);
		BufferedWriter writer = new BufferedWriter(ow);

		// Prints out the commands and the encoder Values into the File
		for (int i = 0; i < commands.size(); i++) {
			try {
				if (commands.get(i) == "straight")
					writer.write("addSequential(new EncoderDrive(" + encoderTicks.get(i) + ", straightSpeed));");
				else if (commands.get(i) == "straight")
					writer.write("addSequential(new EncoderTurn(" + encoderTicks.get(i) + "));");
			} catch (IOException e) {
			}
		}

		// Flushes out the data and closes the stream
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

	public static void writeFile(String fileName, String content) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}