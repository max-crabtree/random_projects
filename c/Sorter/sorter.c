//#include <stdio.h>
///* Min, Max, Mean */
//
//int ascendingSort(int previousNum, int currentNum) {
//    int min;
//    int max;
//    min = previousNum;
//    if (currentNum > previousNum) {
//        max = currentNum;
//        printf("Max: ");
//        return max;
//    }
//    printf("Min: ");
//    return min;
//}
//
//int main() {
//    int num_arr[] = {3, 2, 4, 1, 5};
//    for (int i = 0; i < 5; i++) {
//        ascendingSort(num_arr[i - 1], num_arr[i]);
//        //printf("Current Num: %d\n", num_arr[i]);
//    }
//}
//
//#include <stdio.h>
//
//int minimum(int previous, int current) {
//    int min = pre
//    if (min < current) {
//        min = previous
//    }
//}
//
//int main() {
//    int num_arr[] = {3, 2, 4, 1, 5};
//    int min_arr[] = {};
//
//    for (int i = 1; i < 5; i++) {
//        int previous = num_arr[i - 1];
//        int current = num_arr[i];
//        int min = minimum(previous, current);
//        min_arr[i - 1] = min;
//        printf("min_arr: %d\n", min_arr[i - 1]);
//        previous = current;
//    }
//}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    const size_t LENGTH = 100;
    char *inp = malloc(LENGTH * sizeof(char));
    int min = 0;
    int max = 0;
    int count = 0;
    int sum = 0;
    int avg = 0;

    fgets(inp, sizeof inp, stdin);
    char *trim = strtok(inp, " ");
    while (trim) {
        puts(trim);
        trim = strtok(NULL, " ");
    }
    free(inp);
}