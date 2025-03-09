#ifndef MATRIX_H
#define MATRIX_H

typedef struct Matrix {
    int x;
    int y;
    double* stored;
    int total_cells;
} matrix;

matrix create_matrix(int x, int y);

void fill_matrix(matrix m, double* values, int size);

void clone_matrix(matrix m1, matrix m2);

char* print_matrix(matrix m);

void free_matrix(matrix m);

matrix add_matrix(matrix m1, matrix m2);

matrix subtract_matrix(matrix m1, matrix m2);

matrix multiply_matrix(matrix m1, matrix m2);

double extract_value(matrix m);

matrix append_on_y(matrix m, double value);

matrix append_on_x(matrix m, double value);

matrix transpose(matrix m);

double dot_product(matrix m1, matrix m2);

#endif