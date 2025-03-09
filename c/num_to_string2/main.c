#include <stdio.h>

#define MAX_NUM_LENGTH 128

const char* ones[9] = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" }; 
const char* tens[5] = { "ten", "twen", "thir", "for", "fif" };
const char* large[12] = { "hundred", "thousand", "m", "b", "tr", "quadr", "quint", "sext", "sept", "oct", "non", "dec" };
const char* larger[] = { "un", "duo", "tre", "quattuor", "quin", "sex" };
		

int strlen(char* str) {
	int count = 0;

	while(*str) {
		*str++;
		count++;
	}

	return count;
}


_Bool is_not_num(char* str) {
	while (*str) {
		if (!((*str >= '0' && *str <= '9') || *str == '.')) {
			return 1;
		}
		*str++;
	}

	return 0;
}


char* num_to_string(char* num_str) {



int main() {
	char inputted_num[MAX_NUM_LENGTH];

	printf("Hello, World!\nInput your num here: ");
	scanf("%s", inputted_num);

	if (is_not_num(inputted_num) || strlen(inputted_num) + 1 > MAX_NUM_LENGTH) {
		printf("Inputted number is invalid!\n");
		return -1;
	}

	printf("String num is: %s\n", num_to_string(inputted_num));
}
