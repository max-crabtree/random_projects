#include <stdio.h>

int factorial(int* number) {
	int current = 0;
	int result;
	for (int i = 0; i = number - 1; i++) {
		result = current;
		current = number - i;
		result = current * result;
	}
	return result;
}

int main() {
	int number;
	int result;
	puts("Factorial");
	puts("Type a (whole) number and the factorial will be printed out\n: ");
	scanf("%d", &number);
	printf("\n%d", factorial(&number));
	return 0;
}
