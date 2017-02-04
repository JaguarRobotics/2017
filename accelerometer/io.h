#ifndef FRC1810_IO_H
#define FRC1810_IO_H

#define ACCEL_AXIS_X 1
#define ACCEL_AXIS_Y 2
#define ACCEL_AXIS_Z 3
typedef int accel_axis_t;

// I/O related functions
// All of these must be implemented in both `io-mock.c` and `io-real.c`
/**
 * Reads an accelerometer's input and returns it
 *
 * @param axis The axis to read
 * @return The voltage output on the accelerometer (in mV)
 */
int readAccelerometer(accel_axis_t axis);

#endif
