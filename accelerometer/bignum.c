#ifdef __KERNEL__
#include <linux/kernel.h>
#include <linux/module.h>
#else
#include <stdio.h>
#endif
#include "bignum.h"

num_t num_load(int num) {
    num_t n = {
        .sign = num < 0 ? -1 : 1,
        .whole = num < 0 ? -num : num,
        .decimal = 0
    };
    return n;
}

num_t num_add(num_t a, num_t b) {
    num_t sum = {
        .sign = 1
    };

    if (a.sign < 0) {
        return num_sub(num_invert(a), b);
    } else if (b.sign < 0) {
        return num_sub(a, num_invert(b));
    } else {
        sum.decimal = a.decimal + b.decimal;
        sum.whole = a.whole + b.whole + (sum.decimal / DECIMAL_PLACES_10);
        sum.decimal %= DECIMAL_PLACES_10;
        return sum;
    }
}

num_t num_sub(num_t a, num_t b) {
    num_t diff;

    if (a.sign < 0) {
        return num_invert(num_add(num_invert(a), b));
    } else if (b.sign < 0) {
        return num_add(a, num_invert(b));
    } else {
        if (num_compare(a, b) < 0) {
            diff = a;
            a = b;
            b = diff;
            diff.sign = -1;
        } else {
            diff.sign = 1;
        }
        diff.whole = a.whole - b.whole;
        diff.decimal = a.decimal - b.decimal;
        if (diff.decimal < 0) {
            --diff.whole;
            diff.decimal += DECIMAL_PLACES_10;
        }
        return diff;
    }
}

num_t num_mult(num_t a, num_t b) {
    BIGNUM_TYPE leastSignificantA;
    BIGNUM_TYPE leastSignificantB;
    BIGNUM_TYPE leastSignificant;
    BIGNUM_TYPE lowerSignificantA;
    BIGNUM_TYPE lowerSignificantB;
    BIGNUM_TYPE lowerSignificant;
    BIGNUM_TYPE higherSignificantA;
    BIGNUM_TYPE higherSignificantB;
    BIGNUM_TYPE higherSignificant;
    BIGNUM_TYPE mostSignificantA;
    BIGNUM_TYPE mostSignificantB;
    BIGNUM_TYPE mostSignificant;
    num_t product;

    leastSignificantA = a.decimal & LOWER_SIGNIFICANCE;
    leastSignificantB = b.decimal & LOWER_SIGNIFICANCE;
    leastSignificant = leastSignificantA * leastSignificantB;
    lowerSignificantA = (a.decimal & UPPER_SIGNIFICANCE) >> HALF_SIGNIFICANT_BITS;
    lowerSignificantB = (b.decimal & UPPER_SIGNIFICANCE) >> HALF_SIGNIFICANT_BITS;
    lowerSignificant = lowerSignificantA * lowerSignificantB;
    higherSignificantA = a.whole & LOWER_SIGNIFICANCE;
    higherSignificantB = b.whole & LOWER_SIGNIFICANCE;
    higherSignificant = higherSignificantA * higherSignificantB;
    mostSignificantA = (a.whole & UPPER_SIGNIFICANCE) >> HALF_SIGNIFICANT_BITS;
    mostSignificantB = (b.whole & UPPER_SIGNIFICANCE) >> HALF_SIGNIFICANT_BITS;
    mostSignificant = mostSignificantA * mostSignificantB;

    lowerSignificant += leastSignificant >> HALF_SIGNIFICANT_BITS;
    leastSignificant &= LOWER_SIGNIFICANCE;
    higherSignificant += lowerSignificant % DECIMAL_PLACES_10;
    lowerSignificant /= DECIMAL_PLACES_10;
    mostSignificant += higherSignificant >> HALF_SIGNIFICANT_BITS;
    higherSignificant &= LOWER_SIGNIFICANCE;

    product.sign = a.sign * b.sign;
    product.decimal = leastSignificant | (lowerSignificant << HALF_SIGNIFICANT_BITS);
    product.whole = higherSignificant | (mostSignificant << HALF_SIGNIFICANT_BITS);
    return product;
}

num_t num_div(num_t num, num_t denom) {
    num_t quot;
    num_t rem;

    quot = num_load(0);
    rem = num;
    while (num_compare(rem, denom) > 0) {
        rem = num_sub(rem, denom);
        quot = num_add(quot, num_load(1));
    }
    return quot;
}

num_t num_invert(num_t num) {
    num.sign *= -1;
    return num;
}

int num_compare(num_t lval, num_t rval) {
    if (lval.sign < rval.sign) {
        return -1;
    } else if (lval.sign > rval.sign) {
        return 1;
    } else if (lval.whole < rval.whole) {
        return -1;
    } else if (lval.whole > rval.whole) {
        return 1;
    } else if (lval.decimal < rval.decimal) {
        return -1;
    } else if (lval.decimal > rval.decimal) {
        return 1;
    } else {
        return 0;
    }
}

int num_fmt(num_t val, char *buffer) {
    char fmt[10];

    if (val.sign < 0) {
        sprintf(fmt, "-%%d.%%0%dd", DECIMAL_PLACES);
    } else {
        sprintf(fmt, "%%d.%%0%dd", DECIMAL_PLACES);
    }
    return sprintf(buffer, fmt, val.whole, val.decimal);
}
