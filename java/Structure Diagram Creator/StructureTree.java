public class StructureTree {
    private TNode root;

    public StructureTree() {
        root = null;
    }

    public StructureTree(Object o) {
        root = new TNode(o);
    }

    public Object getData() { return root.getData(); }

    public void setData(Object data) { root.setData(data); }

    public TNode getChild() { return root.getChild(); }

    public void setChild(TNode child) { root.setChild(child); }

    public TNode getSibling() { return root.getSibling(); }

    public void setSibling(TNode sibling) { root.setSibling(sibling); }

    @Override
    public String toString() {
        StringBuffer finalString = new StringBuffer(root.toString() + "\n");
        TNode currentTNode = root;
        TNode childTNode;
        
        while (currentTNode != null) {
            childTNode = currentTNode.getChild();
            while (childTNode != null) {
                finalString.append(childTNode + "-----");
                childTNode = childTNode.getSibling();
            }
            finalString.append("|");
            currentTNode = currentTNode.getChild();
        }
        return finalString.toString();
    }
}
