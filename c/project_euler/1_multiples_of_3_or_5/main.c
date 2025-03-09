#include <stdio.h>

#define LOW_NUM 1
#define MAX_NUM 1000

int main() {
	int sum = 0;

	for (int i = LOW_NUM; i < MAX_NUM; i++) {
		if (i % 3 == 0 || i % 5 == 0) {
			sum += i;
		}
	}

	printf("Sum of numbers from %d to %d is %d!\n", LOW_NUM, MAX_NUM, sum);

	return 0;
}
