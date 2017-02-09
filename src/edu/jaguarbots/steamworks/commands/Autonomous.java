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
    	double straightSpeed = 0.8;
    	double turnSpeed = 0.55;
        addSequential(new GearShiftLow());
        addSequential(new EncoderDrive(CommandBase.driveSubsystem.getEncoderTicksFromInches(36), straightSpeed));
        addSequential(new EncoderTurn(4 * Math.PI, turnSpeed));
        addSequential(new EncoderDrive(-200, straightSpeed));
        addSequential(new EncoderTurn(- 4 * Math.PI, turnSpeed));
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
		switch(position)
		{
			case Left:
				switch(gears)
				{
					case One:
						switch(cross)
						{
							case Yes:
								break;
							case No:
								break;
						}
						break;
					case Two:
						switch(cross)
						{
							case Yes:
								break;
							case No:
								break;
						}
						break;
					case Three:
						switch(cross)
						{
							case Yes:
								break;
							case No:
								break;
						}
						break;
				}
				break;
			case Middle:
				switch(gears)
				{
					case One:
						switch(cross)
						{
							case Yes:
								break;
							case No:
								break;
						}
						break;
					case Two:
						switch(cross)
						{
							case Yes:
								break;
							case No:
								break;
						}
						break;
					case Three:
						switch(cross)
						{
							case Yes:
								break;
							case No:
								break;
						}
						break;
				}
				break;
			case Right:
				switch(gears)
				{
					case One:
						switch(cross)
						{
							case Yes:
								break;
							case No:
								break;
						}
						break;
					case Two:
						switch(cross)
						{
							case Yes:
								break;
							case No:
								break;
						}
						break;
					case Three:
						switch(cross)
						{
							case Yes:
								break;
							case No:
								break;
						}
						break;
				}
				break;
		}
   	}
}
