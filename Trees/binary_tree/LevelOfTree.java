package Trees.binary_tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class LevelOfTree {
    static class Node{
        int data;
        Node left, right;

        Node(int value){
            this.data = value;
            left = null;
            right =  null;
        }
    }
    void levelOrderRec(Node root, int level, List<List<Integer>> res){
        if(root == null){
            return;
        }
        if(res.size() <= level){
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.data);
        levelOrderRec(root.left, level + 1, res);
        levelOrderRec(root.right, level + 1, res);

    }
    List<List<Integer>> levelOrder(Node root) {
        // Stores the result level by level
        List<List<Integer>> res = new ArrayList<>();
        levelOrderRec(root, 0, res);
        return res;
    }

    public static List<List<Integer>> levelOrderIter(Node root){
        if (root == null){
            return new ArrayList<>();
        }

        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        q.offer(root);
        int currLevel =0 ;

        while(!q.isEmpty()){
            int len = q.size();
            res.add(new ArrayList<>());

            for(int i=0; i<len;i++){
                Node node = q.poll();
                res.get(currLevel).add(node.data);

                if(node.left!=null){
                    q.offer(node.left);
                }
                if(node.right!=null){
                    q.offer(node.right);
                }
            }
            currLevel++;
        }
        return res;
    }
    public static void main(String[] args) {
        //      5
        //     / \
        //   12   13
        //   /  \    \
        //  7    14    2
        // /  \   /  \  / \
        //17  23 27  3  8  11

        Node root = new Node(5);
        root.left = new Node(12);
        root.right = new Node(13);

        root.left.left = new Node(7);
        root.left.right = new Node(14);

        root.right.right = new Node(2);

        root.left.left.left = new Node(17);
        root.left.left.right = new Node(23);

        root.left.right.left = new Node(27);
        root.left.right.right = new Node(3);

        root.right.right.left = new Node(8);
        root.right.right.right = new Node(11);

//        LevelOfTree tree = new LevelOfTree();
//        List<List<Integer>> res = tree.levelOrder(root);

        List<List<Integer>> res = levelOrderIter(root);


        for (List<Integer> level : res) {
            System.out.print("[");
            for (int i = 0; i < level.size(); i++) {
                System.out.print(level.get(i));
                if (i < level.size() - 1) System.out.print(", ");
            }
            System.out.print("] ");
        }
    }
}
