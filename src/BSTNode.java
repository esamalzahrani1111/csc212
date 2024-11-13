// Node class for storing data: docId and frequency of term
class DocFrequencyList<T extends Comparable<T>> {
	public T docId;
	public int frequency;
	public DocFrequencyList<T> next;
	
	public DocFrequencyList(T docId) {
        this.docId = docId;
        this.frequency = 1; // Initialize frequency to 1 when first encountered
        this.next = null;
    }
}

public class BSTNode <T extends Comparable<T>> {
	public String key;
	public DocFrequencyList<T> data;
	public BSTNode<T> left, right;
	
	/** Creates a new instance of BSTNode */
	public BSTNode(String k, T data) {
		key = k;
		this.data = new DocFrequencyList<T>(data);
		left = right = null;
	}
	
	public BSTNode(String k, T data, BSTNode<T> l, BSTNode<T> r) {
		key = k;
		this.data = new DocFrequencyList<T>(data);
		left = l;
		right = r;
	}
}
