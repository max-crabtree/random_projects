#ifndef LAYER_H
#define LAYER_H

#include "neuron.h"
#include "activation_functions.h"

#define MAX_LAYERS 5

enum layer_types {
    INPUT,
    HIDDEN,
    OUTPUT
};

typedef struct Layer {
    neuron* neurons;
    int max_neurons;
    enum activation_function func;
    enum layer_types type;
} layer;

void print_layer(layer l);

void set_max_neurons(layer l);

#endif