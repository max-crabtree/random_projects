#include <stdio.h>
#include <stdlib.h>

/* Struct for Pointer logic */
struct Pointer {
	struct Cell *previous;
	struct Cell *current;
	struct Cell *next;
};

/* Struct for Cell logic */
struct Cell {
	int number;
	int value;
};

struct Cell* makeNewCell(struct Pointer* currentCell) {
	printf("Making new cell...\n");
	struct Cell* newCell = (struct Cell*)malloc(sizeof(struct Cell));
	if (newCell != NULL) {
		newCell->number = currentCell->current->number + 1;
		newCell->value = 0;
		printf("New cell number is: %d\tValue is: %d\n", newCell->number, newCell->value);
		currentCell->next == newCell;
		printf("New cell created as the next cell!\n");
	}
	return newCell;
}

void increment(struct Pointer* currentCell) {
	//figure out how to condense these into variables and still work...
	//(readability)
	currentCell->current->value = currentCell->current->value + 1;
	//currentCell->current->value = currentCell->current->value + 1;
	printf("Cell %d's value is: %d\n", currentCell->current->number, currentCell->current->value);
}

void decrement(struct Pointer* currentCell) {
	currentCell->current->value = currentCell->current->value - 1;
	printf("Cell %d's value is: %d\n", currentCell->current->number, currentCell->current->value);
}

void shiftUp(struct Pointer* currentCell) {
	//stuck on one?
	printf("P: %d, C: %d, N:%d\n", currentCell->previous->number,
				       currentCell->current->number, 
				       currentCell->next->number);
	printf("Shifting up...\n");
	printf("Current cell was %d\n", currentCell->current->number);
	currentCell->previous = currentCell->current;
	if (currentCell->next == NULL) {
		currentCell->current = makeNewCell(currentCell);
	}
	else {
		currentCell->current = currentCell->next;
		currentCell->next = makeNewCell(currentCell);
	}
	printf("It is now: %d\n", currentCell->current->number);
}

void shiftDown(struct Pointer* currentCell) {
	printf("P: %d, C: %d, N:%d\n", currentCell->previous->number,
				       currentCell->current->number, 
				       currentCell->next->number);
	if (currentCell->current->number > 0) {
		printf("Shifting down...\n");
		printf("Current cell was %d\n", currentCell->current->number);
		currentCell->next = currentCell->current;
		currentCell->current = currentCell->previous;
		printf("It is now: %d\n", currentCell->current->number);
	}
	else {
		printf("Error! Cell number cannot be less than zero!\n");
	}
}

int printCellValue(struct Pointer* currentCell) {
	return currentCell->current->value;
}

void inputValueForCell(struct Pointer* currentCell) {
	int inputValue;
	printf("Input value for Cell %d: ", currentCell->current->number);
	scanf("%d", &inputValue);
	currentCell->current->value = inputValue;
	printf("Value of Cell %d is now %d", currentCell->current->number, currentCell->current->value);
}

int main() {
	struct Cell currentCell = {0, 0};
	//testing
	//struct Cell previousCell = {0, 0};
	//struct Cell nextCell = {2, 0};
	//struct Pointer pointer = {&previousCell, &currentCell, &nextCell};
	struct Cell cells[64];
	cells[0] = &currentCell;
	int lineCount = 0;
	char command;
	_Bool isWriting = 1;
	printf("Brain Fudge Interpreter\n");
	//make this into a buffer of sorts, so nothing actually happens until 'r' is hit!
	while (isWriting == 1) {
		printf("\n%d:", lineCount);
		scanf(" %c", &command);
		lineCount = lineCount + 1;
		switch (command) {
			case '>':
				shiftUp(&cells[0]);
				break;
			case '<':
				shiftDown(&cells[0]);
				break;
			case '+':
				increment(&cells[0]);
				break;
			case '-':
				decrement(&cells[0]);
				break;
			case '.':
				printf("%d\n", printCellValue(&cells[0]));
				break;
			case ',':
				inputValueForCell(&cells[0]);
				break;
			//more commands
			case 'r':
				isWriting = 0;
				break;
		}
	}
	printf("Writing stopped!\n");
	return 0;
	//run the interpreter
}
