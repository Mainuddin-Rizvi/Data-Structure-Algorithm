package Trees.binary_tree;

public class LevelOfKNode {
    static  class Node{
        int data;
        Node left,right;
        public Node(int value){
            this.data = value;
            this.left = null;
            this.right = null;
        }
    }

    int findLevelOfK(Node root,int target,int level){
        if(root == null){
            return -1;
        }
        if(root.data == target){
            return level;
        }
        int leftLevel = findLevelOfK(root.left,target,level+1);
        if(leftLevel!=-1){
            return leftLevel;
        }
        return findLevelOfK(root.right,target,level+1);

    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(10);
        root.right = new Node(15);
        root.left.left = new Node(20);
        root.left.right = new Node(25);
        root.left.right.right = new Node(45);
        root.right.left = new Node(30);
        root.right.right = new Node(35);

        int target = 25;

        LevelOfKNode tree = new LevelOfKNode();
        System.out.println("Level: " + tree.findLevelOfK(root, target,1));
    }

}
