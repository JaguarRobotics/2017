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
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the IterativeRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends IterativeRobot
{
    private Command               autonomousCommand;
    // vars for auto
    private final SendableChooser positionChooser  = new SendableChooser();
    private final SendableChooser middlePosition   = new SendableChooser();
    private final SendableChooser doughnutsChooser = new SendableChooser();

    // Compressor compresser = new Compressor(RobotMap.pwmCompresser);
    public enum Position
    {
        Left, Middle, Right
    }

    public enum MiddlePosition
    {
        Left, Right
    }

    public enum Doughnuts
    {
        Yes, No
    }

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    public void robotInit()
    {
        try
        {
            CommandBase.init();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        positionChooser.addDefault("Left", Position.Left);
        positionChooser.addObject("Middle", Position.Middle);
        positionChooser.addObject("Right", Position.Right);
        SmartDashboard.putData("Position", positionChooser);
        middlePosition.addDefault("Left", MiddlePosition.Left);
        middlePosition.addObject("Right", MiddlePosition.Right);
        // middlePosition.addObject("null", null);
        SmartDashboard.putData("MiddlePosition", middlePosition);
        doughnutsChooser.addDefault("No", Doughnuts.No);
        doughnutsChooser.addObject("Yes", Doughnuts.Yes);
        // doughnutsChooser.addObject("null", null);
        SmartDashboard.putData("Doughnuts?", doughnutsChooser);
        SmartDashboard.putNumber("EncoderLeft",
                        CommandBase.driveSubsystem.getEncoderLeft());
        SmartDashboard.putNumber("EncoderRight",
                        CommandBase.driveSubsystem.getEncoderRight());
        SmartDashboard.putNumber("Joystick Tolerance", 1);
        // compresser.setClosedLoopControl(true); //should turn on the compresser
    }

    /**
     * This function is called once each time the robot enters Disabled mode. You can use it to reset any subsystem
     * information you want to clear when the robot is disabled.
     */
    public void disabledInit()
    {
        // compresser.setClosedLoopControl(false); //should turn off the compresser
    }

    public void disabledPeriodic()
    {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes using
     * the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
     * remove all of the chooser code and uncomment the getString code to get the auto name from the text box below the
     * Gyro You can add additional auto modes by adding additional commands to the chooser code above (like the
     * commented example) or additional comparisons to the switch structure below with additional strings & commands.
     */
    public void autonomousInit()
    {
        Position position = (Position) positionChooser.getSelected();
        MiddlePosition middlePosition = (MiddlePosition) positionChooser
                        .getSelected();
        Doughnuts doughnuts = (Doughnuts) positionChooser.getSelected();
        autonomousCommand = new Autonomous(position, middlePosition, doughnuts);
        autonomousCommand.start();
        // final Position position = (Position) positionChooser.getSelected();
        // final Gears gears = (Gears) gearsChooser.getSelected();
        // final CrossLine cross = (CrossLine) crossChooser.getSelected();
        // if(position == null && gears == null && cross == null)
        // {
        // autonomousCommand = new Autonomous();
        // }
        // else
        // {
        // autonomousCommand = new Autonomous(position, gears, cross);
        // }
        // if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit()
    {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        // new GearShiftLow();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {
        LiveWindow.run();
    }
}
