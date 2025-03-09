#include "nn_math.h"

int factorial(int x) {
    if (x == 0 || x == 1) { return 1; }

    return x * factorial(x-1);
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

double ex_taylor_series(double x, int n) {
    double result = 0;

    if (x == 0) { return 1; }
    if (x == 1) { return EULERS_NUMBER; }
    if (x - (int)x == 0) { return int_power_of(EULERS_NUMBER, x); }

    for (int i = 0; i <= n; i++) {
        result += int_power_of(x, i) / factorial(i);
    }

    return result;
}

//double root(double x, double y) {
//    return 0; // for weights init
//}