package edu.jaguarbots.steamworks.commands;

import edu.jaguarbots.steamworks.Robot;
import edu.jaguarbots.steamworks.commands.drive.DriveFix;
import edu.jaguarbots.steamworks.commands.drive.DriveTurn;
import edu.jaguarbots.steamworks.commands.drive.EncoderDrive;
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
