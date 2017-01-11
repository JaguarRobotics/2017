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
     * Default, drives forward 5
     */
    public Autonomous()
    {
        moveToRamp();
    }

    /**
     * If spybot position.
     * 
     * @param spyShoot
     *            true if we want to shoot, false to drive up to defense.
     */
    public Autonomous(final boolean spyShoot)
    {
        if (spyShoot)
        {
            addSequential(new DriveTurn(30));
            //addSequential(new ShooterUp());
            //addSequential(new ShooterFire());
        }
        else
        {
            addSequential(new EncoderDrive(-5));
        }
    }

    /**
     * Selects autonomous route based on defense to cross, position in, and what
     * goal to shoot in.
     * 
     * @param defense
     *            enum: Portcullis, Cheval, Moat, Ramparts, Rockwall, Terrain,
     *            or Low
     * @param position
     *            enum: One, Two, Three, Four, or Five
     * @param goal
     *            enum: Left, Middle, or Right
     */
    public Autonomous(final Robot.Defense defense,
                    final Robot.Position position, final Robot.Goal goal)
    {
        moveToRamp();
        crossDefense(defense);
        shoot(position, goal);
    }

    private void moveToRamp()
    {
        addSequential(new EncoderDrive(5));
    }

    private void crossDefense(final Robot.Defense defense)
    {
        if (defense == null) System.out.println("defense null");
        else
        {
            switch (defense)
            {
                case Low:
                    // Low Bar
                    addSequential(new EncoderDrive(4));
                    break;
                case Portcullis:
                    //addSequential(new IntakeBottom());
                    addSequential(new EncoderDrive(1, .5));
                    //addParallel(new IntakeTop());
                    addSequential(new EncoderDrive(3, .4));
                    //addSequential(new IntakeBottom());
                    break;
                case Cheval:
                    //addSequential(new IntakeTop());
                    addSequential(new EncoderDrive(4));
                    break;
                case Moat:
                    addSequential(new EncoderDrive(4, .5));
                    addSequential(new DriveFix(0));
                    break;
                case Ramparts:
                    addSequential(new EncoderDrive(4, .5));
                    addSequential(new DriveFix(0));
                    break;
                case Rockwall:
                    addSequential(new EncoderDrive(4, .5));
                    addSequential(new DriveFix(0));
                    break;
                case Terrain:
                    addSequential(new EncoderDrive(4, .5));
                    addSequential(new DriveFix(0));
                    break;
            }
        }
    }

    private void shoot(final Robot.Position position, final Robot.Goal goal)
    {
        switch (position)
        {
            case Spy:
                System.out.println("got here illegally");
                break;
            case One:
                switch (goal)
                {
                    case Left:
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(-45));
                        break;
                    case Middle:
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(3));
                        break;
                    case Right:
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(6));
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(30));
                        break;
                }
                break;
            case Two:
                switch (goal)
                {
                    case Left:
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(-30));
                        break;
                    case Middle:
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(2.5));
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(3));
                        break;
                    case Right:
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(5.5));
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(30));
                        break;
                }
                break;
            case Three:
                switch (goal)
                {
                    case Left:
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(-30));
                        break;
                    case Middle:
                        addSequential(new EncoderDrive(3));
                        break;
                    case Right:
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(30));
                        break;
                }
                break;
            case Four:
                switch (goal)
                {
                    case Left:
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(4));
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(-30));
                        break;
                    case Middle:
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(2));
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(3));
                        break;
                    case Right:
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(30));
                        break;
                }
                break;
            case Five:
                switch (goal)
                {
                    case Left:
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(6));
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(-30));
                        break;
                    case Middle:
                        addSequential(new DriveTurn(90));
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(-90));
                        addSequential(new EncoderDrive(3));
                        break;
                    case Right:
                        addSequential(new EncoderDrive(3));
                        addSequential(new DriveTurn(45));
                        break;
                }
                break;
        }
        //addSequential(new ShooterUp());
        //addSequential(new AimVertical());
        //addSequential(new AimHorizontal());
        //addSequential(new ShooterFire());
    }
}
