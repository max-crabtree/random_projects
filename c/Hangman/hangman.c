#include <stdio.h>

char* generateWord() {
	//pick random word
	//return word

int main() {
	printf("Hangman Game\n");
	printf("Generating word...\n");
	char* game_word = generateWord();
	doRound(game_word);
	return 0;
}
