import java.util.ArrayList;
import java.util.Scanner;

public class BST<Key extends Comparable<Key>, Value>
{
	Node root;

	class Node
	{
		Key key;
		Value value;
		Node left;
		Node right;

		public Node(Key key, Value value)
		{
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString()
		{
			return "[" + key + ", " + value + "]";
		}
	}
	
	class Entry
	{
		Key key;
		Value value;
		
		public Entry(Key key, Value value)
		{
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString()
		{
			return "[" + key + ", " + value + "]";
		}
	}

	public void put(Key key, Value value)
	{
		root = put(root, key, value);
	}

	private Node put(Node x, Key key, Value value)
	{
		if (x == null)
			return new Node(key, value);

		int cmp = key.compareTo(x.key);

		if (cmp < 0)
			x.left = put(x.left, key, value);
		else
			if (cmp > 0)
				x.right = put(x.right, key, value);
			else
				x.value = value;
		return x;
	}

	public String toString()
	{
		return toString(root);
	}

	public String toString(Node root)
	{
		if (root == null)
			return "";

		return toString(root.left) + " " + root.toString() + toString(root.right);
	}

	public Value get(Key key)
	{
		return get(root, key);
	}

	public Value get(Node root, Key key)
	{
		if (root == null)
			return null;

		if (key.compareTo(root.key) < 0)
			return get(root.left, key);
		else
			if (key.compareTo(root.key) > 0)
				return get(root.right, key);
			else
				return root.value;
	}
	
	public ArrayList<Entry> toArrayList()
	{
		ArrayList<Entry> entries = new ArrayList<>();
		
		populateArrayList(root, entries);
		
		return entries;
	}
	
	private void populateArrayList(Node root, ArrayList<Entry> entries)
	{
		if (root == null)
			return;
		
		populateArrayList(root.left, entries);
		entries.add(new Entry(root.key, root.value));
		populateArrayList(root.right, entries);
	}
	
	public Node deleteMinimum()
	{
		Node result = new Node(null, null);
		
		if (root == null)
			return result;
		
		root = deleteMinimum(root, result);
		
		return result;
	}


	private Node deleteMinimum(Node ptr, Node result)
	{
		if (ptr.left == null)
		{
			result.key = ptr.key;
			result.value = ptr.value;
			return ptr.right;
		}
		
		ptr.left =  deleteMinimum(ptr.left, result);
		return ptr;
	}
	
	public void balanceMe()
	{
		ArrayList<Entry> entries = new ArrayList<>();
		fillArrayList(root, entries);		
		
		BST<Key, Value> newTree = new BST<>();
		
		fillNewTree(newTree, entries, 0, entries.size()-1);		
		
		root = newTree.root;
	}
	
	private void fillNewTree(BST<Key, Value> b, ArrayList<Entry> entries, int lo, int hi)
	{
		if (lo > hi)
			return;
		
		int mid = (lo + hi) / 2;
		Entry e = entries.get(mid);
		b.put(e.key, e.value);
		
		fillNewTree(b, entries, lo, mid-1);
		fillNewTree(b, entries, mid+1, hi);
	}
	
	private void fillArrayList(Node ptr, ArrayList<Entry> e)
	{
		if (ptr == null)
			return;
		
		fillArrayList(ptr.left, e);
		
		e.add(new Entry(ptr.key, ptr.value));
		
		fillArrayList(ptr.right, e);	
	}
	
	public Key smallest()
	{
		if (root == null)
			return null;

		Node ptr = root;
		while (ptr.left != null)
			ptr = ptr.left;

		return ptr.key;
	}
	
	public Node maxIterative()
	{
		Node ptr = root;
		while (ptr.right != null)
			ptr = ptr.right;
		return ptr;
	}
	
	public String toStringReverse()
	{
		return reverseOrder(root);
	}

	private String reverseOrder(Node root)
	{
		if (root == null)
			return "";

		return reverseOrder(root.right) + root + reverseOrder(root.left);
	}
	
	public int countValue(Value target)
	{
		return countValue(root, target);
	}

	private int countValue(Node root, Value target)
	{
		if (root == null)
			return 0;

		return countValue(root.left, target) + (root.value.equals(target) ? 1 : 0) + countValue(root.right, target);
	}	

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		BST<Character, Integer> bst = new BST<Character, Integer>();

		while (true)
		{
			System.out.println("1. put");
			System.out.println("2. get");
			System.out.println("3. put multiple");
			System.out.println("4. toArrayList");
			System.out.println("5. deleteMinimum");
			System.out.println("6. balanceMe");

			System.out.println("10. printInReverse");
			System.out.println("11. afterMeKey");
			System.out.println("12. afterkey");
			System.out.println("13. deleteMaximum");
			
			System.out.println("9. toString");

			switch (in.nextLine())
			{
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
			case "4":
				System.out.println(bst.toArrayList());
				break;
			case "5":
				System.out.println(bst.deleteMinimum());
				break;
			case "6":
				bst.balanceMe();
				break;
			case "10":
				System.out.println(bst.printInReverse());
				break;
			case "11":
				System.out.println(bst.afterMe(in.nextLine().charAt(0)));
				break;
			case "12":
				System.out.println(bst.after(in.nextLine().charAt(0)));
				break;
			case "13":
				System.out.println(bst.deleteMaximum());
				break;
			case "9":
				System.out.println(bst);
				break;
			default:
				break;
			}
			in.close();
		}
		
	}
	
	private Object[] toArrayRepresentation()
	{
		int N = (int) Math.pow(2, height() + 1);
		Object[] result = new Object[N];

		toArrayRep(root, 1, result);
		return result;
	}

	private void toArrayRep(Node root, int position, Object[] result)
	{
		if (root == null)
			return;

		result[position] = root.key;
		toArrayRep(root.left, position * 2, result);
		toArrayRep(root.right, position * 2 + 1, result);
	}

	public int height()
	{
		return height(root) - 1;
	}

	public int height(Node root)
	{
		if (root == null)
			return 0;
		else
			return Math.max(height(root.left), height(root.right)) + 1;
	}

	/**
	 * Print the key value pairs in reverse order of the keys 
	 * without explicitly creating any extra space. 
	 * @return key-value pairs in reverse
	 */
	public String printInReverse() {
		return printInReverse(root);
	}

	public String printInReverse(Node root) {
		if (root == null)
			return "";

		return printInReverse(root.right) + " " + root.toString() + printInReverse(root.left);
	}

	/**
	 * Returns the smallest key greater than key (assuming that key is in the tree)
	 *  or null if the key is not in the tree or if no greater key exists
	 * @param key the key input
	 * @return the smallest key greater than the key input
	 */
	public Key afterMe(Key key) {
		Node x = root;
		Node smallestGreatest = null;  
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {  
				smallestGreatest = x;
				x = x.left;
			} else if (cmp > 0) {  
				x = x.right;
			} else {  
				if (x.right != null) {  
					x = x.right;
					while (x.left != null) {
						x = x.left;
					}
					return x.key;
				} else {  
					if(smallestGreatest==null){
						return null;
					}
					else {
						return smallestGreatest.key;
					}
				}
			}
		}
		return null;  
	}

	/**
	 * Returns the smallest key greater than key 
	 * or null if no greater key is in the tree
	 * @param key the key input
	 * @return the smallest key greater than the key input
	 */
	public Key after(Key key){
		Node x = root;
		if (x == null){
			return null;
		}
		Node smallestGreatest = null;
		while(x!=null){
			int cmp = key.compareTo(x.key);
			if(cmp < 0){
				x = x.left;
				smallestGreatest = x;
			}
			else {
				x = x.right;
			}
			
		}
		if (smallestGreatest == null){
			return null;
		} else {
			return smallestGreatest.key;
		}
		
	}

	/**
	 * Deletes the node containing the maximum 
	 * key from the tree and returns a String 
	 * containing the key value pair deleted form the tree
	 * @return deleted Maximum node's [Key, Value]
	 */
	public String deleteMaximum(){
		Node x = root;
		if (x == null){
			return null;
		}
		Node parent = null;
		while(x.right!=null){
			parent = x;
			x = x.right;
		}

		if(parent!=null) {
			parent.right = x.left;
		}
		else {
			root = x.left;
		}
		
		return x.toString();
	}
}