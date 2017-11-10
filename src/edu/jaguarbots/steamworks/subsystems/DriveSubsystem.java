package edu.jaguarbots.steamworks.subsystems;

import edu.jaguarbots.steamworks.OversampledEncoder;
import edu.jaguarbots.steamworks.commands.drive.DriveTank;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * @author Brian, Nathan G, Zach D. Drive subsystem holds all of methods used in the commands for drive
 * @since 2017
 */
public class DriveSubsystem extends SubsystemBase {
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
    private Encoder                leftEncoder   = new OversampledEncoder(LEFT_ENCODER_CHANNEL_A,
                    LEFT_ENCODER_CHANNEL_B);
    /**
     * Encoder on right side of drive
     */
    private Encoder                rightEncoder  = new OversampledEncoder(RIGHT_ENCODER_CHANNEL_A,
                    RIGHT_ENCODER_CHANNEL_B);
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
     * Diameter of pulleys, used for encoder calculations. (in inches)
     */
    public double                  diameter      = 6;
    /**
     * pulses per rotation for the encoders.
     */
    private int                    ppr           = (int) (400 * 3);
    /**
     * Solenoid to shift gears.
     */
    private static Solenoid        gearSol       = new Solenoid(SOLENOID_GEAR_SHIFT_PORT);
    /**
     * Solenoid to open gear door.
     */
    private static Solenoid        gearDoor      = new Solenoid(SOLENOID_GEAR_DOOR_PORT);
    /**
     * Counter used to count in get motor powers
     */
    int                            counter       = 0;

    /**
     * @param encoderTicks
     *            encoder ticks to move
     * @return encoder ticks converted to inches
     */
    public double getDistanceInInches(double encoderTicks) {
        double result = (diameter * Math.PI) * (encoderTicks / ppr);
        return result;
    }

    /**
     * @param inches
     *            number of inches to convert
     * @return number of encoder ticks for the specified number of inches.
     */
    public double getEncoderTicksFromInches(double inches) {
        double result = inches * (ppr / (Math.PI * diameter));
        return result;
    }

    public void initEncoders() {
        leftEncoder.free();
        rightEncoder.free();
        leftEncoder = new Encoder(LEFT_ENCODER_CHANNEL_A, LEFT_ENCODER_CHANNEL_B);
        rightEncoder = new Encoder(RIGHT_ENCODER_CHANNEL_A, RIGHT_ENCODER_CHANNEL_B);
        counter = 0;
    }

    /**
     * @param radians
     *            ` The amount of radians the robot is going to go
     * @returns the amount of encoder ticks from from radians.
     */
    public double getEncoderTicksFromRadians(double radians) {
        double result = getEncoderTicksFromInches(radians * ROBOT_WIDTH / 2);
        return result;
    }

    /**
     * Calculates the amount of radians that is equivilant to a passed in number of degrees
     * 
     * @param degrees
     *            The degrees that the robot should turn
     * @return The amount of radians per the amount of degrees passed in
     */
    public double getRadiansFromDegrees(double degrees) {
        return (degrees / 360) * (Math.PI * 2);
    }

    /**
     * Calculates motor powers for adjusted driving <html>you can view the math
     * below<img src="https://puu.sh/tO9Si/990853f967.png"></img></html>
     * 
     * @return returns an array of powers with left in slot 0 & right in slot 1
     */
    public double[] getMotorPowers() {
        double left = Math.abs(getEncoderLeft());
        double right = Math.abs(getEncoderRight());
        double diff = Math.abs(right - left + 1);
        double percentage = (diff * 3) / ((right >= left) ? right + 1 : left) * 2;
        percentage = Math.min(percentage, 1);
        double powers[] = new double[2];
        if (right > left) {
            powers[0] = 1;
            powers[1] = 1 - percentage;
        } else {
            powers[0] = 1 - percentage;
            powers[1] = 1;
        }
        if (counter % 5 == 0) {
        }
        counter++;
        return (counter > 20) ? powers : new double[] { 1, 1 };
    }

    /**
     * resets the encoders.
     * 
     * @param left
     *            make true to reset left encoder
     * @param right
     *            make true to reset right encoder
     */
    public void resetEncoders(boolean left, boolean right) {
        if (left) {
            leftEncoder.reset();
        }
        if (right) {
            rightEncoder.reset();
        }
    }

    /**
     * Sets encoder distance
     */
    public void startEncoders() {
        leftEncoder.setDistancePerPulse(Math.PI * diameter / ppr);
        rightEncoder.setDistancePerPulse(Math.PI * diameter / ppr);
    }

    /**
     * gets encoder values
     * 
     * @return array of encoder values with left occupying slot 0, and right occupying slot 1
     */
    public double[] getEncoders() {
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
    public void driveTank(double left, double right) {
        robotDrive.tankDrive(left, right);
    }

    /**
     * Turns robot counter-clockwise at a specific speed.
     * 
     * @param speed
     *            power to turn the robot
     */
    public void robotTurn(double speed) {
        robotDrive.tankDrive(-speed, speed);
    }

    /**
     * Stops both drive motors
     */
    public void robotStop() {
        robotDrive.tankDrive(0, 0);
    }

    /**
     * @return left encoder distance
     */
    public double getEncoderLeft() {
        return leftEncoder.getDistance();
    }

    /**
     * @return right encoder distance
     */
    public double getEncoderRight() {
        return rightEncoder.getDistance();
    }

    /**
     * @return whether extended. If true, extended.
     */
    public static boolean getGearShift() {
        return gearSol.get();
    }

    /**
     * Extends solenoid to shift gears on wheels.
     */
    public static void gearShiftHigh() {
        gearSol.set(true);
        System.out.println("I RAN HIGH!");
    }

    /**
     * Retracts solenoid to shift back gear on wheels.
     */
    public static void gearShiftLow() {
        gearSol.set(false);
        System.out.println("I RAN LOW!");
    }

    /**
     * Extends solenoid to shift gears on wheels.
     */
    public static void gearDoorClose() {
        gearDoor.set(false);
    }

    /**
     * Retracts solenoid to shift back gear on wheels.
     */
    public static void gearDoorOpen() {
        gearDoor.set(true);
    }

    /**
     * Sets the default command of the subsystem.
     */
    public void initDefaultCommand() {
        setDefaultCommand(new DriveTank());
    }
}
