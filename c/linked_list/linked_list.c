#include <stdio.h>

#include "node.h"
#include "linked_list.h"

void insert(int new_data, struct Node** p_root) {
	struct Node* temp_node = *p_root;

	if (!(*p_root)) {
		printf("List empty; inserting at root\n");
		(*p_root)->data = &new_data;
		return;
	}

	printf("test\n");

	while(temp_node->next) {
		printf("Traversing...\n");
		temp_node = temp_node->next;
	}

	temp_node->next->data = new_data;
}

void print_list(struct Node* root) {
	struct Node* temp_node = root;

	if (!root) {
		printf("List empty\n");
		return;
	}

	int count = 1;

	while (temp_node->next) {
		printf("Node %d = %d -> ", count, temp_node->data);
		count++;
		temp_node = temp_node->next;
	}
}
