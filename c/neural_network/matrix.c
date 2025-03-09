#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "matrix.h"
#include "config.h"

matrix create_matrix(int x, int y) {
    matrix m;

    if (x <= 0) { x = 1; }
    if (y <= 0) { y = 1; }
    
    m.x = x;
    m.y = y;
    m.stored = malloc(x * y * sizeof(double));
    m.total_cells = x * y;

    return m;
}

void fill_matrix(matrix m, double* values, int size) {
    if (size != m.total_cells) {
        printf("Cannot fill matrix!\n");
        return;
    }

    for (int i = 0; i < size; i++) {
        m.stored[i] = values[i];
    }
}

void clone_matrix(matrix m1, matrix m2) {
    m2 = m1; // ??
}

char* print_matrix(matrix m) {
    int buffer_size = ((2 + MAX_PRECISION) * sizeof(char)) * m.total_cells + 1;
    char* matrix_string = malloc(buffer_size);
    char double_string[MAX_PRECISION];

    if (!matrix_string) { return NULL; }

    int pos = 0;

    for (int i = 0; i < m.y; i++) {
        for (int j = 0; j < m.x; j++) {
            int index = i * m.x + j;
            matrix_string[pos++] = '[';   

            snprintf(double_string, MAX_PRECISION, "%f", m.stored[index]);

            for (int i = 0; i < MAX_PRECISION; i++) {
                if (double_string[i] == '\0') {
                    break;
                }
                matrix_string[pos++] = double_string[i];
            }

            matrix_string[pos++] = ']';                     
        }
    }

    matrix_string[pos] = '\0';
    return matrix_string;
}

void free_matrix(matrix m) {
    free(m.stored);
}

matrix append_on_y(matrix m, double value) {
    if (m.x != 1) {
        printf("append on y failed: matrix x != 1\n");
        return m;
    }

    matrix new_matrix = create_matrix(m.x, m.y + 1);

    fill_matrix(new_matrix, m.stored, new_matrix.total_cells);

    //printf("new matrix: %s\n", print_matrix(new_matrix));

    new_matrix.stored[new_matrix.total_cells - 1] = value;

    free_matrix(m);

    return new_matrix;
}

matrix append_on_x(matrix m, double value) {
    if (m.y != 1) {
        printf("append on x failed: matrix y != 1\n");
        return m;
    }

    matrix new_matrix = create_matrix(m.x + 1, m.y);

    fill_matrix(new_matrix, m.stored, new_matrix.total_cells);

    new_matrix.stored[new_matrix.total_cells - 1] = value;

    free_matrix(m);

    return new_matrix;
}

matrix add_matrix(matrix m1, matrix m2) {
    if (!(m1.x == m2.x) || !(m1.y == m2.y)) { 
        printf("Matrices cannot be added together!\n");
        return m1;
    }

    matrix m3 = create_matrix(m1.x, m1.y);

    for (int i = 0; i < m1.total_cells; i++) {
        m3.stored[i] = m1.stored[i] + m2.stored[i];
    }

    free_matrix(m1);
    free_matrix(m2);
    
    return m3;
}

matrix subtract_matrix(matrix m1, matrix m2) {
    if (!(m1.x == m2.x) || !(m1.y == m2.y)) { 
        printf("Matrices cannot be subtracted from eachother!\n");
        return m1; 
    }

    matrix m3 = create_matrix(m1.x, m1.y);

    for (int i = 0; i < m1.total_cells; i++) {
        m3.stored[i] = m1.stored[i] - m2.stored[i];
    }

    free_matrix(m1);
    free_matrix(m2);
    
    return m3;
}

matrix multiply_matrix(matrix m1, matrix m2) {
    if (m1.x != m2.y) {
        printf("Cannot multiply matrices!\n");
        return m1;
    }
    
    matrix m3 = create_matrix(m2.x, m1.y);

    int current_cell = 0;
    int column_reset = 0;
    double cell_sum = 0;
    int next_row_shift = 0;

    while (current_cell < (m2.x * m1.y)) {
        for (int i = 0; i < m1.x; i++) {
            cell_sum += m1.stored[i + next_row_shift] * m2.stored[i * m2.x + column_reset];
        }

        m3.stored[current_cell] = cell_sum;
        cell_sum = 0;
        current_cell++;
        column_reset++;

        if (current_cell != 0 && (current_cell % (m2.x) == 0)) {
            next_row_shift += m1.x;
            column_reset = 0;
        }
    }

    free_matrix(m1);
    free_matrix(m2);

    return m3;
}

matrix transpose(matrix m) {
    matrix temp_matrix = create_matrix(m.x, m.y);
    int rows_completed = 0;

    while (rows_completed < m.y) {
        for (int i = 0; i < m.x; i++) {
            temp_matrix.stored[i + ((m.x - 1) * i) + rows_completed] = m.stored[i + m.x * rows_completed];
        }

        rows_completed++;
    }

    free_matrix(m);

    return temp_matrix;
}

double dot_product(matrix m1, matrix m2) {
    if (m1.x == m2.x) {
        m1 = transpose(m1);
    }
    if (m1.y == m2.y) {
        m2 = transpose(m2);
    }

    double res = 0;

    for (int i = 0; i < m1.y; i++) {
        res += m1.stored[i] * m2.stored[i];
    }

    return res;
}

double extract_value(matrix m) {
    if (m.total_cells != 1) {
        printf("Cannot extract value!\n");
        return 0;
    }

    double value = m.stored[0];

    return value;
}