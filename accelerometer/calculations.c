#include <linux/module.h>
#include <linux/fixp-arith.h>
#include <linux/kernel.h>
#include <linux/kthread.h>
#include <linux/sched.h>
#include "calculations.h"

#define TAU ((num_t)(2 * 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136 * MULTIPLICATIVE_CONSTANT))

num_t timeSlice = 1600;
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
    num_t thisangle = ((yVelocity / timeSlice) * xAcceleration) / (yVelocity * yVelocity);
    angle += thisangle;
}

void addXVelocity(num_t aOutput) {
    xAcceleration = aOutput;
    xVelocity += (aOutput / timeSlice);
    setAngle();
    setXPosition(xVelocity / timeSlice);
}

void addYVelocity(num_t aOutput) {
    yAcceleration = aOutput;
    yVelocity += (aOutput / timeSlice);
    setAngle();
    setYPosition(yVelocity / timeSlice);
}

void setXPosition(num_t cLength) {
    xPosition += cLength * fixp_cos32_rad(angle, TAU);
}

void setYPosition(num_t cLength) {
    yPosition += cLength * fixp_sin32_rad(angle, TAU);
}

num_t getXPosition(void) {
    return xPosition;
}

num_t getYPosition(void) {
    return yPosition;
}

void calculationLoop(void) {
    printk(KERN_INFO "Starting accelerometer calculations.\n");
    while (!kthread_should_stop()) {
        // TODO get the accelerometer values and constantly calculate the new x and y positions
        // Some calculations
        schedule();
    }
    printk(KERN_INFO "Ending accelerometer calculations.\n");
}
