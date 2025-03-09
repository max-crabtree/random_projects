#include <stdio.h>

#define MAX_INPUT_LENGTH 50

int to_list(int input_numbers) {
	int updated_list[20];
	for (int i = 0; i < MAX_INPUT_LENGTH; i++) {
		int current = input_numbers[&i];
		if (i != ' ') {
			updated_list[&i] == current;
		}
		else {
			continue;
		}
	}
	return updated_list;
}

int main() {
	char input_numbers[MAX_INPUT_LENGTH];
	int number_list[20];
	printf("Hello World!\n");
	printf("Sort Visualiser and Timer\n");
	printf("Type in a list of numbers (split with spaces): ");
	fgets(input_numbers, MAX_INPUT_LENGTH, stdin);
	number_list = to_list(input_numbers);
	/* Then do sorting */
	return 0;
}
