#include <stdio.h>

#include "node.h"
#include "binary_tree.h"

const _Bool DEBUG_PRINT = 1;

int main() {
	printf("Hello, World!\n");
	struct Node node_test = { .data = 1 };
	struct BTree t = { .root = node_test };

	int* values = { 1,2,3,4,5 };

	add_values_to_btree(t, values);
}
