#include <stdio.h>

#include "matrix.h"
#include "neuron.h"

neuron create_neuron(matrix inputs, matrix weights) {
    neuron n;

    n.inputs = inputs;
    n.weights = weights;
    n.bias = 0;

    return n;

    // inputs is long y, weights is long x
}

double get_weighted_sum(neuron n) {
    matrix matrix_sum = multiply_matrix(n.inputs, n.weights);

    double weighted_sum = extract_value(matrix_sum) + n.bias;

    return weighted_sum;
}

void print_neuron(neuron n) {
    printf("Inputs Matrix: %s\n", print_matrix(n.inputs));
    printf("Weights Matrix: %s\n", print_matrix(n.weights));
    printf("Bias: %d\n", n.bias);
}
