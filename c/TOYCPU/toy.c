#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

#include "toy.h"

/* Improvements:
 * Error handling, easier to write in file, etc.
 * More info in diag
 */

uint16_t memory[256];

uint16_t R[16];

uint8_t PC;

int load_mem(int current_mem, int command) {
	PC = current_mem;
	memory[PC] = command;
	printf("[LOAD] 0x%04X -> M[%02X]\n", memory[PC], PC);
	if (memory[PC] == 0x0000) {
		printf("[OK] LOAD SUCCESS\n");
		return CPU_START;
	}
	return 0;
}

int run_cpu() {

	PC = 0x10; //TOY starts at memory location 0x10
	R[0] = 0;
	_Bool halt = 0;

	while (!halt) {

		uint16_t OP = (memory[PC] & 0xF000) >> 12;
		uint16_t DEST = (memory[PC] & 0x0F00) >> 8;
		uint16_t REG1 = (memory[PC] & 0x00F0) >> 4;
		uint16_t REG2 = (memory[PC] & 0x000F);
		uint16_t ADDR = (memory[PC] & 0x00FF);

		switch (OP) {
			case 0x0:
				printf("[OK] HALTING...\n");
				halt = 1;
				break;
			case 0x1:
				R[DEST] = R[REG1] + R[REG2];
				printf("[OK] R[%X] (%d) + R[%X] (%d) -> R[%X] (%d)\n", REG1, R[REG1], REG2, R[REG2], DEST, R[DEST]); 
				PC = PC + 0x01;
				break;
			case 0x2:
				R[DEST] = R[REG1] - R[REG2];
				printf("[OK] R[%X] (%d) - R[%X] (%d) -> R[%X] (%d)\n", REG1, R[REG1], REG2, R[REG2], DEST, R[DEST]); 
				PC = PC + 0x01;
				break;
			case 0x3:
				R[DEST] = R[REG1] & R[REG2];
				printf("[OK] R[%X] (%d) & R[%X] (%d) -> R[%X] (%d)\n", REG1, R[REG1], REG2, R[REG2], DEST, R[DEST]); 
				PC = PC + 0x01;
				break;
			case 0x4:
				R[DEST] = R[REG1] ^ R[REG2];
				printf("[OK] R[%X] (%d) ^ R[%X] (%d) -> R[%X] (%d)\n", REG1, R[REG1], REG2, R[REG2], DEST, R[DEST]); 
				PC = PC + 0x01;
				break;
			case 0x5:
				R[DEST] = R[REG1] << R[REG2];
				printf("[OK] R[%X] (%d) << R[%X] (%d) -> R[%X] (%d)\n", REG1, R[REG1], REG2, R[REG2], DEST, R[DEST]); 
				PC = PC + 0x01;
				break;
			case 0x6:
				R[DEST] = R[REG1] >> R[REG2];
				printf("[OK] R[%X] (%d) >> R[%X] (%d) -> R[%X] (%d)\n", REG1, R[REG1], REG2, R[REG2], DEST, R[DEST]); 
				PC = PC + 0x01;
				break;
			case 0x7:
				R[DEST] = ADDR;
				printf("[OK] 0x%02X -> R[%X]\n", ADDR, DEST);
				PC = PC + 0x01;
				break;
			case 0x8:
				R[DEST] = memory[ADDR];
				printf("[OK] M[%02X] -> R[%X]\n", ADDR, DEST);
				if (ADDR == 0xFF) {
					char input[4];
					printf("[OK] VALUE FOR R[%X]: 0x", DEST);
					scanf("%s", input);
					if (atoi(input) > atoi("FFFF")) {
						printf("[ERR] INVALID INPUT\n");
						return CPU_FAIL;
					}
					R[DEST] = strtol(input, NULL, 16);
					printf("[OK] 0x%04X -> R[%X]\n", memory[ADDR], DEST);
				}
				PC = PC + 0x01;
				break;
			case 0x9:
				memory[ADDR] = R[DEST];
				printf("[OK] R[%X] (%d) -> M[%02X]\n", DEST, R[DEST], ADDR);
				if (ADDR == 0xFF) {
					printf("[OK] Output of R[%X]: %04X\n", DEST, memory[ADDR]);
				}
				PC = PC + 0x01;
				break;
			case 0xA:
				R[DEST] = memory[R[REG2]];
				printf("[OK] M[R[%X]] (%d) -> R[%X]\n", REG2, memory[R[REG2]], DEST);
				PC = PC + 0x01;
				break;
			case 0xB:
				memory[R[REG2]] = R[DEST];
				printf("[OK] R[%X] (%d) -> M[R[%X]]\n", DEST, R[DEST], REG2);
				PC = PC + 0x01;
				break;
			case 0xC:
				if (R[DEST] == 0x0000) {
					PC = ADDR;
					printf("[OK] R[%X] = 0 | PC -> 0x%02X\n", DEST, ADDR);
				}
				else {
					printf("[OK] R[%X] != 0 CONTINUING...\n", DEST);
					PC = PC + 0x01;
				}
				break;
			case 0xD:
				if (R[DEST] > 0x0000) {
					PC = ADDR;
					printf("[OK] R[%X] > 0 | PC -> 0x%02X\n", DEST, ADDR);
				}
				else {
					printf("[OK] R[%X] !> 0 CONTINUING...\n", DEST);
					PC = PC + 0x01;
				}
				break;
			case 0xE:
				PC = R[DEST];
				printf("[OK] PC -> R[%X] (%d)\n", DEST, R[DEST]);
				break;
			case 0xF:
				R[DEST] = PC;
				PC = ADDR;
				printf("[OK] PC (%d) -> R[%X] ; 0x%02X -> PC\n", PC, DEST, ADDR);
				break;
			default:
				printf("[ERR] UNKNOWN OP-CODE\n");
				return CPU_FAIL;
		}
	}
	return 0;
}
