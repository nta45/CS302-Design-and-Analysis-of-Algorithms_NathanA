public class BST <Key extends Comparable<Key>, Value> {
    Node root;

    class Node{
        Key key;
        Value value;
        Node left;
        Node right;

        public Node (Key key, Value value){
            this.key = key;
            this.value = value;
        }


    }

    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value){
        if (x == null)
            return new Node(key, value);

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = put(root.left, key, value);
        else if (cmp < 0)
            x.right = put(root.right, key, value);
        else
            x.value = value;
        
        return x;
    }
}
