package edu.jaguarbots.steamworks.subsystems;

import edu.jaguarbots.steamworks.commands.CommandBase;
import edu.jaguarbots.steamworks.commands.drive.DriveTank;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends SubsystemBase
{
    /**
     * left drive motor
     */
    private static SpeedController leftDrive     = motor(LEFT_DRIVE_MOTOR_PORT, LEFT_DRIVE_MOTOR_TYPE);
    /**
     * right drive motor
     */
    private static SpeedController rightDrive    = motor(RIGHT_DRIVE_MOTOR_PORT, RIGHT_DRIVE_MOTOR_TYPE);
    /**
     * Class that controls both drive motors
     */
    private static RobotDrive      robotDrive    = new RobotDrive(leftDrive, rightDrive);
    /**
     * Encoder on left side of drive
     */
    private Encoder                leftEncoder   = new Encoder(LEFT_ENCODER_CHANNEL_A, LEFT_ENCODER_CHANNEL_B);
    /**
     * Encoder on right side of drive
     */
    private Encoder                rightEncoder  = new Encoder(RIGHT_ENCODER_CHANNEL_A, RIGHT_ENCODER_CHANNEL_B);
    /**
     * distance left encoder has traveled.
     */
    private double                 leftEncoderValue;
    /**
     * distance right encoder has traveled.
     */
    private double                 rightEncoderValue;
    /**
     * array of encoder values with left occupying 0, and right occupying 1.
     */
    private double[]               encoderValues = { leftEncoderValue, rightEncoderValue };
    /**
     * bias to multiply side for drive adjusted.
     */
    // private double bias = 1;
    //
    // /**
    // * Whether or not we are driving adjusted.
    // */
    // private boolean inAdjustedDrive = false;
    /**
     * Diameter of pulleys, used for encoder calculations. (in inches)
     */
    // TODO change to diameter of pulleys
    private double                 diameter      = 6;
    /**
     * pulses per rotation for the encoders.
     */
    private int ppr = 400;
    /**
     * Gyroscope that measures angle of robot.
     */
    //private AnalogGyro             gyro          = new AnalogGyro(GYRO_PORT);
    /**
     * Solenoid to shift gears.
     */
    private static Solenoid        gearSol       = new Solenoid(SOLENOID_GEAR_SHIFT_PORT);
    /**
     * Current left motor speed.
     */
    private double                 leftMotorSpeed;
    /**
     * Current right motor speed.
     */
    private double                 rightMotorSpeed;
    
    private double				   leftEncoderTemp;
    private double				   rightEncoderTemp;

    /**
     * Calculates motor powers for adjusted driving
     * <html>you can view the math below<img src="https://puu.sh/tO9Si/990853f967.png"></img></html>
     * @return returns an array of powers with left in slot 0 & right in slot 1
     */
    public double[] getMotorPowers()
    {
//        double[] powers = new double[2];
//        double lastLeftMotorSpeed = leftMotorSpeed;
//        double lastRightMotorSpeed = rightMotorSpeed;
//        double lastLeftEncoder = leftEncoderValue;
//        double lastRightEncoder = rightEncoderValue;
//        getEncoders();
//        leftMotorSpeed = rightMotorSpeed = powers[0] = powers[1] = 1;
//        double estimatedLeft = (leftEncoderValue - lastLeftEncoder) / lastLeftMotorSpeed;
//        double estimatedRight = (rightEncoderValue - lastRightEncoder) / lastRightMotorSpeed;
//        if (leftEncoderValue > rightEncoderValue)
//        {
//            powers[0] = leftMotorSpeed = rightEncoderValue / leftEncoderValue + estimatedRight / estimatedLeft - 1;
//        }
//        else if (leftEncoderValue < rightEncoderValue)
//        {
//            powers[1] = rightMotorSpeed = leftEncoderValue / rightEncoderValue + estimatedLeft / estimatedRight - 1;
//        }

        double rightEncoderDelta = rightEncoderValue - rightEncoderTemp;
        double leftEncoderDelta = leftEncoderValue - leftEncoderTemp;
    	double[] powers = new double[2];
        double sqrt = 1;
        if(rightEncoderDelta < leftEncoderDelta)
        {
            sqrt = (4 * ROBOT_WIDTH * ROBOT_WIDTH) - (rightEncoderDelta * rightEncoderDelta) + (2 * rightEncoderDelta * leftEncoderDelta) - (leftEncoderDelta * leftEncoderDelta);

            powers[0] = 1;
            powers[1] = Math.abs(Math.cos(4 * Math.atan(Math.sqrt(sqrt))));

//            powers[0] = Math.abs(Math.cos(4 * Math.atan(Math.sqrt(sqrt))));
//            powers[1] = 1;
        }
        else
        {
            sqrt = (4 * ROBOT_WIDTH * ROBOT_WIDTH) - (leftEncoderDelta * leftEncoderDelta) + (2 * leftEncoderDelta * rightEncoderDelta) - (rightEncoderDelta * rightEncoderDelta);

            powers[0] = Math.abs(Math.cos(4 * Math.atan(Math.sqrt(sqrt))));
            powers[1] = 1;
            
//            powers[0] = 1;
//            powers[1] = Math.abs(Math.cos(4 * Math.atan(Math.sqrt(sqrt))));
        }
    	
        //System.out.println("(" + leftEncoderValue + ", " + rightEncoderValue + ")  powers[0] = " + powers[0] + " powers[1] = " + powers[1]);
        // 
//    	double[] powers = new double[2];
//        if(rightEncoderValue < leftEncoderValue)
//        {
//        	powers[0] = 1;
//        	powers[1] = 1;
//        }
//        else
//        {
//        	powers[0] = 1;
//        	powers[1] = 1;
//        }
        rightEncoderTemp = rightEncoderValue;
        leftEncoderTemp = leftEncoderValue;
        return powers;
    }

    /**
     * resets the encoders.
     * 
     * @param left
     *            make true to reset left encoder
     * @param right
     *            make true to reset right encoder
     */
    public void resetEncoders(boolean left, boolean right)
    {
        if (left) leftEncoder.reset();
        if (right) rightEncoder.reset();
    }

    /**
     * Sets encoder distance
     */
    public void startEncoders()
    {
        leftEncoder.setDistancePerPulse(Math.PI * diameter / ppr);
        rightEncoder.setDistancePerPulse(Math.PI * diameter / ppr);
    }

    /**
     * gets encoder values
     * 
     * @return array of encoder values with left occupying slot 0, and right occupying slot 1
     */
    public double[] getEncoders()
    {
        leftEncoderValue = leftEncoder.getDistance();
        rightEncoderValue = rightEncoder.getDistance();
        encoderValues = new double[] { leftEncoderValue, rightEncoderValue };
        return encoderValues;
    }

    /**
     * Drives the robot based on left and right speeds. Calls adjusted driving when 1 or -1
     * 
     * @param left
     *            speed
     * @param right
     *            speed
     */
    public void driveTank(double left, double right)
    {
        // if (Math.abs(left) == 1 && Math.abs(right) == 1 && left == right)
        // {
        // if (!inAdjustedDrive)
        // {
        // inAdjustedDrive = true;
        // 0 resetEncoders(true, true);
        // // reset encoders
        // }
        // //driveAdjusted(left, right);
        // double[] powers = new double[2];
        // powers = getMotorPowers();
        // driveTank(left*powers[0], right*powers[1]);
        // }
        // else
        // {
        // inAdjustedDrive = false;
        // }
        // System.out.println("left " + getEncoderLeft());
        // System.out.println("right " + getEncoderRight());
        robotDrive.tankDrive(left, right);
//        SmartDashboard.putNumber("EncoderLeft", CommandBase.driveSubsystem.getEncoderLeft());
//        SmartDashboard.putNumber("EncoderRight", CommandBase.driveSubsystem.getEncoderRight());
    }

    // /**
    // * Old, outdated adjusted drive algorithm
    // * @param left speed
    // * @param right speed
    // */
    // public void driveAdjusted(double left, double right)
    // {
    // double leftEnc = leftEncoder.getRaw();
    // double rightEnc = rightEncoder.getRaw();
    // double delta = leftEnc - rightEnc;
    // if (delta > 0) // if left is faster than right
    // {
    // bias = Math.abs((rightEnc / (leftEnc + delta))); // adjust bias
    // left = left * bias; // reduce left power
    // }
    // else if (delta < 0) // if right is faster than left
    // {
    // bias = Math.abs((leftEnc / (rightEnc - delta))); // adjust bias
    // right = right * bias; // if left is faster than right
    // }
    // robotDrive.tankDrive(-left, -right);
    // }
    /**
     * Turns robot counter-clockwise at a specific speed.
     * 
     * @param speed
     *            power to turn the robot
     */
    public void robotTurn(double speed)
    {
        robotDrive.tankDrive(-speed, speed);
    }

    /**
     * Stops both drive motors
     */
    public void robotStop()
    {
        robotDrive.tankDrive(0, 0);
    }

    /**
     * Turns robot to an angle based on gyroscope.
     * 
     * @param angle
     *            counter-clockwise angle to turn to. Pass in negative to turn clockwise.
     * @param speed
     *            at which to turn.
     *//*
    public void gyroTurnToAngle(double angle, double speed)
    {
        if (angle < 0)
        {
            if (gyro.getAngle() > angle)
            {
                robotTurn(-speed);
            }
        }
        else if (angle > 0)
        {
            if (gyro.getAngle() < angle)
            {
                robotTurn(speed);
            }
        }
        robotStop();
    }*/

    /**
     * @return current gyroscope angle.
     *//*
    public double getGyro()
    {
        return gyro.getAngle();
    }*/

    /**
     * @return left encoder distance
     */
    public double getEncoderLeft()
    {
        return leftEncoder.getDistance();
    }

    /**
     * @return right encoder distance
     */
    public double getEncoderRight()
    {
        return rightEncoder.getDistance();
    }

    /**
     * @return whether extended. If true, extended.
     */
    public static boolean getGearShift()
    {
    	//return true;
        return gearSol.get();
    }

    /**
     * Extends solenoid to shift gears on wheels.
     */
    public static void gearShiftHigh()
    {
        gearSol.set(false);
    }

    /**
     * Retracts solenoid to shift back gear on wheels.
     */
    public static void gearShiftLow()
    {
        gearSol.set(true);
    }

    /**
     * Sets the default command of the subsystem.
     */
    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveTank());
    }

    /**
     * Toggle between high and low gear
     */
}
