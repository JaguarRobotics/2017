#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/kthread.h>
#include <linux/sched.h>
#include <linux/time.h>
#include "calculations.h"
#include "io.h"
#include "taylor.h"

num_t timeSlice;
num_t xAcceleration = 0;
num_t yAcceleration = 0;
num_t xVelocity = 0;
num_t yVelocity = 0;
num_t angle = 0;
num_t xPosition = 0;
num_t yPosition = 0;

void setAngle(void);
void addXVelocity(num_t aOutput);
void addYVelocity(num_t aOutput);
void setXPosition(num_t cLength);
void setYPosition(num_t cLength);

void setAngle(void) {
    if (yVelocity != 0) {
        num_t thisangle = DIVIDE(MULTIPLY_RAW(timeSlice, xAcceleration), yVelocity);
        angle += thisangle;
    }
}

void addXVelocity(num_t aOutput) {
    xAcceleration = aOutput;
    xVelocity += MULTIPLY(aOutput, timeSlice);
    setAngle();
    setXPosition(MULTIPLY(xVelocity, timeSlice));
}

void addYVelocity(num_t aOutput) {
    yAcceleration = aOutput;
    yVelocity += MULTIPLY(aOutput, timeSlice);
    setAngle();
    setYPosition(MULTIPLY(yVelocity, timeSlice));
}

void setXPosition(num_t cLength) {
    xPosition += MULTIPLY(cLength, taylor_cos(angle));
}

void setYPosition(num_t cLength) {
    yPosition += MULTIPLY(cLength, taylor_sin(angle));
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
    num_t last;
    num_t now;
    struct timespec time;

    printk(KERN_INFO "Starting accelerometer calculations.\n");
    getnstimeofday(&time);
    last = timespec_to_ns(&time);
    while (!kthread_should_stop()) {
        getnstimeofday(&time);
        now = timespec_to_ns(&time);
        timeSlice = DIVIDE_RAW(MULTIPLY_RAW((now - last), MULTIPLICATIVE_CONSTANT), 1000000000);
        if (timeSlice > 0) {
            addXVelocity(DIVIDE_RAW(MULTIPLY_RAW(MULTIPLICATIVE_CONSTANT, readAccelerometer(ACCEL_AXIS_X)), 4096));
            addYVelocity(DIVIDE_RAW(MULTIPLY_RAW(MULTIPLICATIVE_CONSTANT, readAccelerometer(ACCEL_AXIS_Y)), 4096));
            last = now;
        }
        schedule();
    }
    printk(KERN_INFO "Ending accelerometer calculations.\n");
}
