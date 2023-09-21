/**
 * Able to find certain element inside the data structure
 */
public abstract class Node {
    protected BPlusNode parent;

    protected Node(BPlusNode parent){
        this.parent = parent;
    }

    protected abstract boolean find(int a);
    protected abstract void insert(int a);
    protected abstract void print();
}
