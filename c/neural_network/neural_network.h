#ifndef NEURAL_NETWORK_H
#define NEURAL_NETWORK_H

#include "activation_functions.h"
#include "layer.h"
#include "matrix.h"

typedef struct NeuralNetwork {
    layer layers[3];
    int num_hidden_layers;
    int total_layers;
} neural_network;

neural_network create_network(int num_hid_layers, enum activation_function inp_func, enum activation_function out_func, enum activation_function hid_func);

void initialise_network(neural_network nn, matrix inputs, matrix outputs);

void print_neural_network(neural_network nn);

#endif