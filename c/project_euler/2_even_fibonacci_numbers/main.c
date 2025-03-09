#include <stdio.h>

#define MAX_NUM 40000000000000
#define FACTOR 2

int nth_fib_num(int n) {
	if (n == 0 || n == 1) {
		return 1;
	}

	return nth_fib_num(n - 2) + nth_fib_num(n - 1);
}


int fib_sum() {
	int sum = 0;

	for (int i = 0; i < MAX_NUM; i++) {
		int current_num = nth_fib_num(i);

		if (current_num > MAX_NUM) {
			break;
		}

		if (current_num % FACTOR == 0) {
			printf("%d += %d\n", sum, current_num);
			sum += current_num;
		}
	}

	return sum;
}


int main() {
	printf("Sum of fibonacci numbers that divide into %d up to a max number of %d is: %d\n", FACTOR, MAX_NUM, fib_sum());
}
