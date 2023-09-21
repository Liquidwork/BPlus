public class BPlusTree{
    private Node root;

    public BPlusTree(){
        root = null;
    }

    public boolean find(int a){
        if (root == null) return false;
        return root.find(a);
    }

    public void insert(int a){
        if (root == null){
            this.root = new BPlusLeaf(a, null);
        } else{
            root.insert(a);
            if (root.parent != null) root = root.parent; // The operation generate a new parent.
        }
    }

    public void print(){
        if (root == null){
            System.out.println("No element");
        } else{
            root.print();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree();
        tree.insert(5);
        tree.print();
        tree.insert(10);
        tree.print();
        tree.insert(17);
        tree.print();
        tree.insert(20);
        tree.print();
        tree.insert(12);
        tree.print();
        tree.insert(7);
        tree.print();
    }
}