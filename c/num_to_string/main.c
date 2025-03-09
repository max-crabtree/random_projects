#include <stdio.h>

#define MAX_NUM_LENGTH 350

char* process_str(char* str) {
    for (int i = 0; i < sizeof *str; i++) {
        char current_digit = str[i];

        if ((current_digit >= '0' && current_digit <= '9') || current_digit == '.') {
            continue;
        } else {
            str[i] = '\0';
            break;
        }
    }

    return str;
}

char* readable_num(double num) {
    char buf_str[MAX_NUM_LENGTH];
    char* num_str;

    snprintf(buf_str, MAX_NUM_LENGTH, "%f", num);

    num_str = process_str(buf_str);

    printf("Num string is: ");
    for (int i = 0; i < sizeof num_str; i++) {
        printf("%c", num_str[i]);
    }
    printf("\n");

    return "";
}

int main() {
    printf("Hello World!\n");
    printf("Number to String\n");
    printf("Type your number here: ");

    double inputted_number = 0;

    scanf("%lf", &inputted_number);

    char* temp = readable_num(inputted_number);
}