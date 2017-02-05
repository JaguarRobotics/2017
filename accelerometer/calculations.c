#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/kthread.h>
#include <linux/sched.h>
#include "calculations.h"
#include "io.h"
#include "taylor.h"

num_t timeSlice = ((num_t) (MULTIPLICATIVE_CONSTANT / 1600));
num_t xAcceleration = 0;
num_t yAcceleration = 0;
num_t xVelocity = 0;
num_t yVelocity = 0;
num_t integralOfYAcceleration = 0;
num_t integralOfYVelocity = 0;
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
        num_t thisangle = timeSlice * xAcceleration / yVelocity;
        angle += thisangle;
    }
}

void addXVelocity(num_t aOutput) {
    xAcceleration = aOutput;
    xVelocity += aOutput * timeSlice / MULTIPLICATIVE_CONSTANT;
    setAngle();
    setXPosition(xVelocity * timeSlice / MULTIPLICATIVE_CONSTANT);
}

void addYVelocity(num_t aOutput) {
    yAcceleration = aOutput;
    yVelocity += aOutput * timeSlice / MULTIPLICATIVE_CONSTANT;
    setAngle();
    setYPosition(yVelocity * timeSlice / MULTIPLICATIVE_CONSTANT);
}

void setXPosition(num_t cLength) {
    xPosition += cLength * taylor_cos(angle) / MULTIPLICATIVE_CONSTANT;
}

void setYPosition(num_t cLength) {
    yPosition += cLength * taylor_sin(angle) / MULTIPLICATIVE_CONSTANT;
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
    printk(KERN_INFO "Starting accelerometer calculations.\n");
    while (!kthread_should_stop()) {
        addXVelocity(MULTIPLICATIVE_CONSTANT * readAccelerometer(ACCEL_AXIS_X));
        addYVelocity(MULTIPLICATIVE_CONSTANT * readAccelerometer(ACCEL_AXIS_Y));
        schedule();
    }
    printk(KERN_INFO "Ending accelerometer calculations.\n");
}
