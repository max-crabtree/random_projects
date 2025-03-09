#include "matrix.h"

double mean_squared_error(matrix predicted_output, matrix desired_output) {
    if (predicted_output.y != desired_output.y) {
        printf("Error with test data! (MeanSquaredError)\n");
        return 0;
    }

    matrix errors_vector = create_matrix(1, predicted_output.y);

    errors_vector = subtract_matrix(desired_output, predicted_output);

    return (1 / predicted_output.y) * dot_product(errors_vector, errors_vector);
}