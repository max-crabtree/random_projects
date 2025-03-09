#include <stdio.h>

int main() {
    char str[20];
    FILE* first = fopen("num1.txt", "r");
    fread(str, 1, sizeof(str), first);
    fclose(first);
    FILE* second = fopen("num2.txt", "w");
    fputs(str, second);
    printf("%s\n", str);
    int c;
    while(c = fgetc(second) != EOF) {
        putchar(c);
    }
    fclose(second);
}