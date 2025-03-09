#ifndef ACTIVATION_FUNCTIONS_H
#define ACTIVATION_FUNCTIONS_H

#define LEAKY_RELU_GRADIENT 0.2

enum activation_function {
    NONE,
    SIGMOID,
    RELU,
    LEAKY_RELU,
    BINARY_STEP
};

double sigmoid(double x);

double relu(double x);

double leaky_relu(double x);

int binary_step(double x);

#endif