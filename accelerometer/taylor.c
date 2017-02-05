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
    num_t factorial = 1;
    int n;

    if (rad < 0) {
        rad = -rad;
        sign = -1;
    }
    rad %= TAU;
    product = rad;
    rad2 = rad * rad / MULTIPLICATIVE_CONSTANT;
    for (n = 0; n < TAYLOR_POLYNOMIAL; ++n) {
        res += sign * product * MULTIPLICATIVE_CONSTANT / factorial;
        product = product * rad2 / MULTIPLICATIVE_CONSTANT;
        factorial = factorial * (4 * n * n / MULTIPLICATIVE_CONSTANT + 9 * n + 6 * MULTIPLICATIVE_CONSTANT) / MULTIPLICATIVE_CONSTANT;
    }
    return res;
}

//                    2      4      6               n  2n
//                   x      x      x            (-1)  x
// cos(x) = lim 1 - ──── + ──── - ──── + ... + ───────────
//          n→∞      2!     4!     6!             (2n)!
num_t taylor_cos(num_t rad) {
    num_t res = 1;
    num_t product;
    num_t rad2;
    num_t sign = -1;
    num_t factorial = 2;
    int n;

    if (rad < 0) {
        rad = -rad;
    }
    rad %= TAU;
    product = rad2 = rad * rad / MULTIPLICATIVE_CONSTANT;
    for (n = 1; n < TAYLOR_POLYNOMIAL; ++n) {
        res += sign * product * MULTIPLICATIVE_CONSTANT / factorial;
        product = product * rad2 / MULTIPLICATIVE_CONSTANT;
        factorial = factorial * (4 * n * n / MULTIPLICATIVE_CONSTANT + 5 * n + 3 * MULTIPLICATIVE_CONSTANT) / MULTIPLICATIVE_CONSTANT;
    }
    return res;
}
