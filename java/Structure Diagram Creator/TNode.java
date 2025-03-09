public class TNode {
    private Object data;
    private TNode child;
    private TNode sibling;

    public TNode(Object data) { this.data = data; }

    public Object getData() { return data; }

    public void setData(Object data) { this.data = data; }

    public TNode getChild() { return child; }

    public void setChild(TNode child) { this.child = child; }

    public TNode getSibling() { return sibling; }

    public void setSibling(TNode sibling) { this.sibling = sibling; }

    public int getNumberOfSiblings() {
        TNode currentTNode = this;
        int i = 0;
        
        while (currentTNode != null) {
            i++;
            currentTNode = currentTNode.getSibling();
        }

        return i;
    }

    @Override
    public String toString() { return data.toString(); }
}
