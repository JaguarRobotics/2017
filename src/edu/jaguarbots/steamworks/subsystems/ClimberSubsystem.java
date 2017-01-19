package edu.jaguarbots.steamworks.subsystems;

import edu.jaguarbots.steamworks.IOFactory;
import edu.jaguarbots.steamworks.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the mechanism to scale the castle in end game
 * 
 * @author Nathan Gawith
 * @since 2016
 * @version 2016
 */
public class ClimberSubsystem extends Subsystem
{
    /**
     * Motor for ascending and descending
     */
    private SpeedController climberMotor = IOFactory.motor(RobotMap.climberMotor, RobotMap.climberMotorType);
    /**
     * Solenoid for raising and lowering the arm for climbing
     */
    private Solenoid        climberSol   = new Solenoid(RobotMap.solClimber);
    /**
     * Limit switch to ensure robot does not climb too high
     */
    private DigitalInput    climberLimit = new DigitalInput(RobotMap.climberLimitSwitch);
    // private double POV;

    // private boolean isShooterUp;
    public ClimberSubsystem()
    {
        solDown();
    }

    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Gets the value of the limit switch for the climbing mechanism
     * 
     * @return limit switch value
     */
    public boolean getLimit()
    {
        return climberLimit.get();
    }

    /**
     * Makes climber motor turn forward
     */
    public void motorForward()
    {
        // climberMotor.set(Value.kOn);
        climberMotor.set(1.0);
        // TODO find out which direction is forward and which is backward
    }

    /**
     * Makes motor stop running and stop accepting input
     */
    public void stopMotor()
    {
        climberMotor.stopMotor();
    }

    /**
     * Makes climber motor turn backward
     */
    public void motorBackward()
    {
        // climberMotor.set(Value.kOn);
        climberMotor.set(-1.0);
        // TODO find out which direction is forward and which is backward
    }

    /**
     * Extends climbing solenoid for reaching
     */
    public void solUp()
    {
        climberSol.set(true);
    }

    /**
     * Retracts climbing solenoid
     */
    public void solDown() // tentative, mechanical structure may not allow this
                          // to occur
    {
        climberSol.set(false);
    }
    //
    // public void ascendDescend(double c){
    // POV = c;
    // if(POV <= 45 && POV >= -1){
    // isShooterUp = true;
    // }else if(POV >= 45 && POV <= 135){
    // //UNUSED
    // }else if(POV >= 135 && POV <= 225){
    // isShooterUp = false;
    // }else if(POV >= 225 && POV <= 315){
    // //UNUSED
    // }
    // }
    // public Boolean getShooterPosition = isShooterUp;
}
//