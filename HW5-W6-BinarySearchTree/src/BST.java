/**
 * Student: Nathan Ayele
 * Class: CS302 - Design and Analysis of Algorithms | CaldwellSpring'23
 * Date: March 8th, 2023
 */
import java.util.ArrayList;
import java.util.Scanner;

public class BST<Key extends Comparable<Key>, Value> {
	Node root;

	class Node {
		Key key;
		Value value;
		Node left;
		Node right;

		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "[" + key + ", " + value + "]";
		}

	}

	public Value getValue(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return x.value;
		}
		return null;
	}

	public boolean contains(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return true;
		}
		return false;
	}

	public Key minimum() {
		return minimum(root);
	}

	private Key minimum(Node x) {
		if (x == null) {
			return null;
		}

		if (x.left == null) {
			return x.key;
		}
		return minimum(x.left);
	}

	public Key smallest() {
		Node x = root;

		if (x == null) {
			return null;
		}

		while (x.left != null) {
			x = x.left;
		}
		return x.key;

	}

	public Node deleteMinimum() {
		Node x = root;
		if (x == null) {
			return null;
		}
		Node parent = null;
		while (x.left != null) {
			parent = x;
			x = x.left;
		}
		if (parent == null) {
			root = x.right;
		} else {
			parent.left = x.right;
		}
		return x;
	}

	public Key maximum() {
		return maximum(root);
	}

	private Key maximum(Node x) {
		if (x == null) {
			return null;
		}

		if (x.right == null) {
			return x.key;
		}
		return maximum(x.right);
	}

	public String toStringReverse() {
		return toStringReverse(root);
	}

	public String toStringReverse(Node root) {
		if (root == null)
			return "";

		return toStringReverse(root.right) + " " + root.toString() + toStringReverse(root.left);
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null) {
			return 0;
		}
		return size(x.left) + size(x.right) + 1;
	}

	public int height() {
		return height(root);
	}

	private int height(Node x) {
		if (x == null) {
			return -1;
		}
		return Math.max(height(x.left), height(x.right)) + 1;
	}

	public int countValue(Value value) {
		return countValue(root, value);
	}

	private int countValue(Node x, Value value) {
		if (x == null) {
			return 0;
		}

		int count = 0;
		if (x.value.equals(value)) {
			count++;
		}
		count += countValue(x.left, value);
		count += countValue(x.right, value);
		return count;
	}

	public Key getParentKey(Key target) {
		Node x = root;
		if (x == null || x.key.equals(target)) {
			return null;
		}

		if (x.left != null && x.left.key.equals(target)) {
			return x.key;
		}

		if (x.right != null && x.right.key.equals(target)) {
			return x.key;
		}

		int cmp = target.compareTo(x.key);
		if (cmp < 0) {
			return getParentKey(x.left.key);
		} else if (cmp > 0) {
			return getParentKey(x.right.key);
		}
		return null;
	}

	public void balanceMe() {
		ArrayList<Node> nodes = new ArrayList<>();
		addNodes(root, nodes);
		root = balance(nodes, 0, nodes.size() - 1);
	}

	private void addNodes(Node x, ArrayList nodes) {
		if (x != null) {
			nodes.add(x);
			addNodes(x.left, nodes);
			addNodes(x.right, nodes);
		}
	}

	private BST<Key, Value>.Node balance(ArrayList nodes, int start, int end) {
		if (start > end) {
			return null;
		}

		int mid = (start + end) / 2;
		Node node = (BST<Key, Value>.Node) nodes.get(mid);
		node.left = balance(nodes, start, mid - 1);
		node.right = balance(nodes, mid + 1, end);
		return node;

	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
	}

	private Node put(Node x, Key key, Value value) {
		if (x == null)
			return new Node(key, value);

		int cmp = key.compareTo(x.key);

		if (cmp < 0)
			x.left = put(x.left, key, value);
		else if (cmp > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;
		return x;
	}

	public String toString() {
		return toString(root);
	}

	public String toString(Node root) {
		if (root == null)
			return "";

		return toString(root.left) + " " + root.toString() + toString(root.right);
	}

	public Value get(Key key) {
		return get(root, key);
	}

	public Value get(Node root, Key key) {
		if (root == null)
			return null;

		if (key.compareTo(root.key) < 0)
			return get(root.left, key);
		else if (key.compareTo(root.key) > 0)
			return get(root.right, key);
		else
			return root.value;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		BST<Character, Integer> bst = new BST();

		while (true) {
			System.out.println("1. put");
			System.out.println("2. get");
			System.out.println("3. put multiple");
			System.out.println("9. toString");

			System.out.println("71. getValue");
			System.out.println("72. contains");
			System.out.println("73. minimum");
			System.out.println("74. smallest");
			System.out.println("75. deleteMinimum");
			System.out.println("76. maximum");
			System.out.println("77. toStringReverse");
			System.out.println("78. size");
			System.out.println("79. height");
			System.out.println("80. countValue");
			System.out.println("81. getParentKey");
			System.out.println("82. balanceMe");

			switch (in.nextLine()) {
				case "1":
					System.out.println("Please enter key and value");
					bst.put(in.nextLine().charAt(0), Integer.valueOf(in.nextLine()));
					break;
				case "2":
					System.out.println("Please enter key");
					System.out.println("Value: " + bst.get(in.nextLine().charAt(0)));
					break;
				case "3":
					System.out.println("Please enter multiple keys");
					String keys = in.nextLine();
					int i = 0;
					for (char c : keys.toCharArray())
						bst.put(c, i++);
					break;
				case "71":
					System.out.println("Please enter key");
					System.out.println("Value: " + bst.getValue(in.nextLine().charAt(0)));
					break;
				case "72":
					System.out.println("Please enter key");
					System.out.println("Value Found Status: " + bst.contains(in.nextLine().charAt(0)));
					break;
				case "73":
					System.out.println("Minimum: " + bst.minimum());
					break;
				case "74":
					System.out.println("Smallest: " + bst.smallest());
					break;
				case "75":
					System.out.println("Deleted Value: " + bst.deleteMinimum());
					break;
				case "76":
					System.out.println("Maximum: " + bst.maximum());
					break;
				case "77":
					System.out.println(bst.toStringReverse());
					break;
				case "78":
					System.out.println("Size of your tree: " + bst.size());
					break;
				case "79":
					System.out.println("Height of your tree: " + bst.height());
					break;
				case "80":
					System.out.println("Please enter value");
					System.out.println("Count of Value: " + bst.countValue(Integer.valueOf(in.nextLine())));//check this
					break;
				case "81":
					System.out.println("Please enter key");
					System.out.println("Parent Key: " + bst.getParentKey(in.nextLine().charAt(0)));
					break;
				case "82":
					bst.balanceMe();
					System.out.println("Your tree has been balanced.");	
					System.out.println(bst);
					break;
				case "9":
					System.out.println(bst);
					break;
				case "99":
					break;
			}
		}
	}
}