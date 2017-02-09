#ifndef FRC1810_BIGNUM_H
#define FRC1810_BIGNUM_H

#if __x86_64__ || __ppc64__
#define BIGNUM_TYPE signed long long
#define DECIMAL_PLACES 17
#define DECIMAL_PLACES_10 100000000000000000L
#define UPPER_SIGNIFICANCE 0xFFFFFFFF00000000
#define LOWER_SIGNIFICANCE 0x00000000FFFFFFFF
#define HALF_SIGNIFICANT_BITS 32
#else
#define BIGNUM_TYPE signed long
#define DECIMAL_PLACES 8
#define DECIMAL_PLACES_10 100000000
#define UPPER_SIGNIFICANCE 0xFFFF0000
#define LOWER_SIGNIFICANCE 0x0000FFFF
#define HALF_SIGNIFICANT_BITS 16
#endif

typedef struct {
    int sign;
    BIGNUM_TYPE whole;
    BIGNUM_TYPE decimal;
} num_t;

num_t num_load(int num);
num_t num_add(num_t a, num_t b);
num_t num_sub(num_t a, num_t b);
num_t num_mult(num_t a, num_t b);
num_t num_div(num_t num, num_t denom);
num_t num_invert(num_t num);
int num_compare(num_t lval, num_t rval);
int num_fmt(num_t val, char *buffer);

#endif
