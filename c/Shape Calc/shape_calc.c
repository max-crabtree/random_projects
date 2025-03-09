#include <stdio.h>

int area(int x, int y) {
    int A;
    A = x * y;
    return A;
}

int perimeter(int x, int y) {
    int P;
    P = 2 * (x + y);
    return P;
}

int main() {
    int x, y;
    printf("Rectangle Calculator\n");
    printf("x: ");
    scanf("%d", &x);
    printf("y: ");
    scanf("%d", &y);
    printf("Area: %d\n", area(x, y));
    printf("Perimeter: %d\n", perimeter(x, y));
    return 0;
}