public class BSTNode <T extends Comparable<T>> {
	public String key; // the term to be stored
	public Node<T> data; // Head of linked list of doc IDs and frequencies
	public BSTNode<T> left, right;
	
	/* Creates a new instance of BSTNode */
	public BSTNode(String k, T data) {
		key = k;
		this.data = new Node<T>(data);
		left = right = null;
	}
	
	public BSTNode(String k, T data, BSTNode<T> l, BSTNode<T> r) {
		key = k;
		this.data = new Node<T>(data);
		left = l;
		right = r;
	}
}
