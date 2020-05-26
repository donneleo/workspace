

/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Eoin Donnelly Maguire
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;             // root of BST

	/**
	 * Private node class.
	 */
	private class Node {
		private Key key;           // sorted by key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees
		private int N;             // number of nodes in subtree

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	//-------- little things to help me
	private int numberOfNodes = 0;

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// return number of key-value pairs in BST
	public int size() { return size(root); }

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null) return 0;
		else return x.N;
	}

	/**
	 *  Search BST for given key.
	 *  Does there exist a key-value pair with given key?
	 *
	 *  @param key the search key
	 *  @return true if key is found and false otherwise
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 *  Search BST for given key.
	 *  What is the value associated with given key?
	 *
	 *  @param key the search key
	 *  @return value associated with the given key if found, or null if no such key exists.
	 */
	public Value get(Key key) { return get(root, key); }

	private Value get(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	/**
	 *  Insert key-value pair into BST.
	 *  If key already exists, update with new value.
	 *
	 *  @param key the key to insert
	 *  @param val the value associated with key
	 */
	public void put(Key key, Value val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
		numberOfNodes++;
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else {
			x.val   = val;
		}
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	/**
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: Theta(N+M), where N is the number of links on the left and M is the number of
	 * links on the right. The function is called recursively until neither side has any further node to link to.
	 *
	 * @return the number of links from the root to the deepest leaf.
	 *
	 * Example 1: for an empty tree this should return -1.
	 * Example 2: for a tree with only one node it should return 0.
	 * Example 3: for the following tree it should return 2.
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 */
	public int height() {

		Node tempNode = root;
		return(height(tempNode));
	}

	/**
	 * Median key.
	 * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
	 * is the element at position (N+1)/2 (where "/" here is integer division)
	 *
	 * @return the median key, or null if the tree is empty.
	 */
	public Key median() {
		if (isEmpty()) return null;
		//TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree.
		
		if(numberOfNodes == 1) {
			return root.key;
		}

		double median = (size(root) - 1)/ 2;
		int medianKey = (int)(median);

		return select(medianKey);
	}


	/**
	 * Print all keys of the tree in a sequence, in-order.
	 * That is, for each node, the keys in the left subtree should appear before the key in the node.
	 * Also, for each node, the keys in the right subtree should appear before the key in the node.
	 * For each subtree, its keys should appear within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()"
	 * Example 2: Tree containing only "A" -- output: "(()A())"
	 * Example 3: Tree:
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder() {
		if (isEmpty()) return "()";

		Node tempNode = root;		
		return "(" +inOrderString(tempNode)+")";

	}

	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for details.
	 *
	 * @return a multi-line string with the pretty ascii picture of the tree.
	 */
	public String prettyPrintKeys() {
		//TODO fill in the correct implementation.
		if(isEmpty()) return "-null\n";

		Node tempNode = root;
		String x = "";
		return prettyPrint(tempNode, x);
	}

	/**
	 * Deteles a key from a tree (if the key is in the tree).
	 * Note that this method works symmetrically from the Hibbard deletion:
	 * If the node to be deleted has two child nodes, then it needs to be
	 * replaced with its predecessor (not its successor) node.
	 *
	 * @param key the key to delete
	 */
	public void delete(Key key) {
		//TODO fill in the correct implementation.

		root = delete(root, key);

	}

	//---------------HEIGHT METHODS----------

	private int height(Node x) 
	{
		if(x == null) {
			return -1;	
		}

		int heightLeftBranch = height(x.left);
		int heightRightBranch = height(x.right);

		if(heightLeftBranch > heightRightBranch) {
			return ++heightLeftBranch;
		}
		else {
			return ++heightRightBranch;
		}
	}



	//--------------------------- SELECT METHODS -----------
	
	//--------from lecture slides------
	public Key select(int num) {
		if(num < 0 || num >= size()) {
			return null;
		}
		Node medianNode = select(root, num);
		return medianNode.key;
	}	

	private Node select(Node x, int num) { 
		if(x != null) {
			
		int i = size(x.left);
		if(i>num)              return select(x.left, num);
		else if(i == num)      return x;	
		else {
			return select(x.right, num-i-1);
		}
		}
		return null;
	}


	//-----------------DELETE METHODS------------

//-----altered from lecture slides-----

	private Node deleteMax(Node x) 
	{
		if(x.right == null) {
			return x.left;
		}

		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	private Node max(Node x) 
	{
		return x.right;
	}

	private Node delete(Node x, Key key) 
	{
		if(x == null) { 
			return null;
		}

		int cmp = key.compareTo(x.key);
		if(cmp < 0)       x.left = delete(x.left, key);
		else if(cmp > 0)  x.right = delete(x.right, key);
		else {
			if(x.right == null) return x.left;
			if(x.left  == null) return x.right;

			Node newNode = x;
			x = max(newNode.left);
			x.left = (deleteMax(newNode.left));
			x.right = newNode.right;
		}

		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	//-----------------------PRINTING METHODS


	private String prettyPrint(Node x, String prefix) 
	{

		if(x == null) {
			return prefix + "-null\n";
		}	
		else {
			return ((prefix + "-" + x.key) + "\n" + (prettyPrint(x.left, prefix + " |")) + (prettyPrint(x.right, prefix + "  ")));
		}
	}

	String inOrderString(Node x)  
	{
		String left = "";
		String right = "";
		String output = "";

		if(x.left != null) left = inOrderString(x.left);
		if(x.right != null) right = inOrderString(x.right);

		output = "(" + left + ")" + "" + x.key + "" + "(" + right + ")";
		return output;	
	}	

}

