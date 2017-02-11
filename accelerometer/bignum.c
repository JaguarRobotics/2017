#ifdef __KERNEL__
#include <linux/kernel.h>
#include <linux/module.h>
#else
#include <stdio.h>
#endif
#include "bignum.h"

//#define FORMAT_DEBUG

num_t num_minus_one;
num_t num_zero;
num_t num_tenth_down;
num_t num_tenth_up;
num_t num_one;
num_t num_two;
num_t num_tau;
num_t num_ten;
num_t num_billion;

void num_init(void) {
    num_minus_one = num_load(-1, 0);
    num_zero = num_load(0, 0);
    num_tenth_up = num_load(1, -3);
    num_tenth_down = num_load(1, -4);
    num_one = num_load(1, 0);
    num_two = num_load(2, 0);
    num_tau = num_load(BIGNUM_TAU, BIGNUM_TAU_OFFSET);
    num_ten = num_load(10, 0);
    num_billion = num_load(1000000000, 0);
}

num_t num_load(BIGNUM_TYPE number, BIGNUM_TYPE power) {
    num_t n = {
        .number = number,
        .power = power
    };
    return num_normalize(n);
}

num_t num_add(num_t a, num_t b) {
    if (a.number == 0) {
        return b;
    } else if (b.number == 0) {
        return a;
    }
    while (a.power > b.power) {
        b.number >>= 1;
        ++b.power;
    }
    while (a.power < b.power) {
        a.number >>= 1;
        ++a.power;
    }
    a.number += b.number;
    return num_normalize(a);
}

num_t num_sub(num_t a, num_t b) {
    return num_add(a, num_invert(b));
}

num_t num_mult(num_t a, num_t b) {
    a = num_normalize_half(a);
    b = num_normalize_half(b);
    a.number *= b.number;
    a.power += b.power;
    return num_normalize(a);
}

num_t num_div(num_t num, num_t denom) {
    denom = num_normalize_half(denom);
    num.number /= denom.number;
    num.power -= denom.power;
    return num_normalize(num);
}

void num_divmod(num_t num, num_t denom, BIGNUM_TYPE *quot, num_t *rem) {
    *quot = 0;
    *rem = num;
    while (num_compare(*rem, denom) >= 0) {
        *rem = num_sub(*rem, denom);
        ++*quot;
    }
}

num_t num_invert(num_t num) {
    num.number *= -1;
    return num;
}

int num_compare(num_t lval, num_t rval) {
    if (lval.number > 0 && rval.number < 0) {
        return 1;
    } else if (lval.number < 0 && rval.number > 0) {
        return -1;
    } else if (lval.power < rval.power) {
        return -1;
    } else if (lval.power > rval.power) {
        return 1;
    } else if (lval.number < rval.number) {
        return -1;
    } else if (lval.number > rval.number) {
        return 1;
    } else {
        return 0;
    }
}

int num_fmt(num_t val, char *buffer, int max) {
#ifdef FORMAT_DEBUG
    return sprintf(buffer, "%ld x 2^%d", val.number, val.power);
#else
    char *start;
    char *end;
    BIGNUM_TYPE quot;
    num_t tmp;
    num_t rem;
    num_t place;
    int i;
    
    if (val.number == 0) {
        *buffer = '0';
        *++buffer = 0;
        return 1;
    }
    start = buffer--;
    end = start + max - 1;
    rem = val;
    place = num_one;
    for (i = num_ceil_log10(num_abs(val)); i > 0; --i) {
        place = num_mult(place, num_ten);
    }
    if (rem.number < 0) {
        *++buffer = '-';
        rem.number *= -1;
    }
    while (rem.number != 0 && buffer != end) {
        if (num_compare(place, num_tenth_up) < 0 && num_compare(place, num_tenth_down) > 0) {
            *++buffer = '.';
        }
        tmp = rem;
        num_divmod(tmp, place, &quot, &rem);
        place = num_div(place, num_ten);
        *++buffer = '0' + quot;
    }
    while (num_compare(place, num_one) >= 0 && buffer != end) {
        *++buffer = '0';
        place = num_div(place, num_ten);
    }
    *++buffer = 0;
    return buffer - start;
#endif
}

num_t num_normalize(num_t val) {
    int sign = num_sign(val);

    switch (sign) {
        case -1:
            val.number *= -1;
        case 1:
            while (val.number <= (TARGET_VALUE >> 1)) {
                val.number <<= 1;
                --val.power;
            }
            while (val.number > TARGET_VALUE) {
                val.number >>= 1;
                ++val.power;
            }
            val.number *= sign;
    }
    return val;
}

num_t num_normalize_half(num_t val) {
    int sign = num_sign(val);

    switch (sign) {
        case -1:
            val.number *= -1;
        case 1:
            while (val.number <= (TARGET_VALUE_HALF >> 1)) {
                val.number <<= 1;
                --val.power;
            }
            while (val.number > TARGET_VALUE_HALF) {
                val.number >>= 1;
                ++val.power;
            }
            val.number *= sign;
    }
    return val;
}

int num_sign(num_t val) {
    if (val.number < 0) {
        return -1;
    } else if (val.number > 0) {
        return 1;
    } else {
        return 0;
    }
}

int num_floor_log2(num_t val) {
    BIGNUM_TYPE n = val.number;

    if (n == 0) {
        return 0;
    }
    n |= (n >> 1);
    n |= (n >> 2);
    n |= (n >> 4);
    n |= (n >> 8);
    n |= (n >> 16);
#if __x86_64__ || __ppc64__
    n |= (n >> 32);
#endif
    return num_pop(n >> 1) + val.power;
}

int num_ceil_log10(num_t val) {
    num_t place = num_ten;
    int i;
    int guess = num_floor_log2(val) / 3; // 3 ≈ log 10
                                         //        2
    if (guess <= 0) {
        return 0;
    }
    for (i = 1; i < guess; ++i) {
        place = num_mult(place, num_ten);
    }
    while (num_compare(place, val) > 0) {
        place = num_div(place, num_ten);
        --guess;
    }
    return guess;
}

int num_pop(BIGNUM_TYPE val) {
#if __x86_64__ || __ppc64__
    val = (val & 0x5555555555555555) + ((val >> 1) & 0x5555555555555555);
    val = (val & 0x3333333333333333) + ((val >> 2) & 0x3333333333333333);
    val = (val & 0x0F0F0F0F0F0F0F0F) + ((val >> 4) & 0x0F0F0F0F0F0F0F0F);
    val = (val & 0x00FF00FF00FF00FF) + ((val >> 8) & 0x00FF00FF00FF00FF);
    val = (val & 0x0000FFFF0000FFFF) + ((val >> 16) & 0x0000FFFF0000FFFF);
    val = (val & 0x00000000FFFFFFFF) + ((val >> 32) & 0x00000000FFFFFFFF);
#else
    val = (val & 0x55555555) + ((val >> 1) & 0x55555555);
    val = (val & 0x33333333) + ((val >> 2) & 0x33333333);
    val = (val & 0x0F0F0F0F) + ((val >> 4) & 0x0F0F0F0F);
    val = (val & 0x00FF00FF) + ((val >> 8) & 0x00FF00FF);
    val = (val & 0x0000FFFF) + ((val >> 16) & 0x0000FFFF);
#endif
    return val;
}

num_t num_abs(num_t val) {
    if (val.number < 0) {
        val.number *= -1;
    }
    return val;
}
