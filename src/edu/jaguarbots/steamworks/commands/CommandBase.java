package edu.jaguarbots.steamworks.commands;

import edu.jaguarbots.steamworks.OI;
import edu.jaguarbots.steamworks.RobotMap;
import edu.jaguarbots.steamworks.subsystems.ClimberSubsystem;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This is a class that establishes hierarchy and the ridiculous need to javadoc everything
 * 
 * @author Nathan Gawith
 */
public abstract class CommandBase extends Command implements RobotMap
{
    /**
     * This is the oi
     */
    public static OI                     oi;
    
    /**
     * This creates the drive subsystem
     */
    public static final DriveSubsystem   driveSubsystem  = new DriveSubsystem();
    
    /**
     * This creates the climber subsystem
     */
    public static final ClimberSubsystem climberSubsystem  = new ClimberSubsystem();

    public CommandBase()
    {
        super();
    }

    public static void init() throws InterruptedException
    {
        try {
        oi = new OI();
        } 
        catch(Exception e){
            e.printStackTrace();
        }
   }
}
