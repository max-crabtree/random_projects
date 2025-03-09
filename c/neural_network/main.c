#include <stdio.h>
#include <string.h>

#include "matrix.h"
#include "neuron.h"
#include "activation_functions.h"
#include "nn_math.h"
#include "csv.h"
#include "neural_network.h"
#include "config.h"

int main() {
    printf("Hello, World\n");

    matrix m = create_matrix(5,1);

    matrix m2 = create_matrix(1,5);

    double values1[5] = {1,2,3,4,5};
    double values2[5] = {1,2,3,4,5};

    int size1 = sizeof(values1) / sizeof(values1[0]);
    int size2 = sizeof(values2) / sizeof(values2[0]);

    fill_matrix(m, values1, size1);
    fill_matrix(m2, values2, size2);

    neuron n = create_neuron(m, m2);

    print_neuron(n);

    free_matrix(m);
    free_matrix(m2);

    double x = 1.67489;
    
    printf("e^x Taylor Series: x=%f, n=%d -> %f\n", x, TAYLOR_MAX_TERMS, ex_taylor_series(x, TAYLOR_MAX_TERMS));

    printf("Sigmoid: x=%f -> %f\n", x, sigmoid(x));

    matrix m3 = create_matrix(1, 1);
    double v3[1] = {1.0}; 

    fill_matrix(m3, v3, 1);

    print_matrix(m3);

    m3 = append_on_y(m3, 2.0);

    print_matrix(m3);

    matrix m4 = create_matrix(3,3);
    double v4[9] = {2,4,6,1,3,5,7,8,9};
    fill_matrix(m4, v4, 9);
    print_matrix(m4);

    printf("Transposed:\n");
    m4 = transpose(m4);
    print_matrix(m4);

    matrix m5 = create_matrix(2,2);
    double v5[4] = {1,2,3,4};
    fill_matrix(m5, v5, 4);
    matrix m6;

    m6 = m5;

    print_matrix(m6);

    matrix m7 = create_matrix(1,2);
    matrix m8 = create_matrix(2,1);
    double v7_8[2] = {10,20};

    fill_matrix(m7, v7_8, 2);
    fill_matrix(m8, v7_8, 2);

    printf("Dot product of: %s and %s = %f\n", print_matrix(m7), print_matrix(m8), dot_product(m7, m8));

    char* filename = "sin_training_data.csv";
    
    process_csv(filename);

    printf("%s\n", print_matrix(training_csv.inputs));
    printf("%s\n", print_matrix(training_csv.outputs));

    printf("Dot product of inputs and outputs is: %f\n", dot_product(training_csv.inputs, training_csv.outputs));
    
    neural_network nn = create_network(1, RELU, BINARY_STEP, LEAKY_RELU);

    initialise_network(nn, training_csv.inputs, training_csv.outputs);

    print_neural_network(nn);

    char nn_choice[MAX_INPUT_LENGTH];
    //char* filename;
    _Bool input_is_valid = 1;

    while (input_is_valid) {
        printf("Are you training or testing? (1 or 2): ");

        scanf("%s", nn_choice);

        if (strcmp(nn_choice, "1") == 0) {
            strcpy(nn_choice, "training");
        } else if (strcmp(nn_choice, "2") == 0) {
            strcpy(nn_choice, "testing");
        } else {
            continue;
        }

        printf("What is the %s dataset name? (include .csv): ", nn_choice);

        scanf("%s", nn_choice);
    
        process_csv(nn_choice);
    }

    return 0;
}