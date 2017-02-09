#include <stdio.h>
#include <stdlib.h>
#include "bignum.h"
//#include "calculations.h"
//#include "taylor.h"

//#define TEST_TAYLOR
#define TEST_BIGNUM

int status;

/*
void testTaylor(void) {
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
*/

void num_print(num_t num) {
    char buffer[64];

    num_fmt(num, buffer);
    printf(buffer);
}

void testBignum(void) {
    num_t a = num_load(2);
    num_t b = num_load(3);
    num_t c = num_div(b, a);
    num_t d;

    d = num_add(a, b);
    num_print(a); printf(" + "); num_print(b); printf(" = "); num_print(d); printf("\n");
    d = num_add(a, c);
    num_print(a); printf(" + "); num_print(c); printf(" = "); num_print(d); printf("\n");
    d = num_mult(a, b);
    num_print(a); printf(" x "); num_print(b); printf(" = "); num_print(d); printf("\n");
}

int main(void) {
    status = EXIT_SUCCESS;
#ifdef TEST_TAYLOR
    testTaylor();
#endif
#ifdef TEST_BIGNUM
    testBignum();
#endif
    return status;
}
