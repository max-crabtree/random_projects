#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct Stack {
    int x, y, z, t;
};

void pushStack(int num) {
    struct Stack stack;
    stack.x = num;
    stack.x = stack.y;
    stack.y = stack.z;
    stack.z = stack.t;
}

int popStack() {
    struct Stack stack;
    int num; 
    num = stack.x;
    stack.x = stack.y;
    stack.y = stack.z;
    stack.z = stack.t;
    return num;
}

void outputStack() {
    struct Stack stack;
    printf("x: %d\ny: %d\nz: %d\nt: %d\n", stack.x, stack.y, stack.z, stack.t);
}

int main(void) {
    char inp;
    int inpNum;
    const char zero = '0';
    struct Stack stack;
    stack.x = 0;
    stack.y = 0;
    stack.z = 0;
    stack.t = 0;

    for (int i = 0; i < 6; i++) {
        printf("Type a number here: ");
        //fgets(inp, strlen(inp), stdin);
        scanf(" %c", &inp);
        inpNum = atoi(&inp) - atoi(&zero);
        pushStack(inpNum);
    }
    outputStack();
    return 0;
}