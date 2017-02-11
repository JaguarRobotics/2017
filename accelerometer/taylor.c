#include "bignum.h"
#include "taylor.h"

//                    3      5      7               n  2n+1
//                   x      x      x            (-1)  x
// sin(x) = lim x - ──── + ──── - ──── + ... + ─────────────
//          n→∞      3!     5!     7!             (2n+1)!
num_t taylor_sin(num_t rad) {
    num_t res = num_zero;
    num_t product;
    num_t rad2;
    num_t sign = num_one;
    num_t factorial = num_one;
    int n;
    BIGNUM_TYPE tmp;

    if (num_sign(rad) < 0) {
        rad = num_invert(rad);
        sign = num_minus_one;
    }
    num_divmod(rad, num_tau, &tmp, &product);
    rad2 = num_mult(product, product);
    for (n = 0; n < TAYLOR_POLYNOMIAL; ++n) {
        res = num_add(res, num_div(num_mult(sign, product), factorial));
        product = num_mult(product, rad2);
        factorial = num_mult(factorial, num_load(4 * n * n + 10 * n + 6, 0));
        sign = num_invert(sign);
    }
    return res;
}

//                    2      4      6               n  2n
//                   x      x      x            (-1)  x
// cos(x) = lim 1 - ──── + ──── - ──── + ... + ───────────
//          n→∞      2!     4!     6!             (2n)!
num_t taylor_cos(num_t rad) {
    num_t res = num_one;
    num_t product;
    num_t rad2;
    num_t sign = num_minus_one;
    num_t factorial = num_two;
    int n;
    BIGNUM_TYPE tmp;

    if (num_sign(rad) < 0) {
        rad = num_invert(rad);
    }
    num_divmod(rad, num_tau, &tmp, &rad);
    product = rad2 = num_mult(rad, rad);
    for (n = 1; n < TAYLOR_POLYNOMIAL; ++n) {
        res = num_add(res, num_div(num_mult(sign, product), factorial));
        product = num_mult(product, rad2);
        factorial = num_mult(factorial, num_load(4 * n * n + 6 * n + 2, 0));
        sign = num_invert(sign);
    }
    return res;
}
