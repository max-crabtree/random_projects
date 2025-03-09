#include <stdio.h>

#include "node.h"
#include "stack.h"

int main() {
    printf("Hello, World!\n");

    node* stack;

    push(10, stack);
    push(20, stack);
    push(30, stack);

    print_stack(stack);

    return 0;
}