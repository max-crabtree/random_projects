#ifndef TOY_H_
#define TOY_H_

#define HALT 66
#define CPU_START 99
#define CPU_FAIL 11

int run_cpu();
int load_mem(int current_mem, int command);

#endif
