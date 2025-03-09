#include <stdio.h>

int main() {
    printf("sizeof(char*) = %d\n", sizeof(char*));
    printf("sizeof(int*) = %d\n", sizeof(int*));
    printf("sizeof(_Bool*) = %d\n", sizeof(_Bool*));
    printf("sizeof(void*) = %d\n", sizeof(void*));
    printf("sizeof(char) = %d\n", sizeof(char));
    printf("sizeof(int) = %d\n", sizeof(int));
    printf("sizeof(_Bool) = %d\n", sizeof(_Bool));
    printf("sizeof(void) = %d\n", sizeof(void));

    return 0;
}