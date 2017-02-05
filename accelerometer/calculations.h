#ifndef FRC1810_CALCULATIONS_H
#define FRC1810_CALCULATIONS_H

#define MULTIPLICATIVE_CONSTANT 1000000
#define MULTIPLICATIVE_CONSTANT_LOG10 6
typedef long num_t;

// Calculation related functions
num_t getXPosition(void);
num_t getYPosition(void);
num_t getRotation(void);
void calculationLoop(void);

#endif
