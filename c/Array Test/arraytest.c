#include <stdio.h>

int increment(int array, int index) {
	index = index + 1;
	array = array[index];
	return index;
}

int main() {
	int arr[10];
	int index;
	int res;

	res = increment(arr[0], 0);
	printf("%d", res);
}
