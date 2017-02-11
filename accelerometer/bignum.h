#ifndef FRC1810_BIGNUM_H
#define FRC1810_BIGNUM_H

#if __x86_64__ || __ppc64__
#define BIGNUM_TYPE signed long long
#define TARGET_VALUE 0x0800000000000000
#define TARGET_VALUE_HALF 0x0000000080000000
#define BIGNUM_TAU 0b110010010000111111011010101000100010000101101000110000100011010
#define BIGNUM_TAU_OFFSET -60
#else
#define BIGNUM_TYPE signed long
#define TARGET_VALUE 0x08000000
#define TARGET_VALUE_HALF 0x00008000
#define BIGNUM_TAU 0b1100100100001111110110101010001
#define BIGNUM_TAU_OFFSET -28
#endif

typedef struct {
    BIGNUM_TYPE number;
    BIGNUM_TYPE power;
} num_t;

extern num_t num_minus_one;
extern num_t num_zero;
extern num_t num_one;
extern num_t num_two;
extern num_t num_tau;
extern num_t num_ten;
extern num_t num_billion;

void num_init(void);
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
int num_sign(num_t val);
int num_floor_log2(num_t val);
int num_ceil_log10(num_t val);
int num_pop(BIGNUM_TYPE val);
num_t num_abs(num_t val);

#endif
