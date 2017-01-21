#include "calculations.h"
#include <cmath.h>

float timeSlice = 1/1600;
float xAcceleration = 0.0;
float yAcceleration = 0.0;
float xVelocity = 0.0;
float yVelocity = 0.0;
float integralOfYAcceleration = 0.0;
float integralOfYVelocity = 0.0;
float angle = 0.0;
float xPosition = 0.0;
float yPosition = 0.0;
//float integralOfPosition = 0.0;

void setAngle(void) {
    float thisangle = (yVelocity * timeSlice * xAcceleration) / (yVelocity * yVelocity);
    angle += thisangle;
}

void addXVelocity(float aOutput) {
    xAcceleration = aOutput;
    xVelocity += aOutput * timeSlice;
    setAngle(void);
    setXPosition(xVelocity * timeSlice);
}

void addYVelocity(float aOutput) {
    yAcceleration = aOutput;
    yVelocity += aOutput * timeSlice;
    setAngle(void);
    setYPosition(yVelocity * timeSlice);
}

void setXPosition(float cLength) {
    xPosition += cLength * cos(angle);
}

void setYPosition(float cLength) {
    yPosition += cLength * sin(angle);
}

float getXPosition(void) {
    return xPosition;
}

float getYPosition(void) {
    return yPosition;
}