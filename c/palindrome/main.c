#include <stdio.h>

#define MAX_WORD_LEN 20


int strlen(char* str) {
	int c = *str;
	int count = 0;

	while (c != '\0') {
		c = *str++;
		count++;
	}

	return count;
}


char* reverse(char* str) {
	int str_len = strlen(str);
	char temp_str[str_len - 1];

	// - 2 for indexing + avoiding \0
	for (int i = str_len - 2; i >= 0; i--) {
		temp_str[str_len - i - 2] = *(str + i);
	}

	char* temp_p = &temp_str;

	return temp_p;
}


_Bool is_palindrome(char* str) {
	int str_len = strlen(str);
	char* rev_str = reverse(str);
	
	for (int i = 0; i < str_len; i++) {
		if (*(str + i) == rev_str[i]) {
			continue;
		} else {
			return 0;
		}
	}

	return 1;
}


int main() {
	char inp_str[MAX_WORD_LEN];
	printf("Input: ");
	scanf("%s", inp_str);

	if (is_palindrome(inp_str)) {
		printf("True\n");
	} else {
		printf("False\n");
	}
}

