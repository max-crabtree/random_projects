#include <stdio.h>

#include "maze.h"
#include "cell.h"

int main() {
	printf("Hello Square! %c\n", CELL);

	char zero_init[MAZE_HEIGHT * MAZE_WIDTH];
	for (int i = 0; i < MAZE_WIDTH * MAZE_HEIGHT; i++) {
		zero_init[i] = '0';
	}

	maze m = { .height = MAZE_HEIGHT, .width = MAZE_WIDTH, .cells = zero_init, .visited = 0 };

	perform_backtracking(m);

	return 0;
}
