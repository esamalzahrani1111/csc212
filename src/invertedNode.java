
public class invertedNode<T> {
    public String word;
	public Node<T> data;
	public invertedNode<T> next;
	
	
	public invertedNode () {
        word = null;
		data = null;
		next = null;
		
	}
	
	public invertedNode(String word,T val) {
		this.word = word;
		data = new Node<T>(val);
		next =null;
		
		
	}
	
	
	
	
	
	
	
}
