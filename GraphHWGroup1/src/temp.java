import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class temp {
    public static List<TreeNode> findLongestPath(TreeNode node) {
        if (node == null) {
            List<TreeNode> path = new ArrayList<>();
            return path;
        }

        List<TreeNode> leftPath = findLongestPath(node.left);
        List<TreeNode> rightPath = findLongestPath(node.right);

        int leftLength = leftPath.size();
        int rightLength = rightPath.size();

        if (leftLength >= rightLength) {
            leftPath.add(node);
            return leftPath;
        } else {
            rightPath.add(node);
            return rightPath;
        }
    }

    public static int longestPath(TreeNode root) {
        List<TreeNode> path = findLongestPath(root);
        return path.size() - 1;
    }

    public static void main(String[] args) {
        // Construct the binary tree from the example
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(6);
        root.right.right.left = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
        root.right.left.left.right = new TreeNode(9);

        int longestPathLength = longestPath(root);
        System.out.println("Longest path length: " + longestPathLength);

        List<TreeNode> longestPath = findLongestPath(root);
        System.out.print("Longest path: ");
        for (int i = longestPath.size() - 1; i >= 0; i--) {
            System.out.print(longestPath.get(i).val + " ");
        }
    }
}