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

void num_print(num_t a, char op, num_t b, num_t res) {
    char sa[64];
    char sb[64];
    char sc[64];

    num_fmt(a, sa, 63);
    num_fmt(b, sb, 63);
    num_fmt(res, sc, 63);
    printf("%s %c %s = %s\n", sa, op, sb, sc);
}

void testBignum(void) {
    num_t one = num_load(1, 0);
    num_t two = num_load(2, 0);
    num_t three = num_load(3, 0);
    num_t seven = num_load(7, 0);
    num_t one_five = num_div(three, two);

    num_print(two, '+', three, num_add(two, three));
    num_print(two, '+', one_five, num_add(two, one_five));
    num_print(two, 'x', three, num_mult(two, three));
    num_print(two, 'x', one_five, num_mult(two, one_five));
    num_print(one, '/', three, num_div(one, three));
    num_print(one, '/', seven, num_div(one, seven));
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
