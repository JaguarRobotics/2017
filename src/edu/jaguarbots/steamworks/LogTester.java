package edu.jaguarbots.steamworks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class LogTester
{
    public static void main(String[] args)
    {
        Logger log = LogManager.getLogger();
        log.info("Hello, world!");
        log.trace("This is a trace message");
        log.fatal("Goodbye, world!");
        for (int i = -10; i <= 10; ++i)
        {
            log.trace("test=\"{}\"", ((double) i) * (double) i);
            log.trace("i={}", i);
        }
    }
}
