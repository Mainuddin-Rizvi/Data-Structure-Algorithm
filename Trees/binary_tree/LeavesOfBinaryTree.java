package Trees.binary_tree;

public class LeavesOfBinaryTree {

    static class Node{
        public int data;
        public Node left,right;
    }
    static Node newNode(int data){
        Node newNode = new Node();
        newNode.data = data;
        newNode.left= null;
        newNode.right=null;
        return newNode;
    }

    static void printLeafNodes(Node root){
        if (root==null){
            return;
        }
        if(root.left == null && root.right == null){
            System.out.println(root.data+" ");
            return;
        }

        if(root.left!=null){
            printLeafNodes(root.left);
        }
        if (root.right!=null){
            printLeafNodes(root.right);
        }

    }

    public static void main(String[] args) {
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.right.left = newNode(5);
        root.right.right = newNode(8);
        root.right.left.left = newNode(6);
        root.right.left.right = newNode(7);
        root.right.right.left = newNode(9);
        root.right.right.right = newNode(10);

        // Print leaf nodes of the given tree
        printLeafNodes(root);
    }
}
