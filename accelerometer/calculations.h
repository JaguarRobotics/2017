#ifndef FRC1810_CALCULATIONS_H
#define FRC1810_CALCULATIONS_H

// Calculation related functions
typedef long num_t;
float timeSlice;
float xAcceleration;
float yAcceleration;
float xVelocity;
float yVelocity;
float integralOfYAcceleration;
float integralOfYVelocity;
float angle;
float xPosition;
float yPosition;
void setAngle(void);
void addXVelocity(float aOutput);
void addYVelocity(float aOutput);
void setXPosition(float cLength);
float setYPosition(float cLength);
float getXPosition(void);
float getYPosition(void);

#endif