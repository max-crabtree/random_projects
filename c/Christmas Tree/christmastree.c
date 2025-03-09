#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
	int height;
	char row[height];
	char blank[2 * height];
	printf("Christmas Tree Generator\n");
	printf("Type a number and the height of the tree will be set: ");
	scanf("%d", &height);
	for (int i = 0; i < height; i++) {
		blank = {' '};
		row[i] = '*';
		//strcat(row, row * i);
		printf("%s%s", blank, row);
		printf("\n");
	}
	return 0;
}
