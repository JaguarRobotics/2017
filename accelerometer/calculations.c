#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/kthread.h>
#include <linux/sched.h>
#include <linux/time.h>
#include "bignum.h"
#include "calculations.h"
#include "io.h"
#include "taylor.h"

num_t timeSlice;
num_t xAcceleration;
num_t yAcceleration;
num_t xVelocity;
num_t yVelocity;
num_t angle;
num_t xPosition;
num_t yPosition;

void setAngle(void);
void addXVelocity(num_t aOutput);
void addYVelocity(num_t aOutput);
void setXPosition(num_t cLength);
void setYPosition(num_t cLength);

void setAngle(void) {
    if (num_sign(yVelocity) != 0) {
        num_t thisangle = num_div(num_mult(timeSlice, xAcceleration), yVelocity);
        angle = num_add(angle, thisangle);
    }
}

void addXVelocity(num_t aOutput) {
    xAcceleration = aOutput;
    xVelocity = num_add(xVelocity, num_mult(aOutput, timeSlice));
    setAngle();
    setXPosition(num_mult(xVelocity, timeSlice));
}

void addYVelocity(num_t aOutput) {
    yAcceleration = aOutput;
    yVelocity = num_add(yVelocity, num_mult(aOutput, timeSlice));
    setAngle();
    setYPosition(num_mult(yVelocity, timeSlice));
}

void setXPosition(num_t cLength) {
    xPosition = num_add(xPosition, num_mult(cLength, taylor_cos(angle)));
}

void setYPosition(num_t cLength) {
    yPosition = num_add(yPosition, num_mult(cLength, taylor_sin(angle)));
}

num_t getXPosition(void) {
    return xPosition;
}

num_t getYPosition(void) {
    return yPosition;
}

num_t getRotation(void) {
    return angle;
}

void calculationLoop(void) {
    BIGNUM_TYPE last;
    BIGNUM_TYPE now;
    struct timespec time;
    num_t conv;

    printk(KERN_INFO "Starting accelerometer calculations.\n");
    getnstimeofday(&time);
    last = timespec_to_ns(&time);
    conv = num_load(4096, 0);
    xAcceleration = num_zero;
    yAcceleration = num_zero;
    xVelocity = num_zero;
    yVelocity = num_zero;
    angle = num_zero;
    xPosition = num_zero;
    yPosition = num_zero;
    while (!kthread_should_stop()) {
        getnstimeofday(&time);
        now = timespec_to_ns(&time);
        timeSlice = num_div(num_load(now - last, 0), num_billion);
        if (num_sign(timeSlice) > 0) {
            addXVelocity(num_div(num_load(readAccelerometer(ACCEL_AXIS_X), 0), conv));
            addYVelocity(num_div(num_load(readAccelerometer(ACCEL_AXIS_Y), 0), conv));
            last = now;
        }
        schedule();
    }
    printk(KERN_INFO "Ending accelerometer calculations.\n");
}
