#ifdef __KERNEL__
#include <linux/kernel.h>
#include <linux/module.h>
#else
#include <stdio.h>
#endif
#include "bignum.h"

num_t num_load(BIGNUM_TYPE number, BIGNUM_TYPE power) {
    num_t n = {
        .number = number,
        .power = power
    };
    return num_normalize(n);
}

num_t num_add(num_t a, num_t b) {
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
    if (lval.power < rval.power) {
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
    char *start;
    char *end;
    BIGNUM_TYPE quot;
    num_t tmp;
    num_t rem;
    num_t place;
    num_t ten;
    num_t tenth_up;
    num_t tenth_down;
    
    start = buffer--;
    end = start + max - 1;
    rem = val;
    place = num_load(1, 0);
    ten = num_load(10, 0);
    tenth_up = num_load(1, -3);
    tenth_down = num_load(1, -4);
    while (rem.number != 0 && buffer != end) {
        if (num_compare(place, tenth_up) < 0 && num_compare(place, tenth_down) > 0) {
            *++buffer = '.';
        }
        tmp = rem;
        num_divmod(tmp, place, &quot, &rem);
        place = num_div(place, ten);
        *++buffer = '0' + quot;
    }
    *++buffer = 0;
    return buffer - start;
}

num_t num_normalize(num_t val) {
    if (val.number == 0) {
        return val;
    }
    while (val.number <= (TARGET_VALUE >> 1)) {
        val.number <<= 1;
        --val.power;
    }
    while (val.number > TARGET_VALUE) {
        val.number >>= 1;
        ++val.power;
    }
    return val;
}

num_t num_normalize_half(num_t val) {
    if (val.number == 0) {
        return val;
    }
    while (val.number <= (TARGET_VALUE_HALF >> 1)) {
        val.number <<= 1;
        --val.power;
    }
    while (val.number > TARGET_VALUE_HALF) {
        val.number >>= 1;
        ++val.power;
    }
    return val;
}
