#include <stdio.h>

#include "layer.h"
#include "neural_network.h"
#include "activation_functions.h"
#include "csv.h"

typedef enum activation_function act_func;

neural_network create_network(int num_hid_layers, act_func inp_func, act_func out_func, act_func hid_func) {
    if (num_hid_layers > (MAX_LAYERS - 2)) { return; }

    layer input = { .func = inp_func, .type = INPUT };
    layer output = { .func = out_func, .type = OUTPUT };

    neural_network nn = { .layers = { input, output }, .num_hidden_layers = num_hid_layers, .total_layers = num_hid_layers + 2 };

    for (int i = 1; i <= num_hid_layers - 2; i++) {
        layer h = { .func = hid_func, .type = HIDDEN };
        nn.layers[i] = h;
    }

    for (int i = 0; i < nn.total_layers; i++) {
        set_max_neurons(nn.layers[i]);
    }

    return nn;
}

void initialise_network(neural_network nn, matrix inputs, matrix outputs) {
    // number of neurons in input layer is determined by number of columns in .csv
    // implement this!

    // if output layer returns a value, one neuron; if it returns a label ("green", "red", "blue" for example),
    // also use one neuron unless SOFTMAX is used, then use one per label (3)

    // hidden layers, depends... probably 1 OR 0 ... number of neurons between input - output, the mean of them

    // process csv and get input matrix (and output if training)

    // if outputs NULL and training, throw error... else fine

    for (int i = 0; i < nn.total_layers; i++) { // 2 = NUM_OF_DELIMITERS
        layer current_layer = nn.layers[i];
        for (int j = 0; j < current_layer.max_neurons; j++) {
            if (current_layer.type == INPUT) {
                // num of neurons determined by number of input columns
                matrix inp_weights = create_matrix(1, training_csv.inputs.y);
                neuron inp = create_neuron(training_csv.inputs, inp_weights);
                current_layer.neurons[j] = inp;
            }
            if (current_layer.type == OUTPUT) {
                matrix out_weights = create_matrix(1, training_csv.inputs.y);
                neuron out = create_neuron(training_csv.outputs, out_weights);
                current_layer.neurons[j] = out;
            }
            if (current_layer.type == HIDDEN) {
                neuron hid = create_neuron(create_matrix(1,1), create_matrix(1,1));
                current_layer.neurons[j] = hid;
            }
        }
    }
}

void print_neural_network(neural_network nn) {
    printf("Neural Network:\nLayers\n");
    for (int i = 0; i < nn.total_layers; i++) {
        print_layer(nn.layers[i]);
    }
}