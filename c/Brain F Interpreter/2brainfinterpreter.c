#include <stdio.h>
#include <stdlib.h>

#define AMOUNT_OF_CELLS 64

struct Cell {
	int number;
	int value;
};

void increment(struct Cell* currentCell) {
	currentCell->value = currentCell->value + 1;
	printf("Cell %d value is %d\n", currentCell->number, currentCell->value);
}

void decrement(struct Cell* currentCell) {
	currentCell->value = currentCell->value - 1;
	printf("Cell %d value is %d\n", currentCell->number, currentCell->value);
}

//how to shift up the cells array??
void shiftUp(struct Cell* currentCell, int* index) {
	//currentCell->number = currentCell->number + 1;
	currentCell[index] = currentCell[index + 1];
	printf("Cell is %d", currentCell->number);
}

void shiftDown(struct Cell* currentCell) {
	if (currentCell > 0) {
		currentCell = currentCell - 1;
		printf("Cell is %d", currentCell->number);
	}
	else {
		printf("Err!");
	}
}

int printCellValue(struct Cell* currentCell) {
	return currentCell->value;
}

void inputValueForCell(struct Cell* currentCell) {
	int inputValue;
	scanf("%d", &inputValue);
	currentCell->value = inputValue;
}

int main() {
	struct Cell initialCell = {0, 0};
	struct Cell cells[AMOUNT_OF_CELLS];
	cells[initialCell.number] = initialCell;
	int lineCount = 0;
	char command;
	_Bool isWriting = 1;
	printf("Brain Fudge Interpreter 2\n");
	while (isWriting == 1) {
		printf("\n%d:", lineCount);
		scanf(" %c", &command);
		lineCount = lineCount + 1;
		switch (command) {
			case '>':
				shiftUp(&cells[initialCell.number], &initialCell.number);
				break;
			case '<':
				shiftDown(&cells[initialCell.number]);
				break;
			case '+':
				increment(&cells[initialCell.number]);
				break;
			case '-':
				decrement(&cells[initialCell.number]);
				break;
			case '.':
				printf("%d\n", printCellValue(&cells[initialCell.number]));
				break;
			case ',':
				inputValueForCell(&cells[initialCell.number]);
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
