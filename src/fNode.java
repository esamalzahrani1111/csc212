
public class fNode<T> {

	
	
	public T data;
    int freq;
	public fNode<T> next;
	
	
	public fNode () {
		data = null;
		next = null;
        freq=0;
		
	}
	
	public fNode(T val) {
		
		data = val;
		next =null;
		freq=1;
		
	}
	
	
	
	
	
	
	
}
