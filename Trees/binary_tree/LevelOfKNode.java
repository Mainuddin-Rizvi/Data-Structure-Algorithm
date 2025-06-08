package Trees.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

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

    boolean searchNode(Node root, int key){
        if(root==null){
            return false;
        }
        if(key==root.data){
            return true;
        }
        boolean left = searchNode(root.left,key);
        if (left)
            return true;
        boolean right = searchNode(root.right,key);
        return right;
    }

    int findParentOfNode(Node root, int target, int parent){
        if(root == null){
            return -1;
        }
        if(target == root.data){
            return parent;
        }
        int left = findParentOfNode(root.left, target, root.data);
        if(left != -1){
            return left;
        }
        return findParentOfNode(root.right,target,root.data);
    }

    static Node insertNode(Node root, int data){
        if(root==null){
            root =new Node(data);
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if (curr.left != null){
                queue.add(curr.left);
            }else{
                curr.left = new Node(data);
                return root;
            }

            if(curr.right!= null){
                queue.add(curr.right);
            }else{
                curr.right = new Node(data);
                return root;
            }
        }
        return root;

    }
    static void deletDeepest(Node root, Node dNode){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Node curr;
        while(!queue.isEmpty()){
            curr = queue.poll();

            if(curr == dNode){
                curr = null;
                dNode = null;
                return;
            }

            if(curr.right!= null){
                if(curr.right == dNode){
                    curr.right = null;
                    dNode = null;
                    return;
                }
                queue.add(curr.right);
            }

            if (curr.left != null){
                if (curr.left == dNode){
                    curr.left = null;
                    dNode = null;
                    return;
                }
                queue.add(curr.left);
            }
        }
    }

    // Function to delete the node with the given key
    static Node deletion(Node root, int key) {

        if (root == null)
            return null;

        // If the tree has only one node
        if (root.left == null && root.right == null) {

            // If the root node is the key,
            // delete it
            if (root.data == key)
                return null;
            else
                return root;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        Node curr = null;
        Node keyNode = null;

        // Level order traversal to find the
        // deepest node and the key node
        while (!q.isEmpty()) {
            curr = q.poll();

            // If current node is the key node
            if (curr.data == key)
                keyNode = curr;

            if (curr.left != null)
                q.add(curr.left);

            if (curr.right != null)
                q.add(curr.right);
        }

        // If key node is found, replace its
        // data with the deepest node
        if (keyNode != null) {

            // Store the data of the
            // deepest node
            int x = curr.data;

            // Replace key node data with
            // deepest node's data
            keyNode.data = x;

            // Delete the deepest node
            deletDeepest(root, curr);
        }
        return root;
    }
    static void inorder(Node curr) {
        if (curr == null)
            return;
        inorder(curr.left);
        System.out.print(curr.data + " ");
        inorder(curr.right);
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

        int key = 15;

        LevelOfKNode tree = new LevelOfKNode();
//        System.out.println("Level: " + tree.findLevelOfK(root, target,1));
//        System.out.println("Level: " + tree.searchNode(root,21));
//        System.out.println("Level: " + tree.findParentOfNode(root,45,-1));
//        root = insertNode(root, key);
        root = deletion(root, key);
        inorder(root);




    }

}
