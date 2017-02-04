#include <linux/kernel.h>
#include <linux/module.h>
#include "io.h"

int readAccelerometer(accel_axis_t axis) {
    switch (axis) {
        case ACCEL_AXIS_X:
            return 600;
        case ACCEL_AXIS_Y:
            return 120;
        case ACCEL_AXIS_Z:
            return 300;
        default:
            printk(KERN_ALERT "Invalid axis constant: %d.\n", axis);
            return 0;
    }
}

void initAccelerometer(void) {
}

void deinitAccelerometer(void) {
}
