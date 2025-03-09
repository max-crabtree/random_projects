#include "binary_tree.h"
#include "node.h"

struct Node* walk_down(int val, struct Node n) {
	if (val < n.data) { return walk_down(val, n->left); };
	if (val > n.data) { return walk_down(val, n->right); };
	if (val == n.data) { return n; };
}


void add_values_to_btree(struct BTree* t, int* values) {
	while (*values) {
		struct Node* node_to_fill = walk_down(*values, t->root);
		node_to_fill->data = *values;
		*values++;
	}
}
