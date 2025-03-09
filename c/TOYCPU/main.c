#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "toy.h"

#define MAX_NAME_SIZE 20

void parse_doc(FILE *file) {
	int current;
	char line[9];
	int memory_location;
	int current_command;
	int load_status;
	int cpu_status;
	char *token;
	while ((current = fgetc(file)) != EOF) {
		char c = (char)current;
		strncat(line, &c, 1);
		if (current == ':') {
			token = strtok(line, ":");
			memory_location = strtol(token, NULL, 16);
			strcpy(line, "");
		}
		current_command = strtol(line, NULL, 16);
		if (current == '\n') {
			//fix this
			if (!(current_command == ' ')) {
				load_status = load_mem(memory_location, current_command);
			}
			strcpy(line, "");
		}
	}
	if (load_status == CPU_START) {
		cpu_status = run_cpu();
	}
	if (cpu_status == CPU_FAIL) {
		printf("[ERR] PROGRAM TERMINATED EARLY\n");
	}
	else {
		printf("[OK] PROGRAM COMPLETED SUCCESSFULLY\n");
	}
}

int main() {
	char file_name[MAX_NAME_SIZE];

	printf("TOY CPU Emulator\n");
	printf("Type the name of the file to be run: ");
	scanf("%s", file_name);
	strcat(file_name, ".toy");
	FILE *opened = fopen(file_name, "r");
	if (!opened) {
		perror("");
	}
	parse_doc(opened);
	fclose(opened);
	return 0;
}
