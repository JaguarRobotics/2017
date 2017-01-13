
package edu.jaguarbots.steamworks;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.jaguarbots.steamworks.commands.Autonomous;
import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
    private Command                            autonomousCommand;
    // vars for auto
    private final SendableChooser                    positionChooser  = new SendableChooser();
    private final SendableChooser                    gearsChooser  = new SendableChooser();
    private final SendableChooser                    crossChooser   = new SendableChooser();
    /*-
    private final SendableChooser                    goalChooser      = new SendableChooser();
    private final SendableChooser                    defenseChooser   = new SendableChooser();
    // */
    
//    Compressor compresser = new Compressor(RobotMap.pwmCompresser);

    public enum Position1
    {
    	Left, Middle, Right
    }
    
    public enum Gears1
    {
    	One, Two, Three
    }
    
    public enum CrossLine1
    {
    	Yes, No
    }
    
    
    /*New enums are above*/
    /*Old enums are below*/
    /*-
    public enum Defense
    {
        Portcullis, Cheval, Moat, Ramparts, Rockwall, Terrain, Low
    }

    public enum Position
    {
        One, Two, Three, Four, Five, Spy
    }

    public enum Goal
    {
        Left, Middle, Right
    }
    // */

    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        try{
            CommandBase.init();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        positionChooser.addDefault("Left", Position1.Left);
        positionChooser.addObject("Middle", Position1.Middle);
        positionChooser.addObject("Right", Position1.Right);
        SmartDashboard.putData("Position", positionChooser);
        gearsChooser.addDefault("One", Gears1.One);
        gearsChooser.addObject("Two", Gears1.Two);
        gearsChooser.addObject("Three", Gears1.Three);
        gearsChooser.addObject("null", null);
        SmartDashboard.putData("Gears", gearsChooser);
        crossChooser.addObject("Yes", CrossLine1.Yes);
        crossChooser.addObject("No", CrossLine1.No);
        crossChooser.addDefault("Yes", CrossLine1.No);
        crossChooser.addObject("null", null);
        SmartDashboard.putData("Cross Baseline", crossChooser);

//        positionChooser.addDefault("One", Position.One);
//        positionChooser.addObject("Two", Position.Two);
//        positionChooser.addObject("Three", Position.Three);
//        positionChooser.addObject("Four", Position.Four);
//        positionChooser.addObject("Five", Position.Five);
//        positionChooser.addObject("Spy", Position.Spy);
//        positionChooser.addObject("null", null);
//        SmartDashboard.putData("Position", positionChooser);
//        goalChooser.addDefault("Left", Goal.Left);
//        goalChooser.addObject("Middle", Goal.Middle);
//        goalChooser.addObject("Right", Goal.Right);
//        goalChooser.addObject("null", null);
//        SmartDashboard.putData("Goal", goalChooser);
//        defenseChooser.addObject("Portcullis", Defense.Portcullis);
//        defenseChooser.addObject("Cheval De Frise", Defense.Cheval);
//        defenseChooser.addObject("Moat", Defense.Moat);
//        defenseChooser.addObject("Ramparts", Defense.Ramparts);
//        defenseChooser.addObject("Rockwall", Defense.Rockwall);
//        defenseChooser.addObject("Rough Terrain", Defense.Terrain);
//        defenseChooser.addDefault("Low Bar", Defense.Low);
//        defenseChooser.addObject("null", null);
//        SmartDashboard.putData("Defense", defenseChooser);
        
//        compresser.setClosedLoopControl(true);                              //should turn on the compresser
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit()
    {
//        compresser.setClosedLoopControl(false);         //should turn off the compresser
    }

    public void disabledPeriodic()
    {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro You
     * can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    public void autonomousInit()
    {
        final Position1 position = (Position1) positionChooser.getSelected();
        final Gears1 gears = (Gears1) gearsChooser.getSelected();
        final CrossLine1 cross = (CrossLine1) crossChooser.getSelected();
//        final Position position = (Position) positionChooser.getSelected();
//        final Goal goal = (Goal) goalChooser.getSelected();
//        final Defense defense = (Defense) defenseChooser.getSelected();
        if(position == null && gears == null && cross == null)
        {
            autonomousCommand = new Autonomous();
        }
        else
        {
            autonomousCommand = new Autonomous(position, gears, cross);
        }
        if (autonomousCommand != null) autonomousCommand.start();
//        if (position == Position.Spy)
//        {
//            autonomousCommand = new Autonomous(true);
//        }
//        else if(defense == null && goal == null && position == null)
//        {
//            autonomousCommand = new Autonomous();
//        }
//        else
//        {
//            autonomousCommand = new Autonomous(defense, position, goal);
//        }
//        if (autonomousCommand != null) autonomousCommand.start();
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
    	//new GearShiftLow();
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
