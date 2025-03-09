#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_INPUT_SIZE 1000

//void getWords(char[] text) {
//	for (int i = 0; i < sizeof(text); i++) {
//		char word[i][10];
//		for (int j = 1; j < sizeof(text); j++) {
//			char current = text[j];
//			char previous = text[j - 1];
//			if (current == '\n') {
//				break;
//			}
//			else {
//				word[i][10] = strcat(&previous, &current);
//			}
//			previous = current;
//		}
//		printf("Word %d = %s\n", i, word[i]);
//	}
//}

void timedMode() {
	int time;
	char *testString = "Hello World!";
	char *inputString;
	inputString = (char*)malloc(MAX_INPUT_SIZE);
	printf("Timed Mode\nWrite out as much as the string as you can in time!\n");
	printf("How much time would you like?\n-> ");
	scanf("%d", &time);
	printf("String is: %s\n", testString);
	printf("Hit ENTER to start typing\n");
	clock_t start = clock();
	do {
		scanf("%s", inputString);
	} while (start < time);
	clock_t end = clock();
	double timeTaken = ((double) end - start) / CLOCKS_PER_SEC;
	int accuracy = (strcmp(testString, inputString));
	printf("\nTime elapsed: %f\tAccuracy: %d\tWPM: /", timeTaken, accuracy);
	free(inputString);
}

int main() {
	const char* FILE_NAME = "words.txt";
	char mode;
	printf("Hello World!\n");
	printf("Typing Tutor\n");
	printf("Pick a mode. 't' for timed and 'r' for random\n->");
	scanf(" %c", &mode);
	if (mode == 't') {
		timedMode();
	} else {
		perror("Bad input!\n");
		return EXIT_FAILURE;
	}
	//FILE* words = fopen(FILE_NAME, "r");
	//if (!words) {
	//	perror("Error opening file!");
	//	return EXIT_FAILURE;
	//}

	//char wordsFile[sizeof(words)];
	//int c;
	//while ((c = fgetc(words)) != EOF) {
	//	strcat(wordsFile, (char)c);
	//}
	//getWords(wordsFile);

	//fclose(words);
	return 0;
}
