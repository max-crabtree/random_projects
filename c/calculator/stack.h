#ifndef STACK_H
#define STACK_H

#include "node.h"

void push(int x, node* root);

int pop(node* root);

void print_stack(node* root);

#endif