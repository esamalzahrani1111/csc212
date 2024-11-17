public class BSTNode <T extends Comparable<T>> {
	public String key;
	public fNode<T> data;
	public BSTNode<T> left, right;
	
	/** Creates a new instance of BSTNode */
	public BSTNode(String k, T val) {
		key = k;
		data = new fNode<T>(val);
		left = right = null;
	}
	
	public BSTNode(String k, T val, BSTNode<T> l, BSTNode<T> r) {
		key = k;
		data = new fNode<T>(val);
		left = l;
		right = r;
	}
}
