import java.util.ArrayList;
import java.util.List;

public class BPlusNode extends Node{
    private List<Node> children;
    private List<Integer> index;
    protected BPlusNode parent;

    protected BPlusNode(List<Node> children, int index, BPlusNode parent){
        super(parent);
        this.children = children;
        this.index = new ArrayList<>(3);
        this.index.add(index);
    }

    protected void addChild(Node child, int index){
        int i;
        for (i = 0; i < this.index.size(); i++){
            if (this.index.get(i) >= index) break;
        }
        this.index.add(i, index);
        this.children.add(i + 1, child);

        if (this.index.size() >= 3){ // adjust this index and parent index
            int parentIndex = this.index.remove(1);
            BPlusNode sibling = new BPlusNode(this.children.subList(0, 2), this.index.remove(0), this.parent);
            this.children = this.children.subList(2, 4);

            if (parent == null){ // This node is root, add new node as parent
                List<Node> parentsChildren = new ArrayList<>(4);
                parentsChildren.add(sibling);
                parentsChildren.add(this);
                this.parent = new BPlusNode(parentsChildren, parentIndex, null); // new root
                sibling.parent = this.parent;
            } else{ // add to current parent
                parent.addChild(sibling, parentIndex);
            }
            
        }
    }

    protected boolean find(int a){
        for (int i = 0; i < index.size(); i++){
            if (index.get(i) > a){
                return children.get(i).find(a);
            }
        }
        return children.get(index.size()).find(a);
    }

    @Override
    protected void insert(int a) {
        int i;
        for (i = 0; i < this.index.size(); i++){
            if (this.index.get(i) >= a) break;
        }
        this.children.get(i).insert(a);
    }

    @Override
    protected void print() {
        children.get(0).print();
    }

    
}