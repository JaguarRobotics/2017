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
public class Robot extends IterativeRobot {
    private static final long START_TIME = System.currentTimeMillis();
    private Command autonomousCommand;
    /**
     * chooser used on the SmartDashboard to choose the starting position
     * 
     * @since 2017
     */
    private final SendableChooser positionChooser = new SendableChooser();
    /**
     * Chooser used in SmartDashboard to choose which alliance we are on
     * 
     * @since 2017
     */
    private final SendableChooser allianceChooser = new SendableChooser();
    /**
     * chooser used on the SmartDashboard to choose the starting position
     * 
     * @since 2017
     */
    private final SendableChooser middlePositionChooser = new SendableChooser();
    /**
     * chooser used on the SmartDashboard to choose the whether or not to do doughnuts at the end of Autonomous.
     * 
     * @since 2017
     */
    private final SendableChooser doughnutsChooser = new SendableChooser();
    /**
     * enum constants used with the positionChooser.
     * 
     * @since 2017
     */
    public enum Position {
	Left, Middle, Right, testLength, None, Forward
    }
    /**
     * enum constants used with the middlePositionChooser.
     * 
     * @since 2017
     */
    public enum MiddlePosition {
	Left, Right, Stay
    }
    /**
     * enum constants to choose which alliance we are on
     * 
     * @since 2017
     */
    public enum Alliance {
	Blue, Red
    }
    /**
     * enum constants used with the doughnutsChooser.
     * 
     * @since 2017
     */
    public enum Doughnuts {
	Yes, No
    }
    /**
     * enum constants of gate being open or closed
     * 
     * @since 2017
     */
    public enum Gate {
	Open, Closed
    }
    /**
     * enum constants of gear high or low
     * 
     * @since 2017
     */
    public enum Gear {
	Low, High
    }
    /**
     * This function is run when the robot is first started up and should be used for any initialization code. We are using this to initialize CommandBase and to populate the SmartDashboard.
     */
    public void robotInit() {
	try {
	    CommandBase.init();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	allianceChooser.addDefault("Blue", Alliance.Blue);
	allianceChooser.addObject("Red", Alliance.Red);
	SmartDashboard.putData("Alliance", allianceChooser);
	positionChooser.addDefault("Left", Position.Left);
	positionChooser.addObject("Middle", Position.Middle);
	positionChooser.addObject("Right", Position.Right);
	positionChooser.addObject("No Auto", Position.None);
	positionChooser.addObject("Just Forward", Position.Forward);
	SmartDashboard.putData("Position", positionChooser);
	middlePositionChooser.addDefault("Left", MiddlePosition.Left);
	middlePositionChooser.addObject("Right", MiddlePosition.Right);
	middlePositionChooser.addObject("No Break", MiddlePosition.Stay);
	SmartDashboard.putData("MiddlePosition", middlePositionChooser);
	SmartDashboard.putData("Doughnuts?", doughnutsChooser);
	SmartDashboard.putNumber("Joystick Tolerance", 1);
	SmartDashboard.putString("Gate", Gate.Open.toString());
	SmartDashboard.putString("Gear", Gear.Low.toString());
	SmartDashboard.putNumber("Time", 0);
    }
    @Override
    public void robotPeriodic() {
	SmartDashboard.putNumber("Time", ((double) (System.currentTimeMillis() - START_TIME)) / 1000.);
	super.robotPeriodic();
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
     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box below the Gyro You can add additional auto modes by adding additional commands to the chooser code above (like the commented example) or additional comparisons to the switch structure below with additional strings & commands.
     */
    public void autonomousInit() {
	Position position = (Position) positionChooser.getSelected();
	MiddlePosition middlePosition = (MiddlePosition) middlePositionChooser.getSelected();
	Doughnuts doughnuts = (Doughnuts) doughnutsChooser.getSelected();
	Alliance alliance = (Alliance) allianceChooser.getSelected();
	autonomousCommand = new Autonomous(position, middlePosition, doughnuts, alliance);
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
     * This function is called when teleop starts
     * 
     * (currently this disables the auto when teleop starts. if you want it to continue remove that line)
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
