#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/spi/spi.h>
#include "calculations.h"
#include "io.h"

static void init_spi_accelerometer(void);
static int accelerometer_probe(struct spi_device *spi);
static int accelerometer_remove(struct spi_device *spi);

static struct spi_device *spi_dev;

static struct spi_driver driver = {
    .driver = {
        .name = "bcm2708_spi",
        .owner = THIS_MODULE
    },
    .probe = accelerometer_probe,
    .remove = accelerometer_remove
};

int readAccelerometer(accel_axis_t axis) {
    char buffer[2];
    char command[] = {
        SPI_READ, 0
    };
    
    if (spi_dev == NULL) {
        printk(KERN_ALERT "No accelerometer found.\n");
        return 0;
    }
    switch (axis) {
        case ACCEL_AXIS_X:
            command[1] = REGISTER_X;
            break;
        case ACCEL_AXIS_Y:
            command[1] = REGISTER_Y;
            break;
        case ACCEL_AXIS_Z:
            command[1] = REGISTER_Z;
            break;
        default:
            printk(KERN_ALERT "Invalid accelerometer axis: %d.\n", axis);
            return 0;
    }
    spi_write(spi_dev, command, 2);
    spi_read(spi_dev, buffer, 2);
    return ((((int) buffer[1]) << 8) + (int) buffer[0]);
}

void initAccelerometer(void) {
    int ret;

    if ((ret = spi_register_driver(&driver)) < 0) {
        printk(KERN_ALERT "Error initializing driver: %d.\n", ret);
    }
}

void deinitAccelerometer(void) {
    spi_unregister_driver(&driver);
}

void init_spi_accelerometer(void) {
    char resetData[] = {
        SPI_WRITE, REGISTER_RESET, REGISTER_RESET_VALUE
    };
    char initData[] = {
        SPI_WRITE, REGISTER_FIFO_CONTROL, REGISTER_FIFO_CONTROL_VALUE
    };
    char startData[] = {
        SPI_WRITE, REGISTER_POWER_CONTROL, REGISTER_POWER_CONTROL_VALUE
    };

    spi_write(spi_dev, resetData, 3);
    spi_write(spi_dev, initData, 3);
    spi_write(spi_dev, startData, 3);
}

int accelerometer_probe(struct spi_device *spi) {
    spi_dev = spi;
    return 0;
}

int accelerometer_remove(struct spi_device *spi) {
    spi_dev = NULL;
    return 0;
}
