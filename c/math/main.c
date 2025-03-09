#include <stdio.h>

#include "constants.h"

int int_square(int x) {
    return x << 1;
}

double double_square(double x) {
    return x * x;
}

double int_power_of(double x, int y) {
    double result = x;

    if (y == 0) { return 1; }
    if (y == 1) { return x; }

    for (int i = 2; i <= y; i++) {
        result *= x;
    }

    return result;
}

int factorial(int x) {
    if (x == 0 || x == 1) { return 1; }

    return x * factorial(x-1);
}

double e_to_the_x_taylor_series(double x, int n) {
    double result = 0;

    if (x == 0) { return 1; }
    if (x == 1) { return E; }
    if (x - (int)x == 0) { return int_power_of(E, x); }

    for (int i = 0; i <= n; i++) {
        result += int_power_of(x, i) / factorial(i);
    }

    return result;
}

int is_operator(char c) {
    for (int i = 0; i < NUMBER_OF_OPERATORS; i++) {
        if (c == OPERATORS[i]) {
            return 1;
        }
    }

    return 0;
}

void print_tok_eq_debug(char** eq) {
    for (int i = 0; i < MAX_TERMS_IN_EQUATION; i++) {
        for (int j = 0; j < MAX_TERM_LENGTH; j++) {
            printf("%c ", eq[i][j]);
        }
    }
}

void equation_tokeniser(char* eq) {
    int c;
    char tok[MAX_TERMS_IN_EQUATION][MAX_TERM_LENGTH];
    int current_term = 0;

    while (c != '\0') {
        c = *eq++;

        if (is_operator(c)) {
            c = *eq++; // skip over operator
            current_term++;
            break;
        }

        tok[current_term][c] = c;
    }

    print_tok_eq_debug(tok);
}

int main() {
    equation_tokeniser("abc+123");

    return 0;
}