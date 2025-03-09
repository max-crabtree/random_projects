#ifndef MAZE_H
#define MAZE_H

#define MAZE_WIDTH  30
#define MAZE_HEIGHT 15

typedef struct Maze {
	int width;
	int height;
	char* cells;
	int visited;
} maze;

void print_maze(maze m);

void perform_backtracking(maze m);

#endif
