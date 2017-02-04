#include <linux/kernel.h>
#include <linux/module.h>
#include "io.h"

int readAccelerometer(accel_axis_t axis) {
    printk(KERN_ALERT "Not implemented: readAccelerometer");
    return 0;
}

void initAccelerometer(void) {

}

void deinitAccelerometer(void) {
}
