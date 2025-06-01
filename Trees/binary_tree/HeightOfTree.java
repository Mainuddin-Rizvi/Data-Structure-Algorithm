package Trees.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfTree {
    static class Node {
        int data;
        Node left, right;
        public Node(int value){
            this.data = value;
            this.left = null;
            this.right = null;
        }
    }

    int heightOrderRec(Node root){
        if (root == null){
            return -1;
        }
        int lHeight = heightOrderRec(root.left);
        int rHeight = heightOrderRec(root.right);

        return Math.max(lHeight,rHeight)+1;
    }
    int heightOrderIter(Node root){
        if (root == null) return 0;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int depth =  0;

        while(!q.isEmpty()){
            int levelSize = q.size();
            for(int i =0; i< levelSize; i++){
                Node curr = q.poll();
                if(curr.left != null){
                    q.offer(curr.left);
                }
                if(curr.right!=null){
                    q.offer(curr.right);
                }
            }
            depth++;
        }
        return depth-1;
    }

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(8);
        root.right = new Node(18);
        root.left.left = new Node(5);
        root.left.right = new Node(11);

        HeightOfTree heightOfTree = new HeightOfTree();
        System.out.println(heightOfTree.heightOrderRec(root));
        System.out.println(heightOfTree.heightOrderIter(root));

    }




}
