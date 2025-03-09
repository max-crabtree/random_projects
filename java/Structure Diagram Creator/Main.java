public class Main {
    public static void main(String[] args) {
        System.out.println("Structure Diagram Creator/Viewer");

        Box rootBox = new Box("root");

        StructureTree tree = new StructureTree(rootBox);

        TNode rootChildOne = new TNode(new Box("root child1"));
        TNode rootChildOneSiblingOne = new TNode(new Box("root child1 sibling1"));
        TNode rootChildOneSiblingTwo = new TNode(new Box("root child1 sibling2"));
        TNode rootChildTwo = new TNode(new Box("root child2"));

        tree.setData(rootBox);
        tree.setChild(rootChildOne);
        rootChildOne.setSibling(rootChildOneSiblingOne);
        rootChildOneSiblingOne.setSibling(rootChildOneSiblingTwo);
        rootChildOne.setChild(rootChildTwo);

        System.out.println(tree.toString());
        System.out.println(rootChildOne.getNumberOfSiblings());
    }
}
