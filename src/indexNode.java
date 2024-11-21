public class indexNode<T,U> {
    public T docId;
    public LinkedList<U> data;
	public indexNode<T,U> next;

	public indexNode () {
        docId = null;
		data = null;
		next = null;
	}

	public indexNode (T id,U word) {
        docId = id;
		data = new LinkedList<U>(word);
		next = null;
	}

}
