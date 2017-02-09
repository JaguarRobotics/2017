#ifndef FRC1810_BIGNUM_H
#define FRC1810_BIGNUM_H

#if __x86_64__ || __ppc64__
#define BIGNUM_TYPE signed long long
#define TARGET_VALUE 0x0200000000000000
#define TARGET_VALUE_HALF 0x0000000040000000
#else
#define BIGNUM_TYPE signed long
#define TARGET_VALUE 0x02000000
#define TARGET_VALUE_HALF 0x00004000
#endif

typedef struct {
    BIGNUM_TYPE number;
    BIGNUM_TYPE power;
} num_t;

num_t num_load(BIGNUM_TYPE number, BIGNUM_TYPE power);
num_t num_add(num_t a, num_t b);
num_t num_sub(num_t a, num_t b);
num_t num_mult(num_t a, num_t b);
num_t num_div(num_t num, num_t denom);
void num_divmod(num_t num, num_t denom, BIGNUM_TYPE *quot, num_t *rem);
num_t num_invert(num_t num);
int num_compare(num_t lval, num_t rval);
int num_fmt(num_t val, char *buffer, int max);
num_t num_normalize(num_t val);
num_t num_normalize_half(num_t val);

#endif
