#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "csv.h"
#include "matrix.h"

csv training_csv;

void process_csv(char* filename) {
    char line[MAX_LINE_LENGTH];
    int current_line = 0;

    FILE* file = fopen(filename, "r");

    if (!file) {
        printf("File %s not found!\n", filename);
        return;
    }

    matrix input_matrix = create_matrix(1, 1);
    matrix output_matrix = create_matrix(1, 1);

    // calculate num of delimiters

    //strncpy(training_csv.filename, filename, sizeof(filename));
    training_csv.num_of_delimiters = 2;
    //csv testing_csv = { .inputs = input_matrix, .filename = filename, .num_of_delimiters = 2 };

    while (fgets(line, MAX_LINE_LENGTH, file)) {
        line[strcspn(line, "\n")] = '\0';

        char* token = strtok(line, DELIMITER_TYPE);
        int cell_index = 0;

        while(token) {
            double value = atof(token);

            // expand this to support more delimiters
            if (cell_index == 0) {
                if (current_line == 0) {
                    fill_matrix(input_matrix, &value, 1);
                } else {
                    input_matrix = append_on_y(input_matrix, value);
                }
            }
            if (cell_index == 1) {
                if (current_line == 0) {
                    fill_matrix(output_matrix, &value, 1);
                } else {
                    output_matrix = append_on_x(output_matrix, value);
                }
            }
            token = strtok(NULL, DELIMITER_TYPE);
            cell_index++;
        }
        current_line++;
    }

    fclose(file);

    training_csv.inputs = input_matrix;
    training_csv.outputs = output_matrix;
}