#include <stdio.h>

#include "node.h"
#include "linked_list.h"

int main() {
	printf("Hello, World!\n");

	struct Node* root = NULL;

	//printf("uninit next is: %d\n", root->next);

	if (!root) { 
		printf("True!\n"); 
	} else {
		printf("False!\n");
	}

	insert(10, &root);
	insert(20, &root);
	insert(30, &root);

	printf("root data is: %d\n", root->data);
	printf("root->next data is: %d\n", root->next->data);

	print_list(root);
}
	
