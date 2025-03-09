#include <stdio.h>
#include <string.h>
#include <stdint.h>

#define MAX_NAME_SIZE 20

int main() {
	char file_name[MAX_NAME_SIZE];
	printf("Blank .toy File Generator\n");
	printf("Type the name of the file here: ");
	scanf("%s", file_name);
	strcat(file_name, ".toy");
	FILE *new = fopen(file_name, "w");
	for (int i = 0x00; i <= 0xFF; i++) {
		fprintf(new, "%02X:\n", i);
	}
	fclose(new);
	return 0;
}