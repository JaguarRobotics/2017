#ifndef FRC1810_CALCULATIONS_H
#define FRC1810_CALCULATIONS_H

#define MULTIPLICATIVE_CONSTANT ((num_t) 1000000)
#define MULTIPLICATIVE_CONSTANT_LOG10 6
typedef long long num_t;
#define TAU ((num_t) (2 * 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136 * MULTIPLICATIVE_CONSTANT))

// Math operations
#define DIVIDE_RAW(num, denom) (((num_t) num) / (num_t) denom)
#define MULTIPLY_RAW(a, b) (((num_t) a) * (num_t) b)
#define MODULUS_RAW(num, denom) (((num_t) num) % (num_t) denom)

#ifdef __ARM_EABI__
#ifdef __KERNEL__
#include <asm/div64.h>
#undef DIVIDE_RAW
#undef MODULUS_RAW
#define DIVIDE_RAW(num, denom) ({ \
    num_t a = num; \
    num_t b = denom; \
    do_div(a, b); \
})
#define MODULUS_RAW(num, denom) (((num_t) num) - MULTIPLY_RAW(DIVIDE_RAW(num, denom), denom))
#elif defined(__SIZE_OF_POINTER__) && __SIZE_OF_POINTER__ == 4
#undef DIVIDE_RAW
#undef MULTIPLY_RAW
#define DIVIDE_RAW(num, _denom) ({ \
    num_t quot = num; \
    num_t denom = _denom; \
    while (quot >= denom) { \
        quot -= denom; \
    } \
    quot; \
})
#define MULTIPLY_RAW(_a, _b) ({ \
    num_t a = _a; \
    num_t b = _b; \
    num_t product = 0; \
    num_t mask = 1; \
    while (mask != 0) { \
        if (b & mask) { \
            product += a; \
        } \
        a <<= 1; \
        mask <<= 1; \
    } \
    product; \
})
#endif
#endif

#define DIVIDE(num, denom) DIVIDE_RAW(MULTIPLY_RAW(num, MULTIPLICATIVE_CONSTANT), denom)
#define MULTIPLY(a, b) DIVIDE_RAW(MULTIPLY_RAW(a, b), MULTIPLICATIVE_CONSTANT)

// Calculation related functions
num_t getXPosition(void);
num_t getYPosition(void);
num_t getRotation(void);
void calculationLoop(void);

#endif
