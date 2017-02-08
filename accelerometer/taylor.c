#include "taylor.h"

//                    3      5      7               n  2n+1
//                   x      x      x            (-1)  x
// sin(x) = lim x - ──── + ──── - ──── + ... + ─────────────
//          n→∞      3!     5!     7!             (2n+1)!
num_t taylor_sin(num_t rad) {
    num_t res = 0;
    num_t product;
    num_t rad2;
    num_t sign = 1;
    num_t factorial = MULTIPLICATIVE_CONSTANT;
    int n;

    if (rad < 0) {
        rad = -rad;
        sign = -1;
    }
    rad = MODULUS_RAW(rad, TAU);
    product = rad;
    rad2 = MULTIPLY(rad, rad);
    for (n = 0; n < TAYLOR_POLYNOMIAL; ++n) {
        res += DIVIDE(MULTIPLY_RAW(sign, product), factorial);
        product = MULTIPLY(product, rad2);
        factorial = MULTIPLY_RAW(factorial, (MULTIPLY_RAW(4, MULTIPLY_RAW(n, n)) + MULTIPLY_RAW(9, n) + 6));
        sign = -sign;
    }
    return res;
}

//                    2      4      6               n  2n
//                   x      x      x            (-1)  x
// cos(x) = lim 1 - ──── + ──── - ──── + ... + ───────────
//          n→∞      2!     4!     6!             (2n)!
num_t taylor_cos(num_t rad) {
    num_t res = MULTIPLICATIVE_CONSTANT;
    num_t product;
    num_t rad2;
    num_t sign = -1;
    num_t factorial = 2 * MULTIPLICATIVE_CONSTANT;
    int n;

    if (rad < 0) {
        rad = -rad;
    }
    rad = MODULUS_RAW(rad, TAU);
    product = rad2 = MULTIPLY(rad, rad);
    for (n = 1; n < TAYLOR_POLYNOMIAL; ++n) {
        res += DIVIDE(MULTIPLY_RAW(sign, product), factorial);
        product = MULTIPLY(product, rad2);
        factorial = MULTIPLY_RAW(factorial, (MULTIPLY_RAW(4, MULTIPLY_RAW(n, n)) + MULTIPLY_RAW(6, n) + 2));
        sign = -sign;
    }
    return res;
}
