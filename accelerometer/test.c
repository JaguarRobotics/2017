#include <stdio.h>
#include <stdlib.h>
#include "bignum.h"
#include "taylor.h"

#define TEST_TAYLOR
#define TEST_BIGNUM

int status;

void taylor_print(int denom, const char *func, num_t res) {
    char buffer[64];

    num_fmt(res, buffer, 63);
    puts("    \u03C0");
    printf("%s \u2500 \u2248 %s\n", func, buffer);
    printf("    %d\n", denom);
}

void taylor_print_un(num_t val, const char *func, num_t res) {
    char buffer[64];

    num_fmt(val, buffer, 63);
    printf("%s %s = ", func, buffer);
    num_fmt(res, buffer, 63);
    printf("%s\n", buffer);
}

void test_taylor(void) {
    taylor_print(2, "sin", taylor_sin(num_div(num_tau, num_load(4, 0))));
    taylor_print(4, "sin", taylor_sin(num_div(num_tau, num_load(8, 0))));
    taylor_print(2, "cos", taylor_cos(num_div(num_tau, num_load(4, 0))));
    taylor_print(4, "cos", taylor_cos(num_div(num_tau, num_load(8, 0))));
    printf("(Calculated with the %dth taylor polynomial)\n", TAYLOR_POLYNOMIAL);
}

void num_print(num_t a, char op, num_t b, num_t res) {
    char sa[64];
    char sb[64];
    char sc[64];

    num_fmt(a, sa, 63);
    num_fmt(b, sb, 63);
    num_fmt(res, sc, 63);
    printf("%s %c %s = %s\n", sa, op, sb, sc);
}

void num_print_un(num_t val, const char *func, int res) {
    char buffer[64];
    num_fmt(val, buffer, 63);
    printf("%s %s = %d\n", func, buffer, res);
}

num_t num_mod(num_t num, num_t denom) {
    BIGNUM_TYPE tmp;
    num_t mod;

    num_divmod(num, denom, &tmp, &mod);
    return mod;
}

void test_bignum(void) {
    num_t minus_one = num_load(-1, 0);
    num_t zero = num_load(0, 0);
    num_t one = num_load(1, 0);
    num_t two = num_load(2, 0);
    num_t three = num_load(3, 0);
    num_t seven = num_load(7, 0);
    num_t eight = num_load(8, 0);
    num_t one_five = num_div(three, two);

    num_print(two, '+', three, num_add(two, three));
    num_print(two, '+', one_five, num_add(two, one_five));
    num_print(two, 'x', three, num_mult(two, three));
    num_print(two, 'x', one_five, num_mult(two, one_five));
    num_print(one, '/', three, num_div(one, three));
    num_print(one, '/', seven, num_div(one, seven));
    num_print(minus_one, 'x', one, num_mult(minus_one, one));
    num_print(seven, '%', three, num_mod(seven, three));
    num_print(three, '%', seven, num_mod(three, seven));
    num_print(minus_one, '/', one, num_div(minus_one, one));
    num_print(zero, '+', minus_one, num_add(zero, minus_one));
    num_print(num_tau, '+', zero, num_add(num_tau, zero));
    num_print_un(seven, "log2", num_floor_log2(seven));
    num_print_un(eight, "log2", num_floor_log2(eight));
    num_print_un(num_billion, "log10", num_ceil_log10(num_billion));
    num_print(num_billion, '*', num_billion, num_mult(num_billion, num_billion));
}

int main(void) {
    status = EXIT_SUCCESS;
    num_init();
#ifdef TEST_TAYLOR
    test_taylor();
#endif
#ifdef TEST_BIGNUM
    test_bignum();
#endif
    return status;
}
