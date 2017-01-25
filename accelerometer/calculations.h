#ifndef FRC1810_CALCULATIONS_H
#define FRC1810_CALCULATIONS_H

#define MULTIPLICATIVE_CONSTANT 1000

// Calculation related functions
typedef long num_t;
num_t timeSlice;
num_t xAcceleration;
num_t yAcceleration;
num_t xVelocity;
num_t yVelocity;
num_t integralOfYAcceleration;
num_t integralOfYVelocity;
num_t angle;
num_t xPosition;
num_t yPosition;
num_t setAngle(void);
num_t addXVelocity(num_t aOutput);
num_t addYVelocity(num_t aOutput);
num_t setXPosition(num_t cLength);
num_t setYPosition(num_t cLength);
num_t getXPosition(void);
num_t getYPosition(void);

#endif