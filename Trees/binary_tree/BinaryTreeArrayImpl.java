package Trees.binary_tree;

public class BinaryTreeArrayImpl {

    static class Array_impl{
        static int root = 0;
        static String[] str = new String[10];

        public void Root(String key) {
            str[0] = key;
        }

        void leftSet(String key, int root){
            int t = (2 * root) + 1;
            if(str[root] == null){
                System.out.println("Can't set child at %d, no parent found\n");
            }else{
                str[t] = key;
            }
        }
        void rightSet(String key, int root){
            int t = (2*root)+2;
            if(str[root] == null){
                System.out.println("Can't set child at %d, no parent found\n");
            }else{
                str[t] = key;
            }
        }
        public void print_Tree()
        {

            for (int i = 0; i < 10; i++) {
                if (str[i] != null)
                    System.out.print(str[i]);
                else
                    System.out.print("-");
            }
        }



    }

    public static void main(String[] args) {
        Array_impl obj = new Array_impl();

        // Setting root node
        obj.Root("A");
        obj.leftSet("B", 0);
        obj.rightSet("C", 0);
        obj.leftSet("D", 1);
        obj.rightSet("E", 1);
        obj.rightSet("F", 2);
        obj.print_Tree();

    }
}
