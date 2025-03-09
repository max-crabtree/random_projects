#include <stdio.h>

#include "activation_functions.h"
#include "layer.h"
#include "csv.h"

//layer create_layer(neuron* neurons, activation_function func) {
//    layer l;
//    l.neurons = neurons;
//    l.func = func;
//
//    return l;
//}

void set_max_neurons(layer l) {
    switch (l.type) {
        // use diff csv
        case INPUT: l.max_neurons = training_csv.num_of_delimiters; break;
        case OUTPUT: l.max_neurons = 1; break;
        case HIDDEN: l.max_neurons = 1; break; // one for now
        default: break;
    }
}

char* print_layer_type(layer l) {
    switch (l.type) {
        case INPUT: return "Input";
        case OUTPUT: return "Output";
        case HIDDEN: return "Hidden";
        default: return "Err";
    }
}

void print_layer(layer l) {
    printf("%s Layer:\nFunc: %d\nMax Neurons: %d\nNeurons:\n", print_layer_type(l), l.func, l.max_neurons);
    for (int i = 0; i < l.max_neurons; i++) {
        print_neuron(l.neurons[i]);
    }
}
