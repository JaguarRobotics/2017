package edu.jaguarbots.steamworks.commands;

import edu.jaguarbots.steamworks.Robot;
import edu.jaguarbots.steamworks.commands.drive.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup
{
    /**
     * Default, does nothing as of yet
     */
    public Autonomous()
    {
        addSequential(new EncoderDrive(6 * 12));
        addSequential(new DriveTurn(360));
        addSequential(new EncoderDrive(-6 * 12));
        addSequential(new DriveTurn(360));
    }
    
    /**
     * Selects autonomous route based on defense to cross, position in, and what
     * goal to shoot in.
     * 
     * @param position
     *            enum: Left, Middle, or Right
     * @param gears
     *            enum: One, Two, or Three
     * @param cross
     *            enum: Yes or No
     */
    public Autonomous(final Robot.Position position,
                   final Robot.Gears gears, final Robot.CrossLine cross)
   {
       
   }
}
