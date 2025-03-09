#ifndef NEURON_H
#define NEURON_H

#include "matrix.h"

typedef struct Neuron {
    matrix inputs;
    matrix weights;
    matrix output;
    int bias;
} neuron;

neuron create_neuron(matrix inputs, matrix weights);

void print_neuron(neuron n);

double get_weighted_sum(neuron n);

#endif