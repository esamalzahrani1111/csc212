public class indexNode<T,U> {
    public T docId;
    public Node<U> data;
	public indexNode next;

	public indexNode () {
        docId = null;
		data = null;
		next = null;
	}

	public indexNode (T id,U word) {
        docId = id;
		data = new Node<U>(word);
		next = null;
	}

}
