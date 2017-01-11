package edu.jaguarbots.steamworks.commands.drive;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.subsystems.DriveSubsystem;
/**
 * Shifts Drive Gears
 * @author Cody
 *
 */
public class GearShift extends CommandBase
{
    public GearShift()
    {
        requires(CommandBase.driveSubsystem);
    }

    @Override
    protected void initialize()
    {
        boolean isOut = DriveSubsystem.getGearShift();
        if(isOut){
            DriveSubsystem.gearShiftIn();
        }
        if(isOut == false){
            DriveSubsystem.gearShiftOut();
        }
        
    }

    @Override
    protected void execute()
    {
        // TODO Auto-generated method stub
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub
    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub
    }
}
