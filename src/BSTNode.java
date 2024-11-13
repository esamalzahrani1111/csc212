public class BSTNode <T extends Comparable<T>> {
	public String key;
	public Node<T> data;
	public BSTNode<T> left, right;
	
	/** Creates a new instance of BSTNode */
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
