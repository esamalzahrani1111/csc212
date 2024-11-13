public class Node<T> {

	public T docId;			// Document ID
	public int frequency;	// Frequency of term in document
	public Node<T> next;	// Pointer to next node
	
	public Node () {
		docId = null;
		frequency = 0;
		next = null;
		
	}
	
	public Node(T val) {
		
		docId = val;
		frequency = 1;
		next = null;
				
	}

}
