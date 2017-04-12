package edu.jaguarbots.steamworks;

import edu.jaguarbots.steamworks.commands.Autonomous;
import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. If you change the name of this class or the package after creating this project, you must also update the manifest file in the resource directory.
 */
@SuppressWarnings("rawtypes")
public class Robot extends IterativeRobot {
	/**
	 * The auto command we choose to run.
	 * 
	 * @since 2017
	 */
	private Command autonomousCommand;
	/**
	 * The chooser used on the SmartDashboard to choose whether or not to record for auto.
	 * 
	 * @since 2017
	 */
	public static final SendableChooser recordingChooser = new SendableChooser();
	/**
	 * The chooser used on the SmartDashboard to choose the starting position.
	 * 
	 * @since 2017
	 */
	public static final SendableChooser positionChooser = new SendableChooser();
	/**
	 * The chooser used in SmartDashboard to choose which alliance we are on.
	 * 
	 * @since 2017
	 */
	public static final SendableChooser allianceChooser = new SendableChooser();
	/**
	 * The chooser used on the SmartDashboard to choose the starting position.
	 * 
	 * @since 2017
	 */
	public static final SendableChooser middlePositionChooser = new SendableChooser();

	/**
	 * The enum constants used with the positionChooser.
	 * 
	 * @since 2017
	 */
	public enum Position {
		Left, Middle, Right, testLength, None, Forward
	}

	/**
	 * The enum constants to choose to use recording or not
	 * 
	 * @since 2017
	 */
	public enum Recording {
		RecordNewAuto, UseExistingAuto
	}

	/**
	 * The enum constants used with the middlePositionChooser.
	 * 
	 * @since 2017
	 */
	public enum MiddlePosition {
		Left, Right, Stay
	}

	/**
	 * The enum constants to choose which alliance we are on
	 * 
	 * @since 2017
	 */
	public enum Alliance {
		Blue, Red
	}

	/**
	 * The enum constants used with the doughnutsChooser.
	 * 
	 * @since 2017
	 */
	public enum Doughnuts {
		Yes, No
	}

	/**
	 * This function is ran when the robot is first started up and should be used for any initialization code. We are using this to initialize CommandBase and to populate the SmartDashboard.
	 */
	@SuppressWarnings("unchecked")
	public void robotInit() {
		try {
			CommandBase.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		recordingChooser.addDefault("Record New Auto", Recording.RecordNewAuto);
		recordingChooser.addObject("Run Existing Auto", Recording.UseExistingAuto);
		allianceChooser.addDefault("Blue", Alliance.Blue);
		allianceChooser.addObject("Red", Alliance.Red);
		positionChooser.addDefault("Left", Position.Left);
		positionChooser.addObject("Middle", Position.Middle);
		positionChooser.addObject("Right", Position.Right);
		positionChooser.addObject("No Auto", Position.None);
		positionChooser.addObject("Just Forward", Position.Forward);
		middlePositionChooser.addDefault("Left", MiddlePosition.Left);
		middlePositionChooser.addObject("Right", MiddlePosition.Right);
		middlePositionChooser.addObject("No-Break", MiddlePosition.Stay);
		SmartDashboard.putData("Recording", recordingChooser);
		SmartDashboard.putData("Alliance", allianceChooser);
		SmartDashboard.putData("Position", positionChooser);
		SmartDashboard.putData("MiddlePosition", middlePositionChooser);
		SmartDashboard.putNumber("Joystick Tolerance", 1);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You can use it to reset any subsystem information you want to clear when the robot is disabled.
	 */
	public void disabledInit() {
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function runs at the start of every autonomous allowing us to call Autonomous and get the positions that we have set for autonomous.
	 */
	public void autonomousInit() {
		// CommandBase.driveSubsystem.initEncoders();
		Recording recording = (Recording) recordingChooser.getSelected();
		Position position = (Position) positionChooser.getSelected();
		MiddlePosition middlePosition = (MiddlePosition) middlePositionChooser.getSelected();
		Alliance alliance = (Alliance) allianceChooser.getSelected();
		autonomousCommand = new Autonomous(recording, position, middlePosition, alliance);
		System.out.println("Created Auto");
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This functions runs when you enable teleop
	 */
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
