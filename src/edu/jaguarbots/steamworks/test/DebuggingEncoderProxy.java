package edu.jaguarbots.steamworks.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * An encoder that logs the information given to it
 * 
 * @author Zach Deibert
 * @since 2017
 * @version 2017
 */
public class DebuggingEncoderProxy extends Encoder
{
    private static final Logger LOG = LogManager.getLogger();
    /**
     * The name of the motor (for the log)
     * 
     * @since 2017
     */
    private final String        name;

    @Override
    public int getFPGAIndex()
    {
        int res = super.getFPGAIndex();
        LOG.trace("{}.getFPGAIndex=\"{}\"", name, res);
        return res;
    }

    @Override
    public int getEncodingScale()
    {
        int res = super.getEncodingScale();
        LOG.trace("{}.getEncodingScale=\"{}\"", name, res);
        return res;
    }

    @Override
    public void free()
    {
        super.free();
        LOG.trace("{}.free", name);
    }

    @Override
    public int getRaw()
    {
        int res = super.getRaw();
        LOG.trace("{}.getRaw=\"{}\"", name, res);
        return res;
    }

    @Override
    public int get()
    {
        int res = super.get();
        LOG.trace("{}.get=\"{}\"", name, res);
        return res;
    }

    @Override
    public void reset()
    {
        super.reset();
        LOG.trace("{}.reset", name);
    }

    @Override
    public void setMaxPeriod(double maxPeriod)
    {
        super.setMaxPeriod(maxPeriod);
        LOG.trace("{}.setMaxPeriod=\"{}\"", name, maxPeriod);
    }

    @Override
    public boolean getStopped()
    {
        boolean res = super.getStopped();
        LOG.trace("{}.getStopped=\"{}\"", name, res);
        return res;
    }

    @Override
    public boolean getDirection()
    {
        boolean res = super.getDirection();
        LOG.trace("{}.getDirection=\"{}\"", name, res);
        return res;
    }

    @Override
    public double getDistance()
    {
        double res = super.getDistance();
        LOG.trace("{}.getDistance=\"{}\"", name, res);
        return res;
    }

    @Override
    public double getRate()
    {
        double res = super.getRate();
        LOG.trace("{}.getRate=\"{}\"", name, res);
        return res;
    }

    @Override
    public void setMinRate(double minRate)
    {
        super.setMinRate(minRate);
        LOG.trace("{}.setMinRate=\"{}\"", name, minRate);
    }

    @Override
    public void setDistancePerPulse(double distancePerPulse)
    {
        super.setDistancePerPulse(distancePerPulse);
        LOG.trace("{}.setDistancePerPulse=\"{}\"", name, distancePerPulse);
    }

    @Override
    public void setReverseDirection(boolean reverseDirection)
    {
        super.setReverseDirection(reverseDirection);
        LOG.trace("{}.setReverseDirection=\"{}\"", name, reverseDirection);
    }

    @Override
    public void setSamplesToAverage(int samplesToAverage)
    {
        super.setSamplesToAverage(samplesToAverage);
        LOG.trace("{}.setSamplesToAverage=\"{}\"", name, samplesToAverage);
    }

    @Override
    public int getSamplesToAverage()
    {
        int res = super.getSamplesToAverage();
        LOG.trace("{}.getSamplesToAverage=\"{}\"", name, res);
        return res;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource)
    {
        super.setPIDSourceType(pidSource);
        LOG.trace("{}.setPIDSourceType=\"{}\"", name, pidSource);
    }

    @Override
    public PIDSourceType getPIDSourceType()
    {
        PIDSourceType res = super.getPIDSourceType();
        LOG.trace("{}.getPIDSourceType=\"{}\"", name, res);
        return res;
    }

    @Override
    public double pidGet()
    {
        double res = super.pidGet();
        LOG.trace("{}.pidGet=\"{}\"", name, res);
        return res;
    }

    @Override
    public void setIndexSource(int channel)
    {
        super.setIndexSource(channel);
        LOG.trace("{}.setIndexSource=\"{}\"", name, channel);
    }

    @Override
    public void setIndexSource(DigitalSource source)
    {
        super.setIndexSource(source);
        LOG.trace("{}.setIndexSource=\"{}\"", name, source);
    }

    @Override
    public void setIndexSource(int channel, IndexingType type)
    {
        super.setIndexSource(channel, type);
        LOG.trace("{}.setIndexSource=\"{}\":{}", name, channel, type);
    }

    @Override
    public void setIndexSource(DigitalSource source, IndexingType type)
    {
        super.setIndexSource(source, type);
        LOG.trace("{}.setIndexSource=\"{}\":{}", name, source, type);
    }

    @Override
    public String getSmartDashboardType()
    {
        String res = super.getSmartDashboardType();
        LOG.trace("{}.getSmartDashboardType=\"{}\"", name, res);
        return res;
    }

    @Override
    public void initTable(ITable subtable)
    {
        super.initTable(subtable);
        LOG.trace("{}.initTable=\"{}\"", name, subtable);
    }

    @Override
    public ITable getTable()
    {
        ITable res = super.getTable();
        LOG.trace("{}.getTable=\"{}\"", name, res);
        return res;
    }

    @Override
    public void updateTable()
    {
        super.updateTable();
        LOG.trace("{}.updateTable", name);
    }

    @Override
    public void startLiveWindowMode()
    {
        super.startLiveWindowMode();
        LOG.trace("{}.startLiveWindowMode", name);
    }

    @Override
    public void stopLiveWindowMode()
    {
        super.stopLiveWindowMode();
        LOG.trace("{}.stopLiveWindowMode");
    }

    /**
     * Creates a new {@link DebuggingEncoderProxy}
     * 
     * @param channelA
     *            The a channel digital input channel.
     * @param channelB
     *            The b channel digital input channel.
     * @param name
     *            The name of the motor (for the log)
     * @since 2017
     */
    public DebuggingEncoderProxy(final int channelA, final int channelB, final String name)
    {
        super(channelA, channelB);
        this.name = name;
    }
}
