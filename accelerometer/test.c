#include <stdio.h>
#include <stdlib.h>
#include "calculations.h"
#include "taylor.h"

int main(void) {
    char *format;
    num_t res;
    num_t ipart;
    num_t fpart;

    format = (char *) malloc(256);
#define STR(x) # x
#define CONCAT(a, b) a ## b
#define TEST_TRIG(fn, denom) \
    sprintf(format, STR(fn) " \u2500 \u2248 %%d.%%0%dd\n", MULTIPLICATIVE_CONSTANT_LOG10); \
    res = CONCAT(taylor_, fn)(TAU / (2 * denom)); \
    ipart = res / MULTIPLICATIVE_CONSTANT; \
    fpart = res % MULTIPLICATIVE_CONSTANT; \
    if (fpart < 0) { \
        --ipart; \
        fpart = -fpart; \
    } \
    puts("    \u03C0"); \
    printf(format, ipart, fpart); \
    puts("    " STR(denom) "\n");
    TEST_TRIG(sin, 2)
    TEST_TRIG(sin, 4)
    TEST_TRIG(cos, 2)
    TEST_TRIG(cos, 4)
    printf("(Calculated with the %dth taylor polynomial and a multiplicative constant of %ld)\n", TAYLOR_POLYNOMIAL, (long) MULTIPLICATIVE_CONSTANT);
}
