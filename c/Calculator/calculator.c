#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int addition(int x, int y) {
    int sum;
    sum = x + y;
    return sum;
}

int subtraction(int x, int y) {
    int difference;
    difference = x - y;
    return difference;
}

int multiplication(int x, int y) {
    int product;
    product = x * y;
    return product;
}

int division(int x, int y) {
    int quotient;
    quotient = x / y;
    return quotient;
}

int doOperation(char operation, int x, int y) {
    switch (operation) {
    case '+':
        printf("%d\n", addition(x, y));
        break;
    case '-':
        printf("%d\n", subtraction(x, y));
        break;
    case '*':
        printf("%d\n", multiplication(x, y));
        break;
    case '/':
        printf("%d\n", division(x, y));
        break;
    default:
        printf("\nErr!\n");
        break;
    }
}

void outputNumbers(char *eq) {
    char curr;
    char prev;
    char multi[9]; //max digits in one number
    int len;
    len = strlen(eq);
    int &num_arr[2] = {NULL};

    /* Detect numbers */
    for (int i = 0; i < len; i++) { 
        curr = eq[i];
        while (curr >= '0' && curr <= '9') {
            strcat(multi, curr);
            atoi(&multi);
            if (num_arr[0] == NULL) {
                num_arr[0] = multi;
            } else {
                num_arr[1] = multi;
            }
        }
        if (num_arr[1] != NULL) {
            curr = eq[i - 2];
            doOperation(multi, num_arr[0], num_arr[1]);
        } else {
            continue;
        }
    }
    //for (int i = 1; i < len; i++) {
    //    prev = eq[i - 1];
    //    curr = eq[i];
    //    while (curr >= '0' && curr <= '9') {
    //        curr = prev + curr;
    //        curr = atoi(&curr);
    //        if (num_arr[0] == 0) {
    //            num_arr[0] = curr; 
    //        } else {
    //            num_arr[1] = curr;
    //        }
    //        //i = i + 1;
    //    }
    //    if (num_arr[1] != 0) {
    //        curr = eq[i - 2];
    //        doOperation(curr, num_arr[0], num_arr[1]);
    //    } else {
    //        continue;
    //    }
    //}
}

int main() {
    const size_t LENGTH = 100;
    char equation[LENGTH];
    printf("Calculator\n");
    printf("Type equation here: ");
    fgets(equation, sizeof(equation), stdin);
    //char *trim = strtok(equation, " ");
    //while (trim) {
    //    trim = strtok(NULL, " ");
    //}
    printf("Parsing... %s", equation);
    outputNumbers(equation);
    /*char equation[20];
    char command;
    int firstNum;
    int secondNum;
    printf("Calculator\n");
    printf("Equation:\n");
    scanf("%s", &equation);
    printf("Is: %s", equation);
    printf("First Num: ");
    scanf("%d", &firstNum);
    printf("Second Num: ");
    scanf("%d", &secondNum);
    printf("Op: ");
    scanf(" %c", &command);
    calculatorLogic(command, firstNum, secondNum);
    scan through string? */
}