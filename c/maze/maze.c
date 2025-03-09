#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "maze.h"
#include "cell.h"

//void print_maze(maze m) {
//	for (int i = 0; i <= m.height; i++) {
//		for (int j = 0; j <= m.width; j++) {
//			if (i % 2 == 0 || (i == m.height && !(m.height % 2 == 0))) {
//				printf("---");
//			} else {
//				printf("|%c|", CELL);
//			}
//		}
//		printf("\n");
//	}
//}
//

void perform_backtracking(maze m) {
	char* current_cell = m.cells; // at 0,0
	srand(time(NULL));

	m.visited = 1;


	while (m.visited < m.width * m.height) {

		int rand_value = rand() / (RAND_MAX/3);






		m.visited++;

	}
}
