package edu.jaguarbots.steamworks.net;

import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * The class that pulls data from the accelerometer over the network
 * 
 * @author Zach Deibert
 * @since 2017
 * @version 2017
 */
public class Accelerometer extends Thread
{
    /**
     * The port to connect to
     * 
     * @since 2017
     */
    private static final int           PORT     = 14740;
    /**
     * The instance of the accelerometer singleton
     * 
     * @since 2017
     */
    private static final Accelerometer INSTANCE = new Accelerometer();
    /**
     * The semaphore to access the position data
     * 
     * @since 2017
     */
    private final Semaphore            lock;
    /**
     * The x coordinate in feet
     * 
     * @since 2017
     */
    private double                     x;
    /**
     * The y coordinate in feet
     * 
     * @since 2017
     */
    private double                     y;
    /**
     * The rotation of the robot in radians
     * 
     * @since 2017
     */
    private double                     rotation;

    /**
     * Gets the x coordinate of the robot
     * 
     * @return The x coordinate in feet
     * @since 2017
     */
    public static double getX()
    {
        try
        {
            INSTANCE.lock.acquire();
            return INSTANCE.x;
        }
        catch (InterruptedException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            INSTANCE.lock.release();
        }
    }

    /**
     * Gets the y coordinate of the robot
     * 
     * @return The y coordinate in feet
     * @since 2017
     */
    public static double getY()
    {
        try
        {
            INSTANCE.lock.acquire();
            return INSTANCE.y;
        }
        catch (InterruptedException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            INSTANCE.lock.release();
        }
    }

    /**
     * Gets the rotation of the robot
     * 
     * @return The rotation in radians
     * @since 2017
     */
    public static double getRotation()
    {
        try
        {
            INSTANCE.lock.acquire();
            return INSTANCE.rotation;
        }
        catch (InterruptedException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            INSTANCE.lock.release();
        }
    }

    @Override
    public void run()
    {
        try (Socket sock = new Socket("0.0.0.0", PORT))
        {
            try (InputStream stream = sock.getInputStream())
            {
                try (Scanner scan = new Scanner(stream))
                {
                    scan.useDelimiter(",");
                    while (scan.hasNext())
                    {
                        double x = scan.nextDouble();
                        double y = scan.nextDouble();
                        double rotation = scan.nextDouble();
                        lock.acquire();
                        this.x = x;
                        this.y = y;
                        this.rotation = rotation;
                        lock.release();
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Default constructor
     * 
     * @since 2017
     */
    private Accelerometer()
    {
        lock = new Semaphore(1);
        start();
    }
}
