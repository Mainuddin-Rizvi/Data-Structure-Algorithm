package Trees.binary_tree;

public class HeightDepthToK {

    static class Node{
        int data;
        Node left, right;
        public Node(int value){
            this.data = value;
            this.left = this.right = null;
        }
    }

    int findDepth(Node root, int k){
        if(root == null){
            return -1;
        }
        int depth = -1;

        if((root.data == k)
                || (depth = findDepth(root.left, k)) >= 0
                || (depth = findDepth(root.right,k)) >=0)
                return depth+1;
        return depth;
    }

    int findHeightUtil(Node root,int k, int[] h){
        if(root == null)
            return -1;
        int leftHeight = findHeightUtil(root.left,k,h);
        int rightHeight = findHeightUtil(root.right,k,h);


        int ans = Math.max(leftHeight,rightHeight)+1;

        if(root.data == k){
            h[0] = ans;
        }
        return ans;

    }

    int findHeight(Node root, int k){
        int h[] = {-1};
        findHeightUtil(root,k,h);
        return h[0];

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

        int k = 25;

        HeightDepthToK tree = new HeightDepthToK();
        System.out.println("Depth: " + tree.findDepth(root, k));
        System.out.println("Height: " + tree.findHeight(root, k));
    }
}
