#ifndef CSV_H
#define CSV_H

#include "matrix.h"

#define MAX_LINE_LENGTH 50
#define DELIMITER_TYPE ","

typedef struct CSV {
    char* filename;
    matrix inputs;
    matrix outputs;
    int num_of_delimiters;
} csv;

void process_csv(char* filename);

#endif

extern csv training_csv;
//extern csv testing_csv;