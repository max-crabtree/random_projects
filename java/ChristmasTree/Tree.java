public class Tree {
    int height;
    
    public Tree(int height) {
        this.height = height;
    }

    public void generateTree() {
        StringBuffer tree = new StringBuffer();

        for (int i = 1; i < height; i +=2) {
            for (int j = 0; j < (height - i) / 2; j++) {
                tree.append(" ");
            }
            for (int k = 0; k < i+2; k++) {
                tree.append("*");
            }
            tree.append("\n");
        }

        System.out.println(tree);
    }
}
