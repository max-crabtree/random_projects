#include <stdio.h>

#define MAX_NUM 100

int main() {
	for (int i = 1; i <= MAX_NUM; i++) {
		if (i % 3 == 0 && i % 5 == 0) {
			printf("FizzBuzz\n");
			continue;
		}
		if (i % 3 == 0) {
			printf("Fizz\n");
			continue;
		}
		if (i % 5 == 0) {
			printf("Buzz\n");
			continue;
		}
		printf("%d\n", i);
	}
}
