#include "activation_functions.h"
#include "nn_math.h"

double sigmoid(double x) {
    double f = 1 / (1 + ex_taylor_series(-x, TAYLOR_MAX_TERMS));

    return f;
}

double relu(double x) {
    if (x <= 0) { return 0; }
    return x;
}

double leaky_relu(double x) {
    if (x <= 0) { return LEAKY_RELU_GRADIENT * x; }
    return x;
}

int binary_step(double x) {
    if (x < 0) { return 0; }
    return 1;
}