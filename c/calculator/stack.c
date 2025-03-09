#include <stdio.h>

#include "node.h"

void push(int new_data, node* x) {
    
}

int pop(node* root) {
    root->next = root;
    
    return root->data;
}

void print_stack(node* root) {
    node* temp_node = root;

    printf("Stack is:\n");

    while (temp_node) {
        printf("%d -> ", temp_node->data);
        temp_node = temp_node->next;
    }
}