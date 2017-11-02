package edu.jaguarbots.steamworks;

/**
 * The type of motor to create in the factory
 * 
 * @author Zach Deibert
 * @since 2017
 * @version 2017
 */
public enum MotorID {
    /**
     * Texas Instruments / Vex Robotics Jaguar Speed Controller as a PWM device.
     * 
     * @since 2017
     */
    CANTJaguar,
    /**
     * Mindsensors SD540 Speed Controller
     * 
     * @since 2017
     */
    SD540,
    /**
     * REV Robotics SPARK Speed Controller
     * 
     * @since 2017
     */
    Spark,
    /**
     * Cross the Road Electronics (CTRE) Talon and Talon SR Speed Controller
     * 
     * @since 2017
     */
    Talon,
    /**
     * VEX Robotics Victor 888 Speed Controller. The Vex Robotics Victor 884 Speed Controller can also be used with this class but may need to be calibrated per the Victor 884 user manual.
     * 
     * @since 2017
     */
    Victor,
    /**
     * VEX Robotics Victor SP Speed Controller
     * 
     * @since 2017
     */
    VictorSP
}
