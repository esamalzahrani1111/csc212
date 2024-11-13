/*
current inverted index format:
	term1 -> [doc_x, doc_y, ...]
	term2 -> [doc_a, ...]
	...

what to implement: (for ranking)
	term1 -> [doc_x:freq_x, doc_y:freq_y, ...]
	term2 -> [doc_a:freq_a, ...]
	...
 */

// Node class for storing data: docId and frequency of term
class DocFrequency<T extends Comparable<T>> {
	public T docId;
	public int frequency;
	public DocFrequency<T> next;
	
	public DocFrequency(T docId) {
        this.docId = docId;
        this.frequency = 1; // Initialize frequency to 1 when first encountered
        this.next = null;
    }
}

public class BSTNode <T extends Comparable<T>> {
	public String key;
	public DocFrequency<T> data;
	public BSTNode<T> left, right;
	
	/** Creates a new instance of BSTNode */
	public BSTNode(String k, T val) {
		key = k;
		data = new DocFrequency<T>(val);
		left = right = null;
	}
	
	public BSTNode(String k, T val, BSTNode<T> l, BSTNode<T> r) {
		key = k;
		data = new DocFrequency<T>(val);
		left = l;
		right = r;
	}
}
