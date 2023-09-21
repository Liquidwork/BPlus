import java.util.ArrayList;
import java.util.List;

public class BPlusLeaf extends Node{
    private List<Integer> nums;
    private BPlusLeaf nextLeaf;

    protected BPlusLeaf(int a, BPlusNode parent){
        super(parent);
        this.nums = new ArrayList<>(3);
        this.nums.add(a);
        this.nextLeaf = null;
    }

    protected BPlusLeaf(int a, BPlusNode parent, BPlusLeaf next){
        this(a, parent);
        this.nextLeaf = next;
    }

    protected BPlusLeaf(List<Integer> nums, BPlusNode parent, BPlusLeaf next){
        super(parent);
        this.nums = nums;
        this.nextLeaf = next;
    }

    /**
     * Insert a value into this leaf node
     * @param a the value to be inserted
     */
    protected void insert(int a){
        int i;
        for (i = 0; i < nums.size(); i++){
            if (nums.get(i) >= a) break;
        }
        nums.add(i, a); // append to the end

        if (nums.size() >= 3){
            int index = nums.get(1);
            BPlusLeaf newLeaf = new BPlusLeaf(nums, parent, this.nextLeaf); // [0,1,2] --> [0], [1,2], index = 1
            List<Integer> newNums = new ArrayList<>(3);
            newNums.add(newLeaf.nums.remove(0));
            this.nums = newNums;
            this.nextLeaf = newLeaf;
            if (parent == null){ // This node is root
                List<Node> children = new ArrayList<>(4);
                children.add(this);
                children.add(newLeaf);
                this.parent = new BPlusNode(children, index, null);
                newLeaf.parent = this.parent;
            } else{
                this.parent.addChild(newLeaf, index);
            }
        }
    }

    @Override
    protected boolean find(int a) {
        return nums.contains(a);
    }

    @Override
    protected void print() {
        for (int num : nums){
            System.out.print(num);
            System.out.print(' ');
        }
        if (this.nextLeaf != null) this.nextLeaf.print();
    }
}
