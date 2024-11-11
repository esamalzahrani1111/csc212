
public class Node<T extends Comparable<T>> {

	
	
	public T data;
	public Node<T> next;
	
	
	public Node () {
		data = null;
		next = null;
		
	}
	
	public Node(T val) {
		
		data = val;
		next =null;
		
		
	}
	
	
	
	
	
	
	
}
