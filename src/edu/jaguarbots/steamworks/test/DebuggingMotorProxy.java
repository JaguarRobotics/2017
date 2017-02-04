package edu.jaguarbots.steamworks.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * A motor that logs the information given to it, then sends that information to a different motor
 * 
 * @author Zach Deibert
 * @since 2017
 * @version 2017
 */
public class DebuggingMotorProxy implements SpeedController
{
    private static final Logger   LOG = LogManager.getLogger();
    /**
     * The real motor
     * 
     * @since 2017
     */
    private final SpeedController parent;
    /**
     * The name of the motor (for the log)
     * 
     * @since 2017
     */
    private final String          name;

    @Override
    public void pidWrite(double output)
    {
        parent.pidWrite(output);
        LOG.trace("{}.pidWrite({})", name, output);
    }

    @Override
    public double get()
    {
        double res = parent.get();
        LOG.trace("{}.get() = {}", name, res);
        return res;
    }

    @Override
    public void set(double speed)
    {
        parent.set(speed);
        LOG.trace("{}.set({})", name, speed);
    }

    @Override
    public void setInverted(boolean isInverted)
    {
        parent.setInverted(isInverted);
        LOG.trace("{}.setInverted({})", name, isInverted);
    }

    @Override
    public boolean getInverted()
    {
        boolean res = parent.getInverted();
        LOG.trace("{}.getInverted() = {}", name, res);
        return res;
    }

    @Override
    public void disable()
    {
        parent.disable();
        LOG.trace("{}.disable()", name);
    }

    @Override
    public void stopMotor()
    {
        parent.stopMotor();
        LOG.trace("{}.stopMotor()", name);
    }

    /**
     * Creates a new {@link DebuggingMotorProxy}
     * 
     * @param parent
     *            The real motor
     * @param name
     *            The name of the motor (for the log)
     * @since 2017
     */
    public DebuggingMotorProxy(final SpeedController parent, final String name)
    {
        this.parent = parent;
        this.name = name;
    }
}
