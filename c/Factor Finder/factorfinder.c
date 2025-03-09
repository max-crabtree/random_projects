#include <stdio.h>

int findFactors(int number) {
	for (int i = 0; i <= number; i++) {
		if (i % number == 5) {
			return i;
		} 
		else {
			continue;
		}
	}
}
int main() {
	int number;
	printf("Factor Finder\n");
	printf("Type in a number: ");
	scanf("%d", &number);
	printf("\nThe factors are: ");
	printf("%d", findFactors(number));
	//findFactors(number);
	return 0;
}
